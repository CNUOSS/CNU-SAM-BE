package gp.cnusambe.service;

import gp.cnusambe.domain.*;
import gp.cnusambe.dto.*;
import gp.cnusambe.exception.custom.SWNotFoundException;
import gp.cnusambe.payload.response.DepartmentResponse;
import gp.cnusambe.payload.response.LectureTypeResponse;
import gp.cnusambe.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureSWService {
    private final ModelMapper modelMapper;
    private final LectureTypeRepository lectureTypeRepository;
    private final DepartmentRepository departmentRepository;
    private final LectureSWRepository lectureSWRepository;
    private final LectureMapRepository lectureMapRepository;
    private final RegistrationSWRepository registrationSWRepository;
    private final LectureSWQueryRepository lectureSWQueryRepository;

    public LectureSWDto createLectureSW(LectureSWDto swDto) {
        LectureSW sw = lectureSWRepository.save(new LectureSW(swDto));
        return modelMapper.map(sw, LectureSWDto.class);
    }

    public List<SWInLectureSWDto> createAllLectureMap(LectureSWDto lectureSw, List<SWInLectureSWDto> swMapDto) {
        List<SWInLectureSW> listOfSW = swMapDto.stream().map(element -> modelMapper.map(element, SWInLectureSW.class)).collect(Collectors.toList());
        List<SWInLectureSWDto> listOfSWInLectureSWDto = new ArrayList<>();

        for (SWInLectureSW sw : listOfSW) {
            RegistrationSW registrationSW = registrationSWRepository.findBySwManufacturerAndSwNameAndIsManaged(sw.getSwManufacturer(), sw.getSwName(), true)
                    .orElse(registrationSWRepository.save(new RegistrationSW(sw.getSwManufacturer(), sw.getSwName())));
            createLectureMap(lectureSw.getId(), registrationSW.getId(), lectureSw.getLatestUpdateDate());
            listOfSWInLectureSWDto.add(new SWInLectureSWDto(registrationSW.getId(), registrationSW.getSwManufacturer(), registrationSW.getSwName()));
        }
        return listOfSWInLectureSWDto;
    }

    private LectureMap createLectureMap(Long lectureSWId, Long registrationSWId, Date latestUpdateDate) {
        return lectureMapRepository.save(new LectureMap(lectureSWId, registrationSWId, latestUpdateDate));
    }

    public LectureSWListDto readAllLectureSW(String department, String year, String lectureType, String semester, String lectureName, String lectureNum, String owner, Pageable pageable) {
        boolean search = department.length() == 0 && year.length() == 0 && lectureType.length() == 0 && semester.length() == 0 && lectureName.length() == 0 && lectureNum.length() == 0 && owner.length() == 0 ? false : true;

        List<LectureSW> listOfSW = search
                ? lectureSWQueryRepository.findAllBy(department, year, lectureType, semester, lectureName, lectureNum, owner)
                : lectureSWRepository.findAll();
        List<Long> listOfSWId = listOfSW.stream().map(LectureSW::getId).collect(Collectors.toList());
        Page<LectureMap> pageOfMap = lectureMapRepository.findByLectureSWIdIn(listOfSWId, pageable);

        PageInfoDto pageInfo = new PageInfoDto(pageOfMap.getTotalElements(), pageOfMap.isLast(), pageOfMap.getTotalPages(), pageOfMap.getSize());
        return new LectureSWListDto(pageInfo, makeListOfLectureSWListDto(pageOfMap.getContent()));
    }

    private List<LectureSWList> makeListOfLectureSWListDto(List<LectureMap> pageOfMap){
        List<LectureSWList> listOfLectureSWList = new ArrayList<>();
        for (LectureMap lectureMap : pageOfMap) {
            LectureSW lectureSW = lectureSWRepository.findById(lectureMap.getLectureSWId()).get();
            RegistrationSW registrationSW = registrationSWRepository.findById(lectureMap.getRegistrationSWId()).get();
            listOfLectureSWList.add(new LectureSWList(lectureSW, registrationSW));
        }
        return listOfLectureSWList;
    }

    public void deleteLectureSW(Long swId) {
        List<LectureMap> lectureMaps = lectureMapRepository.findAllByLectureSWId(swId);
        for (LectureMap map : lectureMaps)
            lectureMapRepository.deleteByLectureSWId(map.getLectureSWId());
        LectureSW sw = lectureSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        lectureSWRepository.delete(sw);
    }

    public List<LectureTypeResponse> readAllLectureTypes() {
        return lectureTypeRepository.findAll()
                .stream()
                .map(element -> modelMapper.map(element, LectureTypeResponse.class))
                .collect(Collectors.toList());
    }

    public List<DepartmentResponse> readAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(element -> modelMapper.map(element, DepartmentResponse.class))
                .collect(Collectors.toList());
    }
}