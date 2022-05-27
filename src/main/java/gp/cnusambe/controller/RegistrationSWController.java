package gp.cnusambe.controller;

import gp.cnusambe.dto.PageInfoDto;
import gp.cnusambe.dto.RegistrationSWDto;
import gp.cnusambe.payload.request.RegistrationSWRequest;
import gp.cnusambe.payload.response.ManufacturerResponse;
import gp.cnusambe.payload.response.RegistrationSWListResponse;
import gp.cnusambe.payload.response.RegistrationSWResponse;
import gp.cnusambe.service.RegistrationSWService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
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
public class RegistrationSWController {
    private final ModelMapper modelMapper;
    private final RegistrationSWService registerSWService;

    @PostMapping("/registrations")
    public ResponseEntity<RegistrationSWResponse> postRegistrationSW(@RequestBody RegistrationSWRequest request){
        RegistrationSWDto swDto = modelMapper.map(request, RegistrationSWDto.class);
        RegistrationSWResponse response = new RegistrationSWResponse(registerSWService.createRegistrationSW(swDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/registrations/search")
    public ResponseEntity<RegistrationSWListResponse> getAllRegistrationsW(
            @RequestParam(value="sw-mfr", required=false) String swManufacturer,
            @RequestParam(value="sw-name", required=false) String swName,
            @PageableDefault(size=9, page=0, sort="latestUpdateDate", direction= Sort.Direction.DESC) Pageable pageable
    ){
        String swManufacturer_ = Optional.ofNullable(swManufacturer).orElse("");
        String swName_ = Optional.ofNullable(swName).orElse("");

        Page<RegistrationSWDto> pageOfSW = registerSWService.readAllRegistrationSW(swManufacturer_, swName_, pageable);
        PageInfoDto pageInfo = new PageInfoDto(pageOfSW.getTotalElements(), pageOfSW.isLast(), pageOfSW.getTotalPages(), pageOfSW.getSize());
        RegistrationSWListResponse response = new RegistrationSWListResponse(pageInfo, pageOfSW.stream().collect(Collectors.toList()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/registrations/{rsw_id}")
    public ResponseEntity<RegistrationSWResponse> putRegistrationSW(@PathVariable("rsw_id") Long swId, @RequestBody RegistrationSWRequest request){
        RegistrationSWDto swDto = modelMapper.map(request, RegistrationSWDto.class);
        RegistrationSWResponse response = new RegistrationSWResponse(registerSWService.updateRegistrationSW(swId, swDto));
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
