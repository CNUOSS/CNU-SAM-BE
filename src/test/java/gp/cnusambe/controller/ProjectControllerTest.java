package gp.cnusambe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gp.cnusambe.controller.payload.request.ProjectPostRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class ProjectControllerTest extends AbstractContainerBaseTest{

    @Autowired
    public MockMvc mockMvc;

    @Test
    void Project_등록하기() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        ProjectPostRequest request = ProjectPostRequest.builder()
                .projectDescription("this is test description")
                .projectName("힝구힝구")
                .ossLicenseId(81L)
                .projectCategoryName("개인")
                .userId("201902690")
                .build();

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void project_조회하기() throws Exception{
        mockMvc.perform(get("/projects/search"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void project조회하기_ByLicenseId() throws Exception{
        mockMvc.perform(get("/projects/search")
                        .param("lc-id", "75"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void project_상세조회하기() throws Exception{
        mockMvc.perform(get("/projects/19"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}