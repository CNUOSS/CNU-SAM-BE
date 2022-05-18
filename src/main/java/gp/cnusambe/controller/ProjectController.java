package gp.cnusambe.controller;

import gp.cnusambe.domain.Project;
import gp.cnusambe.dto.ProjectDto;
import gp.cnusambe.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/projects")
    public ResponseEntity<ProjectDto> post(@Valid @RequestBody ProjectDto projectDto){
        ProjectDto newProject = this.projectService.create(projectDto);
        return new ResponseEntity<>(newProject, HttpStatus.OK);
    }

}
