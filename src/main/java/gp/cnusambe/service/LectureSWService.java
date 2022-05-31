package gp.cnusambe.service;

import gp.cnusambe.domain.LectureSW;
import gp.cnusambe.domain.RegistrationSW;
import gp.cnusambe.domain.SWInLectureSW;
import gp.cnusambe.dto.LectureSWDto;
import gp.cnusambe.exception.custom.SWNotFoundException;
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
    private final RegistrationSWRepository registrationSWRepository;

    public LectureSWDto createLectureSW(LectureSWDto swDto) {
        LectureSW sw = lectureRepository.save(new LectureSW(swDto));
        return modelMapper.map(sw, LectureSWDto.class);
    }

    public List<RegistrationSW> getAllRegistrationSW(List<SWInLectureSW> listOfSW){
        List<RegistrationSW> listOfRegistrationSW = new ArrayList<>();
        for(SWInLectureSW sw : listOfSW) {
            RegistrationSW registrationSW
                    = registrationSWRepository.findBySwManufacturerAndSwNameAndIsManaged(sw.getSwManufacturer(), sw.getSwName(), true)
                    .orElse(registrationSWRepository.save(new RegistrationSW(sw.getSwManufacturer(), sw.getSwName())));
            listOfRegistrationSW.add(registrationSW);
        }
        return listOfRegistrationSW;
    }

    public void deleteLectureSW(Long swId) {
        LectureSW sw = lectureRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        lectureRepository.delete(sw);
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
