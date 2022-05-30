package gp.cnusambe.payload.response;

import gp.cnusambe.dto.AnalysisRestrictionDto;
import gp.cnusambe.dto.OssAnalysisDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LicenseProtectorResponse {
    private Long versionId;
    private String versionName;
    private String versionDescription;
    private List<OssAnalysis> ossAnalysis;
    private List<AnalysisRestrictionDto> analysisRestriction;

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class OssAnalysis {
        private String ossLocation;
        private String ossName;
        private String ossVersion;
        private String ossUrl;
        private String licenseName;
        private String licenseUrl;
        private String licenseTypeName;

        public OssAnalysis(OssAnalysisDto analysisDto){
            this.setOssLocation(analysisDto.getOssLocation());
            this.setOssName(analysisDto.getOssName());
            this.setOssVersion(analysisDto.getOssVersion());
            this.setOssUrl(analysisDto.getOssUrl());
            this.setLicenseName(analysisDto.getLicenseName());
            this.setLicenseUrl(analysisDto.getLicenseUrl());
            this.setLicenseTypeName(analysisDto.getLicenseTypeName());
        }
    }

    public void setOssAnalysis(List<OssAnalysisDto> analysisDtoList){
        this.ossAnalysis = new ArrayList<>();

        for(OssAnalysisDto analysisDto : analysisDtoList){
            this.ossAnalysis.add(new OssAnalysis(analysisDto));
        }
    }


}
