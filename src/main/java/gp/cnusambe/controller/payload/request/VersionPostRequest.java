package gp.cnusambe.controller.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.repository.domain.PartOfOssAnalysis;
import lombok.*;

import java.util.List;


@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VersionPostRequest {
    private Long projectId;
    private String versionName;
    private String versionDescription;
    private List<PartOfOssAnalysis> ossAnalysis;
}

