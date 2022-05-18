package gp.cnusambe.service;

import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.Project;
import gp.cnusambe.domain.ProjectCategory;
import gp.cnusambe.domain.User;
import gp.cnusambe.dto.ProjectDto;
import gp.cnusambe.repository.OssLicenseRepository;
import gp.cnusambe.repository.ProjectCategoryRepository;
import gp.cnusambe.repository.ProjectRepository;
import gp.cnusambe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final OssLicenseRepository ossLicenseRepository;
    private final ProjectCategoryRepository projectCategoryRepository;
    private final UserRepository userRepository;

    public ProjectDto create(ProjectDto projectDto){
        //Dto -> Entity
        Optional<OssLicense> license = this.ossLicenseRepository.findById(projectDto.getOssLicenseId());
        Optional<User> user = this.userRepository.findByUserId(projectDto.getUserId());
        Optional<ProjectCategory> projectCategory = this.projectCategoryRepository.findById(projectDto.getProjectCategoryName());
        Project project = projectDto.makeProjectEntity(license.get(),user.get(),projectCategory.get());

        //Save Project
        Project newProject = this.projectRepository.save(project);

        //Entity -> Dto
        ProjectDto newProjectDto = newProject.makeProjectDto();

        return newProjectDto;
    }




}
