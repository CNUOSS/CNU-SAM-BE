package gp.cnusambe.controller;

import gp.cnusambe.payload.request.RegistrationSWRequest;
import gp.cnusambe.payload.response.ManufacturerResponse;
import gp.cnusambe.payload.response.RegistrationSWResponse;
import gp.cnusambe.service.RegistrationSWService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RegistrationSWController {
    private final RegistrationSWService registerSWService;

    @PostMapping("/registrations")
    public ResponseEntity<RegistrationSWResponse> postRegistrationSW(@RequestBody RegistrationSWRequest request){
        RegistrationSWResponse response = new RegistrationSWResponse(registerSWService.createRegistrationSW(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/registrations/{rsw_id}")
    public ResponseEntity<Void> deleteRegistrationSW(@PathVariable("rsw_id") Long swId){
        registerSWService.deleteRegistrationSW(swId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manufacturers")
    public ResponseEntity<List<ManufacturerResponse>> getManufacturers(){
        return new ResponseEntity<>(registerSWService.getAllManufacturers(), HttpStatus.OK);
    }
}
