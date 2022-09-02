package gp.cnusambe.controller;

import gp.cnusambe.domain.*;
import gp.cnusambe.repository.domain.PartOfOssAnalysis;
import gp.cnusambe.repository.domain.Project;
import gp.cnusambe.repository.domain.Version;
import gp.cnusambe.service.dto.LicenseProtectorDto;
import gp.cnusambe.service.dto.VersionDto;
import gp.cnusambe.controller.payload.request.VersionPostRequest;
import gp.cnusambe.controller.payload.response.LicenseProtectorResponse;
import gp.cnusambe.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class VersionController {
    private final ProjectService projectService;
    private final VersionService versionService;

    private final ModelMapper modelMapper;

    @GetMapping("/projects/{project_id}/versions/{version_id}/license-protector")
    public ResponseEntity<LicenseProtectorResponse> getLicenseProtector(@PathVariable("project_id")Long projectId, @PathVariable("version_id")Long versionId){
        LicenseProtectorDto licenseProtectorDto = this.versionService.getLicenseProtector(versionId);
        LicenseProtectorResponse response = this.makeLicenseProtectorResponse(licenseProtectorDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //TODO : PathVariable, Body에  있는 project_id 동일한지 검사
    @PostMapping("/projects/{project_id}/versions")
    public ResponseEntity post(@PathVariable("project_id")Long projectId,@Valid @RequestBody VersionPostRequest request){
        List<PartOfOssAnalysis> ossAnalysisRequests = request.getOssAnalysis();

        // Version에 해당되는 project 찾기
        Project project = this.projectService.getProject(request.getProjectId());

        // request -> dto
        VersionDto versionDto = this.makeVersionDto(project, request);
        Version newVersion = this.versionService.create(versionDto, ossAnalysisRequests);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/projects/{project_id}/versions/{version_id}")
    public ResponseEntity delete(@PathVariable("project_id")Long projectId,@PathVariable("version_id") Long id){
        this.versionService.deleteVersion(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private VersionDto makeVersionDto(Project project, VersionPostRequest request){
        VersionDto versionDto = modelMapper.map(request, VersionDto.class);
        versionDto.setProject(project);
        return versionDto;
    }

    private LicenseProtectorResponse makeLicenseProtectorResponse(LicenseProtectorDto licenseProtectorDto){
        LicenseProtectorResponse protectorResponse = modelMapper.map(licenseProtectorDto, LicenseProtectorResponse.class);
        protectorResponse.setOssAnalysis(licenseProtectorDto.getOssAnalysisDto());
        return protectorResponse;
    }

}
