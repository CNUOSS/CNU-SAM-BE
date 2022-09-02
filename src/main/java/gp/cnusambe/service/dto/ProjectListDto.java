package gp.cnusambe.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.repository.domain.Project;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProjectListDto {
    private Long id;
    private String projectName;
    private String projectStatus;
    private Date createDate;
    private Date updateDate;
    private String ossLicenseName;
    private String projectCategoryName;
    private String userId;

    @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

    public List<ProjectListDto> makeProjectListDto(Page<Project> projectPage) {
        List<ProjectListDto> projectListDtoList = projectPage.stream().map(p -> modelMapper.map(p, ProjectListDto.class)).collect(Collectors.toList());
        return projectListDtoList;
    }
}
