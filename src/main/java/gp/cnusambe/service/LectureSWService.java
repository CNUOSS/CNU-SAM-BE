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
    private final ModelMapper strictMapper;
    private final LectureTypeRepository lectureTypeRepository;
    private final DepartmentRepository departmentRepository;
    private final LectureSWRepository lectureSWRepository;
    private final LectureMapRepository lectureMapRepository;
    private final RegistrationSWRepository registrationSWRepository;
    private final LectureSWQueryRepository lectureSWQueryRepository;

    public LectureSWDto createLectureSW(LectureSWDto swDto) {
        LectureSW sw = lectureSWRepository.save(new LectureSW(swDto));
        return strictMapper.map(sw, LectureSWDto.class);
    }

    public List<SWInLectureSWDto> createAllLectureMap(LectureSWDto lectureSw, List<SWInLectureSWDto> swMapDto) {
        List<SWInLectureSW> listOfSW = swMapDto.stream().map(element -> strictMapper.map(element, SWInLectureSW.class)).collect(Collectors.toList());
        List<SWInLectureSWDto> listOfSWInLectureSWDto = new ArrayList<>();

        for (SWInLectureSW sw : listOfSW) {
            RegistrationSW registrationSW = registrationSWRepository.findBySwManufacturerAndSwNameAndIsManaged(sw.getSwManufacturer(), sw.getSwName(), true)
                    .orElse(registrationSWRepository.save(new RegistrationSW(sw.getSwManufacturer(), sw.getSwName())));
            LectureMap lectureMap = createLectureMap(lectureSw.getId(), registrationSW.getId(), sw.getLicense(), lectureSw.getLatestUpdateDate());
            listOfSWInLectureSWDto.add(new SWInLectureSWDto(lectureMap.getRegistrationSWId(), registrationSW.getSwManufacturer(), registrationSW.getSwName(), lectureMap.getLicense()));
        }
        return listOfSWInLectureSWDto;
    }

    private LectureMap createLectureMap(Long lectureSWId, Long registrationSWId, String license, Date latestUpdateDate) {
        return lectureMapRepository.save(new LectureMap(lectureSWId, registrationSWId, license, latestUpdateDate));
    }

    public LectureSWListDto readAllLectureSW(String department, String year, String lectureType, String semester, String lectureName, String lectureNum, String owner, Pageable pageable) {
        boolean search = department.length() == 0 && year.length() == 0 && lectureType.length() == 0 && semester.length() == 0 && lectureName.length() == 0 && lectureNum.length() == 0 && owner.length() == 0 ? false : true;

        List<LectureSW> listOfSW = search
                ? lectureSWQueryRepository.findAllBy(department, year, lectureType, semester, lectureName, lectureNum, owner)
                : lectureSWRepository.findAll();
        List<Long> listOfSWId = listOfSW.stream().map(LectureSW::getId).collect(Collectors.toList());
        Page<LectureMap> pageOfMap = lectureMapRepository.findAllByLectureSWIdIn(listOfSWId, pageable);

        PageInfoDto pageInfo = new PageInfoDto(pageOfMap.getTotalElements(), pageOfMap.isLast(), pageOfMap.getTotalPages(), pageOfMap.getSize());
        return new LectureSWListDto(pageInfo, makeListOfLectureSWListDto(pageOfMap.getContent()));
    }

    private List<LectureSWList> makeListOfLectureSWListDto(List<LectureMap> pageOfMap) {
        List<LectureSWList> listOfLectureSWList = new ArrayList<>();
        for (LectureMap lectureMap : pageOfMap) {
            LectureSW lectureSW = lectureSWRepository.findById(lectureMap.getLectureSWId()).get();
            RegistrationSW registrationSW = registrationSWRepository.findById(lectureMap.getRegistrationSWId()).get();
            listOfLectureSWList.add(new LectureSWList(lectureSW, registrationSW, lectureMap));
        }
        return listOfLectureSWList;
    }

    public LectureSWDto readLectureSW(Long swId) {
        LectureSW lectureSW = lectureSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        return strictMapper.map(lectureSW, LectureSWDto.class);
    }

    public List<SWInLectureSWDto> readSWInLectureSW(Long swId) {
        List<LectureMap> listOfMap = lectureMapRepository.findAllByLectureSWId(swId);
        List<LectureSWList> listOfLectureSW = makeListOfLectureSWListDto(listOfMap);
        return listOfLectureSW.stream()
                .map(element -> strictMapper.map(element, SWInLectureSWDto.class))
                .collect(Collectors.toList());
    }

    public void deleteLectureSW(Long swId) {
        List<LectureMap> lectureMaps = lectureMapRepository.findAllByLectureSWId(swId);
        for (LectureMap map : lectureMaps)
            lectureMapRepository.deleteByLectureSWId(map.getLectureSWId());
        LectureSW sw = lectureSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        lectureSWRepository.delete(sw);
    }

    public List<LectureSWForChartDto> readLAllLectureSWForChart(String year) {
        List<LectureSW> a = lectureSWRepository.findAllByYear(year);
        List<Long> listOfSWId = a.stream().map(LectureSW::getId).collect(Collectors.toList());
        List<LectureMap> pageOfMap = lectureMapRepository.findAllByLectureSWIdIn(listOfSWId);

        Map<Long, Integer> map = new HashMap<>();
        for (LectureMap b : pageOfMap) {
            Long registrationSWId = b.getRegistrationSWId();
            if (map.containsKey(registrationSWId))
                map.put(registrationSWId, map.get(registrationSWId) + 1);
            else map.put(registrationSWId, 1);
        }

        List<LectureSWForChartDto> listOfLectureSWForChartDto = new ArrayList<>();
        for (Long key : map.keySet()) {
            Optional<RegistrationSW> optionOfRegistrationSW = registrationSWRepository.findByIdAndIsManaged(key, true);
            optionOfRegistrationSW.ifPresent(sw -> listOfLectureSWForChartDto.add(new LectureSWForChartDto(sw, map.get(key))));
        }
        return listOfLectureSWForChartDto;
    }

    public List<LectureTypeResponse> readAllLectureTypes() {
        return lectureTypeRepository.findAll()
                .stream()
                .map(element -> strictMapper.map(element, LectureTypeResponse.class))
                .collect(Collectors.toList());
    }

    public List<DepartmentResponse> readAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(element -> strictMapper.map(element, DepartmentResponse.class))
                .collect(Collectors.toList());
    }
}