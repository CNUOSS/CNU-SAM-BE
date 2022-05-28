package gp.cnusambe.service;

import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.PartOfOssAnalysis;
import gp.cnusambe.domain.Restriction;
import gp.cnusambe.domain.Version;
import gp.cnusambe.dto.OssAnalysisDto;
import gp.cnusambe.dto.VersionDto;
import gp.cnusambe.repository.OssLicenseRepository;
import gp.cnusambe.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VersionService {
    private final OssLicenseService ossLicenseService;
    private final RestrictionService restrictionService;
    private final VersionRepository versionRepository;
    private final AnalysisRestrictionMapService analysisRestrictionMapService;
    private final ModelMapper modelMapper;

    public Version create(VersionDto versionDto, List<PartOfOssAnalysis> ossAnalysisRequests){
        Version version = this.modelMapper.map(versionDto, Version.class);
        Version newVersion = this.versionRepository.save(version);

        for(PartOfOssAnalysis ossAnalysis : ossAnalysisRequests){
            // Anaysis에 해당되는 license,restriction 찾기
            OssLicense ossLicense = this.ossLicenseService.getOssLicense(ossAnalysis.getLicenseId());
            List<Restriction> restrictionList = this.restrictionService.getRestrictionByOssLicense(ossLicense);

            //OssAnalysisRequest -> AnalysisDto
            OssAnalysisDto analysisDto = this.makeOssLicenseAnalysisDto(ossAnalysis, newVersion, ossLicense);

            //Save AnalysisRestrictionMap
            this.analysisRestrictionMapService.create(analysisDto,restrictionList);
        }
        return newVersion;
    }

    private OssAnalysisDto makeOssLicenseAnalysisDto(PartOfOssAnalysis analysis, Version version, OssLicense license){
        OssAnalysisDto analysisDto = modelMapper.map(analysis, OssAnalysisDto.class);
        analysisDto.setVersion(version);
        analysisDto.setLicenseName(license.getLicenseName());
        analysisDto.setLicenseUrl(license.getLicenseUrl());
        analysisDto.setLicenseTypeName(license.getOssLicenseType().getLicenseTypeName());

        return analysisDto;
    }
}
