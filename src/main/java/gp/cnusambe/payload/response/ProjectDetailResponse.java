package gp.cnusambe.payload.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.User;
import gp.cnusambe.domain.Version;
import gp.cnusambe.dto.ProjectDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProjectDetailResponse {
    private Long id;
    private String projectDescription;
    private String projectName;
    private String projectStatus;
    private Date createDate;
    private Date updateDate;
    private String projectCategoryName;
    private String ossLicenseName;
    private String userId;
    private List<VersionResponse> version;

}
