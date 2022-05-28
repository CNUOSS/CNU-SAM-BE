package gp.cnusambe.controller;

import gp.cnusambe.domain.Project;
import gp.cnusambe.dto.PageInfoDto;
import gp.cnusambe.dto.ProjectDto;
import gp.cnusambe.payload.request.ProjectPostRequest;
import gp.cnusambe.payload.response.ProjectDetailResponse;
import gp.cnusambe.dto.ProjectListDto;
import gp.cnusambe.payload.response.ProjectListResponse;
import gp.cnusambe.payload.response.ProjectPostResponse;
import gp.cnusambe.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;


    @PostMapping("/projects")
    public ResponseEntity<ProjectPostResponse> post(@Valid @RequestBody ProjectPostRequest request){
        ProjectPostResponse response = this.projectService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/projects/search")
    public ResponseEntity<ProjectListResponse> getProjectList(
            @RequestParam(value = "pj-name", required = false) String projectNameKeyword,
            @RequestParam(value = "user", required = false) String userIdKeyword,
            @RequestParam(value = "category", required = false) String requestCategory,
            @RequestParam(value = "lc-id", required = false) Long requestLicenseId,
            @PageableDefault(size = 9, sort = "updateDate",direction = Sort.Direction.DESC) Pageable pageable){
        String projectName = Optional.ofNullable(projectNameKeyword).orElse("");
        String userId = Optional.ofNullable(userIdKeyword).orElse("");
        String category = Optional.ofNullable(requestCategory).orElse("");
        Long licenseId = Optional.ofNullable(requestLicenseId).orElse(-1L);

        //Service -> Dto
        Page<ProjectListDto> projectList = projectService.getProjectList(projectName,userId,category,licenseId,pageable);
        ProjectListResponse response = new ProjectListResponse(
                new PageInfoDto(projectList.getTotalElements(),projectList.isLast(),projectList.getTotalPages(),projectList.getSize())
                ,projectList.getContent());

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
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
