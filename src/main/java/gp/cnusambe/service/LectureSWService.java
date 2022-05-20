package gp.cnusambe.service;

import gp.cnusambe.payload.response.DepartmentResponse;
import gp.cnusambe.payload.response.LectureTypeResponse;
import gp.cnusambe.repository.DepartmentRepository;
import gp.cnusambe.repository.LectureTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureSWService {
    private final ModelMapper modelMapper;
    private final LectureTypeRepository lectureTypeRepository;
    private final DepartmentRepository departmentRepository;

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
