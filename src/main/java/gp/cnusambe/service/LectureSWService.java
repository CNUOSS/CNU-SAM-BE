package gp.cnusambe.service;

import gp.cnusambe.domain.LectureMap;
import gp.cnusambe.domain.LectureSW;
import gp.cnusambe.domain.RegistrationSW;
import gp.cnusambe.domain.SWInLectureSW;
import gp.cnusambe.dto.LectureMapDto;
import gp.cnusambe.dto.LectureSWDto;
import gp.cnusambe.dto.SWInLectureSWDto;
import gp.cnusambe.payload.response.DepartmentResponse;
import gp.cnusambe.payload.response.LectureTypeResponse;
import gp.cnusambe.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureSWService {
    private final ModelMapper modelMapper;
    private final LectureTypeRepository lectureTypeRepository;
    private final DepartmentRepository departmentRepository;
    private final LectureSWRepository lectureRepository;
    private final LectureMapRepository lectureMapRepository;
    private final RegistrationSWRepository registrationSWRepository;

    public LectureSWDto createLectureSW(LectureSWDto swDto) {
        LectureSW sw = lectureRepository.save(new LectureSW(swDto));
        return modelMapper.map(sw, LectureSWDto.class);
    }

    public List<LectureMapDto> createAllLectureMap(Long lectureSwId, List<SWInLectureSWDto> swMapDto) {
        List<SWInLectureSW> listOfSW = swMapDto.stream().map(element -> modelMapper.map(element, SWInLectureSW.class)).collect(Collectors.toList());
        List<LectureMapDto> listOfLectureMapDto = new ArrayList<>();

        for (SWInLectureSW sw : listOfSW) {
            RegistrationSW registrationSW = registrationSWRepository.findAllBySwManufacturerAndSwName(sw.getSwManufacturer(), sw.getSwName())
                    .orElse(registrationSWRepository.save(new RegistrationSW(sw.getSwManufacturer(), sw.getSwName())));
            LectureMap lectureMap = createLectureMap(lectureSwId, registrationSW.getId());
            listOfLectureMapDto.add(new LectureMapDto(lectureMap, sw));
        }
        return listOfLectureMapDto;
    }

    private LectureMap createLectureMap(Long lectureSWId, Long registrationSWId) {
        return lectureMapRepository.save(new LectureMap(lectureSWId, registrationSWId));
    }

    public List<LectureTypeResponse> getAllLectureTypes() {
        return lectureTypeRepository.findAll()
                .stream()
                .map(element -> modelMapper.map(element, LectureTypeResponse.class))
                .collect(Collectors.toList());
    }

    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(element -> modelMapper.map(element, DepartmentResponse.class))
                .collect(Collectors.toList());
    }
}
