package gp.cnusambe.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gp.cnusambe.domain.LicenseRestrictionMap;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.Restriction;
import gp.cnusambe.service.LicenseRestrictionMapService;
import gp.cnusambe.service.OssLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class OssLicenseController {
    private OssLicenseService ossLicenseService;
    private LicenseRestrictionMapService licenseRestrictionMapService;

    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);


    @Autowired
    public OssLicenseController(OssLicenseService ossLicenseService,LicenseRestrictionMapService licenseRestrictionMapService){
        this.ossLicenseService = ossLicenseService;
        this.licenseRestrictionMapService = licenseRestrictionMapService;
    }

    @PostMapping("/licenses")
    public ResponseEntity<Message> post(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        OssLicense request_license = objectMapper.readValue(messageBody,OssLicense.class);
        Restriction request_restriction = objectMapper.readValue(messageBody,Restriction.class);

        OssLicense new_license = this.ossLicenseService.create(request_license);
        LicenseRestrictionMap new_map = this.licenseRestrictionMapService.create(new_license,request_restriction);

        Message message = new Message();
        message.setOss_license(new_license);
        message.setLicense_restriction_map(new_map);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }



}
