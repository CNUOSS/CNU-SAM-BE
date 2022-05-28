package gp.cnusambe.controller;

import gp.cnusambe.domain.*;
import gp.cnusambe.dto.OssAnalysisDto;
import gp.cnusambe.dto.VersionDto;
import gp.cnusambe.payload.request.VersionPostRequest;
import gp.cnusambe.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class VersionController {
    private final ProjectService projectService;
    private final VersionService versionService;
    private final OssLicenseService ossLicenseService;
    private final OssAnalysisMapService ossAnalysisMapService;
    private final RestrictionService restrictionService;
    private final AnalysisRestrictionMapService analysisRestrictionMapService;

    private final ModelMapper modelMapper;

    @PostMapping("/versions")
    public ResponseEntity post(@Valid @RequestBody VersionPostRequest request){
        List<VersionPostRequest.OssAnalysisRequest> ossAnalysisRequests = request.getOssAnalysis();

        // Version에 해당되는 project 찾기
        Optional<Project> project = this.projectService.getProject(request.getProjectId());

        // request -> dto
        VersionDto versionDto = this.makeVersionDto(project.get(), request);
        Version newVersion = this.versionService.create(versionDto);

        List<AnalysisRestrictionMap> response = new ArrayList<>();
        for(VersionPostRequest.OssAnalysisRequest ossAnalysis : ossAnalysisRequests){
            // Anaysis에 해당되는 license,restriction 찾기
            Optional<OssLicense> ossLicense = this.ossLicenseService.getOssLicense(ossAnalysis.getLicenseId());
            List<Restriction> restrictionList = this.restrictionService.getRestrictionByOssLicense(ossLicense.get());

            //OssAnalysisRequest -> AnalysisDto
            OssAnalysisDto analysisDto = this.makeOssLicenseAnalysisDto(ossAnalysis, newVersion, ossLicense.get());

            //Save OssAnalysis
            OssAnalysisMap newAnalysis = this.ossAnalysisMapService.create(analysisDto);

            //Save AnalysisRestrictionMap
            List<AnalysisRestrictionMap> newRestrictionMap = this.analysisRestrictionMapService.create(restrictionList,newAnalysis);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    private VersionDto makeVersionDto(Project project, VersionPostRequest request){
        VersionDto versionDto = modelMapper.map(request, VersionDto.class);
        versionDto.setProject(project);
        return versionDto;
    }

    private OssAnalysisDto makeOssLicenseAnalysisDto(VersionPostRequest.OssAnalysisRequest request, Version version, OssLicense license){
        OssAnalysisDto analysisDto = modelMapper.map(request, OssAnalysisDto.class);
        analysisDto.setVersion(version);
        analysisDto.setLicenseName(license.getLicenseName());
        analysisDto.setLicenseUrl(license.getLicenseUrl());
        analysisDto.setLicenseTypeName(license.getOssLicenseType().getLicenseTypeName());

        return analysisDto;
    }

}
