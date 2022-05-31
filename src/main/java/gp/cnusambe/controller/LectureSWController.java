package gp.cnusambe.controller;

import gp.cnusambe.dto.*;
import gp.cnusambe.payload.request.LectureSWRequest;
import gp.cnusambe.payload.response.*;
import gp.cnusambe.service.LectureSWService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class LectureSWController {
    private final ModelMapper modelMapper;
    private final LectureSWService lectureSwService;

    @PostMapping("/lectures")
    public ResponseEntity<LectureSWResponse> getLectureSW(@RequestBody LectureSWRequest request) {
        LectureSWDto lectureSwDto = modelMapper.map(request, LectureSWDto.class);
        lectureSwDto.setRegistrationSW(lectureSwService.getAllRegistrationSW(request.getSw()));

        LectureSWResponse response = new LectureSWResponse(lectureSwService.createLectureSW(lectureSwDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/lectures/{lsw_id}")
    public ResponseEntity<Void> deleteLectureSW(@PathVariable("lsw_id") Long swId){
        lectureSwService.deleteLectureSW(swId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lecture-types")
    public ResponseEntity<List<LectureTypeResponse>> getLectureTypes() {
        return new ResponseEntity<>(lectureSwService.getAllLectureTypes(), HttpStatus.OK);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentResponse>> getDepartments() {
        return new ResponseEntity<>(lectureSwService.getAllDepartments(), HttpStatus.OK);
    }
}
