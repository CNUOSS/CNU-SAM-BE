package gp.cnusambe.controller.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

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
