package gp.cnusambe.repository.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.service.dto.ProjectDto;
import gp.cnusambe.controller.payload.response.ProjectPostResponse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Builder
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_description", nullable = true, length = 100)
    private String projectDescription;

    @Column(name = "project_name", nullable = false, length = 50)
    private String projectName;

    @Column(name = "project_status", nullable = false, length = 5)
    private String projectStatus;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateDate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn
    @JsonProperty("oss_license")
    private OssLicense license;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name="project_category_name")
    @JsonProperty("project_category")
    private ProjectCategory projectCategory;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @JsonProperty("user")
    private User user;

    @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

    public ProjectDto makeProjectDto(List<Version> versionList){
        ProjectDto projectDto = modelMapper.map(this, ProjectDto.class);
        projectDto.setVersion(versionList);

        return projectDto;
    }

    public ProjectPostResponse makeProjectResponse(){
        ProjectPostResponse response = modelMapper.map(this, ProjectPostResponse.class);
        response.setOssLicenseId(this.license.getId());
        response.setUserId(this.user.getUserId());

        return response;
    }

}
