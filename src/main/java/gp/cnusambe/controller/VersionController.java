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

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class VersionController {
    private final ProjectService projectService;
    private final VersionService versionService;

    private final ModelMapper modelMapper;

    @PostMapping("/versions")
    public ResponseEntity post(@Valid @RequestBody VersionPostRequest request){
        List<PartOfOssAnalysis> ossAnalysisRequests = request.getOssAnalysis();

        // Version에 해당되는 project 찾기
        Project project = this.projectService.getProject(request.getProjectId());

        // request -> dto
        VersionDto versionDto = this.makeVersionDto(project, request);
        Version newVersion = this.versionService.create(versionDto, ossAnalysisRequests);

        return new ResponseEntity(HttpStatus.OK);
    }

    private VersionDto makeVersionDto(Project project, VersionPostRequest request){
        VersionDto versionDto = modelMapper.map(request, VersionDto.class);
        versionDto.setProject(project);
        return versionDto;
    }

}
