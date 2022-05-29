package gp.cnusambe.controller;

import gp.cnusambe.dto.LectureMapDto;
import gp.cnusambe.dto.LectureSWDto;
import gp.cnusambe.dto.SWInLectureSWDto;
import gp.cnusambe.payload.request.LectureSWRequest;
import gp.cnusambe.payload.response.DepartmentResponse;
import gp.cnusambe.payload.response.LectureSWResponse;
import gp.cnusambe.payload.response.LectureTypeResponse;
import gp.cnusambe.service.LectureSWService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class LectureSWController {
    private final ModelMapper modelMapper;
    private final LectureSWService lectureSwService;

    @PostMapping("/lectures")
    public ResponseEntity<LectureSWResponse> getLectureSW(@RequestBody LectureSWRequest request) {
        LectureSWDto lectureSwDto = modelMapper.map(request, LectureSWDto.class);
        lectureSwDto = lectureSwService.createLectureSW(lectureSwDto);

        List<SWInLectureSWDto> swInLectureSWDtos = request.getSw()
                .stream().map(element -> modelMapper.map(element, SWInLectureSWDto.class))
                .collect(Collectors.toList());
        List<LectureMapDto> lectureMapDtos = lectureSwService.createAllLectureMap(lectureSwDto.getId(), swInLectureSWDtos);

        LectureSWResponse response = new LectureSWResponse(lectureSwDto, lectureMapDtos);
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
