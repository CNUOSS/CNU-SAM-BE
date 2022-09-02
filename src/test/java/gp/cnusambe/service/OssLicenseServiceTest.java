package gp.cnusambe.service;

import gp.cnusambe.repository.domain.OssLicense;
import gp.cnusambe.repository.domain.OssLicenseType;
import gp.cnusambe.repository.domain.Restriction;
import gp.cnusambe.service.dto.OssLicenseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
class OssLicenseServiceTest {

    @Autowired
    OssLicenseService ossLicenseService;

    @AfterEach
    public void cleanUp(){

    }

    @Test
    @Transactional
    public void osslicense_등록하기(){
        //Given
        List<Restriction> restrictionList = new ArrayList<>();
        restrictionList.add(new Restriction("배포시 라이선스사본첨부"));
        restrictionList.add(new Restriction("조합저작물 작성 및 타 라이선스 배포허용"));

        OssLicenseDto licenseDto = OssLicenseDto.builder()
                .licenseName("Apache License 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .ossLicenseType(new OssLicenseType("Permissive"))
                .restriction(restrictionList)
                .build();

        //when
        OssLicenseDto newLicenseDto = ossLicenseService.create(licenseDto);

        //then
        assertThat(newLicenseDto.getId()).isNotNull();
    }

    @Test
    public void ossLicenseData_가져오기(){
        //Given
        List<OssLicense> licenseList = ossLicenseService.getOssLicenseData();
        //Then
        Assertions.assertTrue(licenseList.size() >= 1);
    }
}