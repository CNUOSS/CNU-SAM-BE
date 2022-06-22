package gp.cnusambe.dto;

import gp.cnusambe.domain.Version;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OssAnalysisDto {
    private String ossLocation;
    private String ossName;
    private String ossVersion;
    private String ossUrl;
    private String licenseName;
    private String licenseUrl;
    private String licenseTypeName;
    private Version version;
}
