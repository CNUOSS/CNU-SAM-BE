package gp.cnusambe.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    private List<OssAnalysisRequest> ossAnalysis;

    @Getter @Setter
    @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class OssAnalysisRequest{
        private String ossLocation;
        private String ossName;
        private String ossVersion;
        private String ossUrl;
        private Long licenseId;
    }
}

