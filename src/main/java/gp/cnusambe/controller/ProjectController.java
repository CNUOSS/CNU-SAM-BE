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


    @PostMapping("/projects")
    public ResponseEntity<ProjectPostResponse> post(@Valid @RequestBody ProjectPostRequest request){
        ProjectPostResponse response = this.projectService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/projects/{project_id}")
    public ResponseEntity<ProjectDetailResponse> getProjectDetail(@PathVariable("project_id") Long id){
        ProjectDto projectDto = this.projectService.getProjectDetail(id);
        ProjectDetailResponse response = projectDto.makeProjectDetailResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/projects/{project_id}")
    public ResponseEntity delete(@PathVariable("project_id") Long id){
        this.projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
