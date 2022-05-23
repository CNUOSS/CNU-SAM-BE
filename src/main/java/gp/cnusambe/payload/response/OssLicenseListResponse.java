package gp.cnusambe.payload.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.dto.OssLicenseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OssLicenseListResponse {
    private MetaResponse meta;
    private List<OssLicenseDto> OssLicense;

}
