package gp.cnusambe.controller;

import gp.cnusambe.domain.Department;
import gp.cnusambe.domain.LectureType;
import gp.cnusambe.service.LectureSWService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class LectureSWController {
    private final LectureSWService lectureSwService;

    @GetMapping("/lecture-types")
    public ResponseEntity<List<LectureType>> getLectureTypes(){
        return new ResponseEntity<>(lectureSwService.getAllLectureTypes(), HttpStatus.OK);
    }
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartments(){
        return new ResponseEntity<>(lectureSwService.getAllDepartments(), HttpStatus.OK);
    }
}
