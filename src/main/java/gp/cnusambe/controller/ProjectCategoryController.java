package gp.cnusambe.controller;

import gp.cnusambe.repository.domain.ProjectCategory;
import gp.cnusambe.service.ProjectCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectCategoryController {
    private final ProjectCategoryService projectCategoryService;

    @GetMapping("/category-names")
    public ResponseEntity<List<ProjectCategory>> getCategoryNameData(){
        List<ProjectCategory> categoryList = this.projectCategoryService.getProjectCategory();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
