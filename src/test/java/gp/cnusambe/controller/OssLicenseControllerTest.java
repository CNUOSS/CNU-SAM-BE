package gp.cnusambe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gp.cnusambe.repository.domain.OssLicenseType;
import gp.cnusambe.repository.domain.Restriction;
import gp.cnusambe.service.dto.OssLicenseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class OssLicenseControllerTest extends AbstractContainerBaseTest{

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
    void OssLicense_등록하기() throws Exception{
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

    @Test
    void ossLicenseList_조회하기_ByLicenseName() throws Exception{
        mockMvc.perform(get("/licenses/search")
                .param("lc-name", "Apache"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void ossLicenseList_조회하기_ByLicenseName_검색결과X() throws Exception{
        mockMvc.perform(get("/licenses/search")
                        .param("lc-name", "aa"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void ossLicenseList_조회하기_ByLicenseTypeName() throws Exception{
        mockMvc.perform(get("/licenses/search")
                        .param("lc-type", "Permissive"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void ossLicenseList_조회하기_ByLicenseTypeName_검색결과X() throws Exception{
        mockMvc.perform(get("/licenses/search")
                        .param("lc-type", "AA"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void ossLicenseList_조회하기_ByRestriction() throws Exception{
        mockMvc.perform(get("/licenses/search")
                        .param("restriction", "배포시 라이선스사본첨부"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void ossLicenseList_전체조회하기() throws Exception{
        mockMvc.perform(get("/licenses/search"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void ossLicense_삭제하기() throws Exception{
        mockMvc.perform(delete("/licenses/83"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void ossLicenseData_가져오기() throws Exception{
        mockMvc.perform(get("/license-names"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}