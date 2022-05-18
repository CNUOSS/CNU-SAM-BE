package gp.cnusambe.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.Project;
import gp.cnusambe.domain.ProjectCategory;
import gp.cnusambe.domain.User;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
    private Long ossLicenseId;
    private ProjectCategory projectCategory;
    private String userId;

    @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

    public
    Project makeProjectEntity(OssLicense license, User user){
        Project project = modelMapper.map(this, Project.class);

        project.setLicense(license);
        project.setUser(user);

        return project;
    }

}