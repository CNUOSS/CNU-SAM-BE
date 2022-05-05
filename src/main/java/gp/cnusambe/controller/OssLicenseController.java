package gp.cnusambe.controller;

import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.Restriction;
import gp.cnusambe.service.OssLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OssLicenseController {
    private OssLicenseService ossLicenseService;

    @Autowired
    public OssLicenseController(OssLicenseService ossLicenseService){
        this.ossLicenseService = ossLicenseService;
    }

    @PostMapping("/licenses")
    public @ResponseBody
    OssLicense post(@Valid @RequestBody OssLicense license){
        OssLicense new_license = this.ossLicenseService.create(license);
        return new_license;
    }


}
