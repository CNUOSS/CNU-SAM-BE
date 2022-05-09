package gp.cnusambe.service;

import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.OssLicenseType;
import gp.cnusambe.repository.OssLicenseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
class OssLicenseServiceTest {

    @Autowired
    OssLicenseService ossLicenseService;

    @Autowired
    OssLicenseRepository ossLicenseRepository;

    @AfterEach
    public void cleanUp(){
        ossLicenseRepository.deleteAll();
    }

    @Test
    public void osslicense추가하기(){
        //given
        String license_name = "mit";
        String license_url = "http://mit.com";
        OssLicenseType ossLicenseType = new OssLicenseType("Permissive");

        //when
        OssLicense new_license = ossLicenseRepository.save(OssLicense.builder()
                .licenseName(license_name)
                .licenseUrl(license_url)
                .ossLicenseType(ossLicenseType)
                .build());

        //then
        assertThat(new_license.getLicenseName()).isEqualTo(license_name);
    }
}