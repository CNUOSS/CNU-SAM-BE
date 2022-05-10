package gp.cnusambe.controller;

import gp.cnusambe.dto.OssLicenseDto;
import gp.cnusambe.service.LicenseRestrictionMapService;
import gp.cnusambe.service.OssLicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class OssLicenseController {
    private final OssLicenseService ossLicenseService;

    @PostMapping("/licenses")
    public ResponseEntity<OssLicenseDto> postTest(@Valid @RequestBody OssLicenseDto licenseDto){
        OssLicenseDto newLicense = this.ossLicenseService.create(licenseDto);
        return new ResponseEntity<>(newLicense,HttpStatus.OK);

    }
}

