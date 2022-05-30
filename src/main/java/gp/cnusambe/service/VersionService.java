package gp.cnusambe.service;

import gp.cnusambe.domain.*;
import gp.cnusambe.dto.AnalysisRestrictionDto;
import gp.cnusambe.dto.LicenseProtectorDto;
import gp.cnusambe.dto.OssAnalysisDto;
import gp.cnusambe.dto.VersionDto;
import gp.cnusambe.exception.custom.VersionNotFoundException;
import gp.cnusambe.repository.AnalysisRestrictionMapRepository;
import gp.cnusambe.repository.OssAnalysisMapRepository;
import gp.cnusambe.repository.OssLicenseRepository;
import gp.cnusambe.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VersionService {
    private final OssLicenseService ossLicenseService;
    private final RestrictionService restrictionService;
    private final AnalysisRestrictionMapService analysisRestrictionMapService;
    private final VersionRepository versionRepository;
    private final OssAnalysisMapRepository ossAnalysisMapRepository;
    private final AnalysisRestrictionMapRepository analysisRestrictionMapRepository;

    private final ModelMapper modelMapper;

    public LicenseProtectorDto getLicenseProtector(Long versionId){
        //versionId에 해당하는 verison, ossAnalysisMap 찾기
        Version version = this.versionRepository.findById(versionId).orElseThrow(VersionNotFoundException::new);
        List<OssAnalysisMap> analysisMapList = this.ossAnalysisMapRepository.findAllByVersion_Id(versionId);

        //OssAnalysisDto : version, OssAnalysisMap 설정
        //OssAnalysisDto : analysisRestrictionMap 초기화
        List<OssAnalysisDto> analysisDtoList = analysisMapList.stream().map(a -> modelMapper.map(a, OssAnalysisDto.class)).collect(Collectors.toList());
        LicenseProtectorDto licenseProtectorDto = modelMapper.map(version, LicenseProtectorDto.class);
        licenseProtectorDto.setOssAnalysisDto(analysisDtoList);
        licenseProtectorDto.setAnalysisRestrictionDto(new ArrayList<>());

        //AnalysisRestrictionDto 생성 -> LicenseProtectorDto : AnalysisRestriction 에 저장
        for(OssAnalysisMap analysisMap : analysisMapList){
            List<AnalysisRestrictionMap> restrictionMap = this.analysisRestrictionMapRepository.findAllByAnalysisMap_Id(analysisMap.getId());
            List<Restriction> restrictionList = restrictionMap.stream().map(r -> r.getRestriction()).collect(Collectors.toList());

            AnalysisRestrictionDto restrictionDto = new AnalysisRestrictionDto(analysisMap.getLicenseName(), restrictionList);
            licenseProtectorDto.addAnalysisRestriction(restrictionDto);
        }

        return licenseProtectorDto;

    }

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

    public void deleteVersion(Long id){
        Optional<Version> version = this.versionRepository.findById(id);
        List<OssAnalysisMap> analysisMapList = this.ossAnalysisMapRepository.findAllByVersion_Id(id);

        for(OssAnalysisMap ossAnalysisMap :  analysisMapList){
            List<AnalysisRestrictionMap> restrictionMapList = this.analysisRestrictionMapRepository.findAllByAnalysisMap_Id(ossAnalysisMap.getId());
            for (AnalysisRestrictionMap restrictionMap : restrictionMapList){
                this.analysisRestrictionMapRepository.delete(restrictionMap);
            }
            this.ossAnalysisMapRepository.delete(ossAnalysisMap);
        }

        this.versionRepository.delete(version.get());

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
