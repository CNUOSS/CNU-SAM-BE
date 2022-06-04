package gp.cnusambe.service;

import gp.cnusambe.domain.*;
import gp.cnusambe.dto.AnalysisRestrictionDto;
import gp.cnusambe.dto.LicenseProtectorDto;
import gp.cnusambe.dto.OssAnalysisDto;
import gp.cnusambe.dto.VersionDto;
import gp.cnusambe.exception.custom.ProjectNotFoundException;
import gp.cnusambe.exception.custom.VersionNotFoundException;
import gp.cnusambe.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VersionService {
    private final RestrictionService restrictionService;
    private final AnalysisRestrictionMapService analysisRestrictionMapService;
    private final ProjectRepository projectRepository;
    private final VersionRepository versionRepository;
    private final OssAnalysisMapRepository ossAnalysisMapRepository;
    private final AnalysisRestrictionMapRepository analysisRestrictionMapRepository;
    private final OssLicenseRepository ossLicenseRepository;

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

            if(!Objects.isNull(analysisMap.getLicenseName())){
                //analysisMap의 licenseName이 비워져있지 않을때
                AnalysisRestrictionDto restrictionDto = new AnalysisRestrictionDto(analysisMap.getLicenseName(), restrictionList);
                licenseProtectorDto.addAnalysisRestriction(restrictionDto);
            }
        }

        return licenseProtectorDto;

    }

    public Version create(VersionDto versionDto, List<PartOfOssAnalysis> ossAnalysisRequests){
        Version version = this.modelMapper.map(versionDto, Version.class);
        Version newVersion = this.versionRepository.save(version);

        for(PartOfOssAnalysis ossAnalysis : ossAnalysisRequests){
            // Anaysis에 해당되는 license,restriction 찾기
            Optional<OssLicense> ossLicense = this.ossLicenseRepository.findOssLicenseById(ossAnalysis.getLicenseId());
            List<Restriction> restrictionList = this.restrictionService.getRestrictionByOssLicense(ossLicense.orElse(null));

            //OssAnalysisRequest -> AnalysisDto
            OssAnalysisDto analysisDto = this.makeOssLicenseAnalysisDto(ossAnalysis, newVersion, ossLicense);

            //Save AnalysisRestrictionMap
            this.analysisRestrictionMapService.create(analysisDto,restrictionList);
        }

        //Project Status -> "R"로 변경
        Project project = this.projectRepository.findProjectById(version.getProject().getId()).orElseThrow(ProjectNotFoundException::new);
        this.updateProjectStatus(project, "R");

        return newVersion;
    }


    public void deleteVersion(Long id){
        Version version = this.versionRepository.findById(id).orElseThrow(VersionNotFoundException::new);
        List<OssAnalysisMap> analysisMapList = this.ossAnalysisMapRepository.findAllByVersion_Id(id);

        for(OssAnalysisMap ossAnalysisMap :  analysisMapList){
            List<AnalysisRestrictionMap> restrictionMapList = this.analysisRestrictionMapRepository.findAllByAnalysisMap_Id(ossAnalysisMap.getId());
            for (AnalysisRestrictionMap restrictionMap : restrictionMapList){
                this.analysisRestrictionMapRepository.delete(restrictionMap);
            }
            this.ossAnalysisMapRepository.delete(ossAnalysisMap);
        }

        this.versionRepository.delete(version);

        //project에 version이 있는 지 확인
        Project project = this.projectRepository.findProjectById(version.getProject().getId()).orElseThrow(ProjectNotFoundException::new);
        if(this.projectIsEmpty(project)) {
            this.updateProjectStatus(project, "C");
        }


    }

    private void updateProjectStatus(Project project, String projectStatus){
        project.setProjectStatus(projectStatus);
        this.projectRepository.save(project);
    }

    private OssAnalysisDto makeOssLicenseAnalysisDto(PartOfOssAnalysis analysis, Version version, Optional<OssLicense> license){
        OssAnalysisDto analysisDto = modelMapper.map(analysis, OssAnalysisDto.class);
        analysisDto.setVersion(version);

        if(license.isPresent()){
            analysisDto.setLicenseName(license.get().getLicenseName());
            analysisDto.setLicenseUrl(license.get().getLicenseUrl());
            analysisDto.setLicenseTypeName(license.get().getOssLicenseType().getLicenseTypeName());
        }

        return analysisDto;
    }

    private boolean projectIsEmpty(Project project){
        List<Version> versionList = this.versionRepository.findAllByProject(project);
        if (versionList.isEmpty())
            return true;
        return false;
        }

}
