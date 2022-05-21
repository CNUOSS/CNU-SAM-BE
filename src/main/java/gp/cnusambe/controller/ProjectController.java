package gp.cnusambe.controller;

import gp.cnusambe.dto.ProjectDto;
import gp.cnusambe.payload.request.ProjectPostRequest;
import gp.cnusambe.payload.response.ProjectDetailResponse;
import gp.cnusambe.payload.response.ProjectPostResponse;
import gp.cnusambe.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/projects")
    public ResponseEntity<ProjectPostResponse> post(@Valid @RequestBody ProjectPostRequest request){
        ProjectPostResponse response = this.projectService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    }

}
