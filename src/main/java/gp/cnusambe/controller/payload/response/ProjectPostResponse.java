package gp.cnusambe.controller.payload.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProjectPostResponse {
    private Long id;
    private String projectDescription;
    private String projectName;
    private String projectStatus;
    private Date createDate;
    private Date updateDate;
    private Long ossLicenseId;
    private String projectCategoryName;
    private String userId;

}
