package gp.cnusambe.dto;

import gp.cnusambe.domain.Project;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VersionDto {
    private Project project;
    private String versionName;
    private String versionDescription;
}
