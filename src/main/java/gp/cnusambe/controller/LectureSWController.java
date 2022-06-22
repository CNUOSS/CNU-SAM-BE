package gp.cnusambe.controller;

import gp.cnusambe.dto.*;
import gp.cnusambe.payload.request.LectureSWRequest;
import gp.cnusambe.payload.response.*;
import gp.cnusambe.service.LectureSWService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class LectureSWController {
    private final ModelMapper strictMapper;
    private final LectureSWService lectureSwService;

    @PostMapping("/lectures")
    public ResponseEntity<LectureSWResponse> getLectureSW(@RequestBody LectureSWRequest request) {
        LectureSWDto lectureSwDto = strictMapper.map(request, LectureSWDto.class);
        lectureSwDto = lectureSwService.createLectureSW(lectureSwDto);

        List<SWInLectureSWDto> swInLectureSWDtos = request.getSw()
                .stream().map(element -> strictMapper.map(element, SWInLectureSWDto.class))
                .collect(Collectors.toList());
        swInLectureSWDtos = lectureSwService.createAllLectureMap(lectureSwDto, swInLectureSWDtos);

        LectureSWResponse response = new LectureSWResponse(lectureSwDto, swInLectureSWDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/lectures/search")
    public ResponseEntity<LectureSWListResponse> getAllLectureSW(
            @RequestParam(value="department", required=false) String department,
            @RequestParam(value="year", required=false) String year,
            @RequestParam(value="lecture-type", required=false) String lectureType,
            @RequestParam(value="semester", required=false) String semester,
            @RequestParam(value="lecture-name", required=false) String lectureName,
            @RequestParam(value="lecture-num", required=false) String lectureNum,
            @RequestParam(value="owner", required = false) String owner,
            @PageableDefault(size=9, page=0, sort="latestUpdateDate", direction= Sort.Direction.DESC) Pageable pageable
    ){
        String department_ = Optional.ofNullable(department).orElse("");
        String year_ = Optional.ofNullable(year).orElse("");
        String lectureType_ = Optional.ofNullable(lectureType).orElse("");
        String semester_ = Optional.ofNullable(semester).orElse("");
        String lectureName_ = Optional.ofNullable(lectureName).orElse("");
        String lectureNum_ = Optional.ofNullable(lectureNum).orElse("");
        String owner_ = Optional.ofNullable(owner).orElse("");

        LectureSWListDto lectureSWListDto = lectureSwService.readAllLectureSW(department_, year_, lectureType_, semester_, lectureName_, lectureNum_, owner_, pageable);
        LectureSWListResponse response = strictMapper.map(lectureSWListDto, LectureSWListResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/lectures/{lsw_id}")
    public ResponseEntity<LectureSWResponse> getLectureSW(@PathVariable("lsw_id") Long swId) {
        LectureSWDto lectureSWDto = lectureSwService.readLectureSW(swId);
        List<SWInLectureSWDto> swInLectureSWDtos = lectureSwService.readSWInLectureSW(swId);
        LectureSWResponse response = new LectureSWResponse(lectureSWDto, swInLectureSWDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/lectures/{lsw_id}")
    public ResponseEntity<Void> deleteLectureSW(@PathVariable("lsw_id") Long swId){
        lectureSwService.deleteLectureSW(swId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("lectures/chart/{year}")
    public ResponseEntity<LectureSWForChartResponse> getLectureSWForChart(@PathVariable("year") String year) {
        List<LectureSWForChartDto> lectureSWForChartDtos = lectureSwService.readLAllLectureSWForChart(year);
        LectureSWForChartResponse response = new LectureSWForChartResponse(lectureSWForChartDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/lecture-types")
    public ResponseEntity<List<LectureTypeResponse>> getLectureTypes() {
        return new ResponseEntity<>(lectureSwService.readAllLectureTypes(), HttpStatus.OK);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentResponse>> getDepartments() {
        return new ResponseEntity<>(lectureSwService.readAllDepartments(), HttpStatus.OK);
    }
}
