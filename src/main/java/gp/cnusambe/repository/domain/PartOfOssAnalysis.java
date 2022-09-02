package gp.cnusambe.repository.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PartOfOssAnalysis {
    private String ossLocation;
    private String ossName;
    private String ossVersion;
    private String ossUrl;
    private Long licenseId;
}
