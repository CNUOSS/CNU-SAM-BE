package gp.cnusambe.controller;

import gp.cnusambe.repository.domain.OssLicenseType;
import gp.cnusambe.service.OssLicenseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OssLicenseTypeController {
    private final OssLicenseTypeService ossLicenseTypeService;

    @GetMapping("/license-type-names")
    public ResponseEntity<List<OssLicenseType>> getOssLicenseTypeData(){
        List<OssLicenseType> licenseTypeList = this.ossLicenseTypeService.getOssLicenseTypeData();
        return new ResponseEntity<>(licenseTypeList, HttpStatus.OK);
    }
}
