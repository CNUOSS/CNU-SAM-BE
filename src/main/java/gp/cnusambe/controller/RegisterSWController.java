package gp.cnusambe.controller;

import gp.cnusambe.payload.response.ManufacturerResponse;
import gp.cnusambe.service.RegisterSWService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RegisterSWController {
    private final RegisterSWService registerSWService;

    @GetMapping("/manufacturers")
    public ResponseEntity<List<ManufacturerResponse>> getManufacturers(){
        return new ResponseEntity<>(registerSWService.getAllManufacturers(), HttpStatus.OK);
    }
}
