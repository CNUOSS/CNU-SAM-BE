package gp.cnusambe.service;

import gp.cnusambe.repository.domain.ProjectCategory;
import gp.cnusambe.repository.ProjectCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectCategoryService {
    private final ProjectCategoryRepository projectCategoryRepository;

    public List<ProjectCategory> getProjectCategory(){
        List<ProjectCategory> categoryList = this.projectCategoryRepository.findAll();
        return categoryList;
    }
}
