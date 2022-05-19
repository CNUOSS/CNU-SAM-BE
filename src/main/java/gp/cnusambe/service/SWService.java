package gp.cnusambe.service;

import gp.cnusambe.domain.Department;
import gp.cnusambe.domain.LectureType;
import gp.cnusambe.repository.DepartmentRepository;
import gp.cnusambe.repository.LectureTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SWService {
    private final LectureTypeRepository lectureTypeRepository;
    private final DepartmentRepository departmentRepository;

    public List<LectureType> getAllLectureTypes() {
        return lectureTypeRepository.findAll();
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
