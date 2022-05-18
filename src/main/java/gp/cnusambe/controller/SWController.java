package gp.cnusambe.controller;

import gp.cnusambe.domain.LectureType;
import gp.cnusambe.service.SWService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SWController {
    private final SWService swService;

    @GetMapping("/lecture-types")
    public ResponseEntity<List<LectureType>> getLectureTypes(){
        return new ResponseEntity<>(swService.getAllLectureTypes(), HttpStatus.OK);
    }
}
