package gp.cnusambe.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.domain.*;
import gp.cnusambe.controller.payload.response.ProjectDetailResponse;
import gp.cnusambe.controller.payload.response.VersionResponse;
import gp.cnusambe.repository.domain.OssLicense;
import gp.cnusambe.repository.domain.User;
import gp.cnusambe.repository.domain.Version;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public ProjectDetailResponse makeProjectDetailResponse(){
        ProjectDetailResponse response = modelMapper.map(this, ProjectDetailResponse.class);

        List<VersionResponse> versionResponses = this.getVersion().stream().map(v -> modelMapper.map(v, VersionResponse.class)).collect(Collectors.toList());

        response.setVersion(versionResponses);
        response.setUserId(this.getUser().getUserId());
        response.setOssLicenseName(this.getLicense().getLicenseName());

        return response;
    }





}