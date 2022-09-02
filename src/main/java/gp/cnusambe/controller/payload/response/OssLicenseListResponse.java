package gp.cnusambe.controller.payload.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.service.dto.OssLicenseDto;
import gp.cnusambe.service.dto.PageInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OssLicenseListResponse {
    private PageInfoDto pageInfo;
    private List<OssLicenseDto> OssLicense;
}
