package gp.cnusambe.controller;

import gp.cnusambe.dto.ProjectDto;
import gp.cnusambe.payload.request.ProjectPostRequest;
import gp.cnusambe.payload.response.ProjectDetailResponse;
import gp.cnusambe.payload.response.ProjectPostResponse;
import gp.cnusambe.payload.response.VersionResponse;
import gp.cnusambe.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/projects")
    public ResponseEntity<ProjectPostResponse> post(@Valid @RequestBody ProjectPostRequest request){
        ProjectPostResponse response = this.projectService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/projects/{project_id}")
    public ResponseEntity<ProjectDetailResponse> getProjectDetail(@PathVariable("project_id") int id){
        ProjectDto projectDto = this.projectService.getProjectDetail((long) id);
        ProjectDetailResponse response = makeProjectDetailResponse(projectDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ProjectDetailResponse makeProjectDetailResponse(ProjectDto projectDto){
        ProjectDetailResponse response = modelMapper.map(projectDto, ProjectDetailResponse.class);

        List<VersionResponse> versionResponses = projectDto.getVersion().stream().map(v -> modelMapper.map(v, VersionResponse.class)).collect(Collectors.toList());

        response.setVersion(versionResponses);
        response.setUser_id(projectDto.getUser().getUserId());
        response.setOss_license_name(projectDto.getLicense().getLicenseName());

        return response;
    }

}
