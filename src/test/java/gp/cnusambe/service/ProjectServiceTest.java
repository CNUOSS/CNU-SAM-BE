package gp.cnusambe.service;

import gp.cnusambe.dto.ProjectDto;
import gp.cnusambe.dto.ProjectListDto;
import gp.cnusambe.payload.request.ProjectPostRequest;
import gp.cnusambe.payload.response.ProjectPostResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    void create() {
        //Given
        ProjectPostRequest request = ProjectPostRequest.builder()
                .projectDescription("쪽갈비는 맛있어")
                .projectName("testProject")
                .ossLicenseId(75l)
                .projectCategoryName("창업")
                .userId("201902690")
                .build();

        //When
        ProjectPostResponse response = projectService.create(request);

        //Then
        assertThat(response.getId()).isNotNull();
    }

    @Test
    void getProjectDetailTest(){
        //When
        ProjectDto projectDto = projectService.getProjectDetail(22L);

        //Then
        assertThat(projectDto.getUser().getUserId()).isEqualTo("201902690");
        assertThat(projectDto.getLicense().getId()).isEqualTo(75);
        assertThat(projectDto.getVersion().size()).isEqualTo(2);
    }

}