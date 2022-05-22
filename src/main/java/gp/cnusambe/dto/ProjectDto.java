package gp.cnusambe.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.domain.*;
import gp.cnusambe.payload.response.ProjectDetailResponse;
import gp.cnusambe.payload.response.ProjectPostResponse;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProjectDto {
    private Long id;
    private String projectDescription;
    private String projectName;
    private String projectStatus;
    private Date createDate;
    private Date updateDate;
    private String projectCategoryName;
    private OssLicense license;
    private User user;
    private List<Version> version;

    @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

    public Project makeProjectEntity(OssLicense license, User user, ProjectCategory category){
        Project project = modelMapper.map(this, Project.class);

        project.setLicense(license);
        project.setUser(user);
        project.setProjectCategory(category);

        return project;
    }





}