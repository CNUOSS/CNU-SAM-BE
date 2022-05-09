package gp.cnusambe.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.payload.Message;
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
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class OssLicenseController {
    // TODO: Controller field에 final을 추가하고, @RequiredArgsConstructor를 사용하여 생성자를 주입
    private OssLicenseService ossLicenseService;
    private LicenseRestrictionMapService licenseRestrictionMapService;

    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


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
        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
        HashMap<String,Object> map = objectMapper.readValue(messageBody, typeRef);

        OssLicense new_license = this.ossLicenseService.create(request_license);
        ArrayList<String> new_map = this.licenseRestrictionMapService.create(new_license,map.get("restriction"));

        // FIXME: Response(Message)
        Message message = new Message();
        message.setOss_license(new_license);
        message.setRestriction(new_map);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }



}
