package gp.cnusambe.service;

import gp.cnusambe.service.dto.LicenseProtectorDto;
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
class VersionServiceTest {

    @Autowired
    VersionService versionService;

    @Test
    void licenseProtector_조회하기(){
        LicenseProtectorDto licenseProtectorDto = versionService.getLicenseProtector(25L);

        assertThat(licenseProtectorDto.getVersionName()).isEqualTo("522Test");
        assertThat(licenseProtectorDto.getVersionId()).isEqualTo(25L);
        assertThat(licenseProtectorDto.getOssAnalysisDto().size()).isEqualTo(3);
        assertThat(licenseProtectorDto.getAnalysisRestrictionDto().size()).isEqualTo(3);

    }
}