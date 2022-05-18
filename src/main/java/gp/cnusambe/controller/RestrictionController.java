package gp.cnusambe.controller;

import gp.cnusambe.domain.Restriction;
import gp.cnusambe.service.RestrictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestrictionController {
    private final RestrictionService restrictionService;

    @GetMapping("/restriction-names")
    public ResponseEntity<List<Restriction>> getRestrictionData(){
        List<Restriction> restrictionList = this.restrictionService.getRestrictionData();
        return new ResponseEntity<>(restrictionList, HttpStatus.OK);
    }
}
