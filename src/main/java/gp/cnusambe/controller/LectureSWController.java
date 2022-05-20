package gp.cnusambe.controller;

import gp.cnusambe.payload.response.DepartmentResponse;
import gp.cnusambe.payload.response.LectureTypeResponse;
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
    public ResponseEntity<List<LectureTypeResponse>> getLectureTypes(){
        return new ResponseEntity<>(lectureSwService.getAllLectureTypes(), HttpStatus.OK);
    }
    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentResponse>> getDepartments(){
        return new ResponseEntity<>(lectureSwService.getAllDepartments(), HttpStatus.OK);
    }
}
