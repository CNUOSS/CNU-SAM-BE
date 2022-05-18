package gp.cnusambe.controller;

import gp.cnusambe.domain.Project;
import gp.cnusambe.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;


    @PostMapping("/projects")
    public ResponseEntity<Project> post(){
        return new ResponseEntity<>(null);
    }

}
