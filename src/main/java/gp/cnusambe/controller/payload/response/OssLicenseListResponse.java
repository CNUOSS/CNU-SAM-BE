package gp.cnusambe.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.repository.domain.OssLicenseType;
import gp.cnusambe.repository.domain.Restriction;
import gp.cnusambe.service.dto.OssLicenseDto;
import gp.cnusambe.service.dto.PageInfoDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OssLicenseListResponse {
    private PageInfoDto pageInfo;
    private List<OssLicenseListDto> ossLicenseList;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<OssLicenseDto> OssLicense;

    public OssLicenseListResponse(PageInfoDto pageInfo, List<OssLicenseDto> ossLicense){
        this.pageInfo = pageInfo;
        this.OssLicense = ossLicense;
        this.ossLicenseList = ossLicense.stream().map(o -> new OssLicenseListDto(o)).collect(Collectors.toList());
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class OssLicenseListDto{
        private Long id;
        private String licenseName;
        private String licenseUrl;
        private OssLicenseType ossLicenseType;
        private List<RestrictionScope> restriction;

        public OssLicenseListDto(OssLicenseDto licenseDto){
            this.id = licenseDto.getId();
            this.licenseName = licenseDto.getLicenseName();
            this.licenseUrl = licenseDto.getLicenseUrl();
            this.ossLicenseType = licenseDto.getOssLicenseType();
            this.restriction = this.makeRestrictionScopeList(licenseDto.getRestriction());

        }

        private List<RestrictionScope> makeRestrictionScopeList(List<Restriction> restriction){
            List<RestrictionScope> restrictionScopeList = restriction.stream().map(r -> new RestrictionScope(r.getRestrictionName(),r.getScope())).collect(Collectors.toList());
            return restrictionScopeList;
        }
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class RestrictionScope{
        private String restriction;

        public RestrictionScope(String restrictionName, String scope){
            this.restriction = restrictionName;
            if (!scope.equals(" ")){
                restriction += "(" + scope + ")";
            }
        }
    }
}
