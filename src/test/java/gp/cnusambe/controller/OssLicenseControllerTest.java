package gp.cnusambe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.OssLicenseType;
import gp.cnusambe.domain.Restriction;
import gp.cnusambe.dto.OssLicenseDto;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class OssLicenseControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void OssLicnese_등록하기() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

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

        String requestBody = mapper.writeValueAsString(licenseDto);

        //When
        mockMvc.perform(post("/licenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
        //Then
                .andExpect(status().isOk())
                .andDo(print());


    }
}