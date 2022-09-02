package gp.cnusambe.service.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LicenseProtectorDto {
    private Long versionId;
    private String versionName;
    private String versionDescription;
    private List<OssAnalysisDto> ossAnalysisDto;
    private List<AnalysisRestrictionDto> analysisRestrictionDto;

    /*
    * description : 변수 analysisRestrictionDto에 AnalysisRestrctionDto 객체 한개 추가
    * */
    public void addAnalysisRestriction(AnalysisRestrictionDto restrictionDto){
        this.analysisRestrictionDto.add(restrictionDto);
    }
}
