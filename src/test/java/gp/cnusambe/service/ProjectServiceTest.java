package gp.cnusambe.service;

import gp.cnusambe.domain.ProjectCategory;
import gp.cnusambe.dto.ProjectDto;
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
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    void create() {
        //Given
        ProjectDto projectDto = ProjectDto.builder()
                .projectDescription("쪽갈비는 맛있어")
                .projectName("testProject")
                .projectStatus("C")
                .ossLicenseId(75l)
                .projectCategoryName("창업")
                .userId("201902690")
                .build();

        //When
        ProjectDto newProjectDto = projectService.create(projectDto);

        //Then
        assertThat(newProjectDto.getId()).isNotNull();
    }
}