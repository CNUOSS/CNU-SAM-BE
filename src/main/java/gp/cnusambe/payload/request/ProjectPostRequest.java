package gp.cnusambe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.Project;
import gp.cnusambe.domain.ProjectCategory;
import gp.cnusambe.domain.User;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProjectPostRequest {
    private String projectDescription;
    private String projectName;
    private Long ossLicenseId;
    private String projectCategoryName;
    private String userId;
}
