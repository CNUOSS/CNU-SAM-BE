package gp.cnusambe.service;

import gp.cnusambe.domain.*;
import gp.cnusambe.dto.ProjectDto;
import gp.cnusambe.payload.request.ProjectPostRequest;
import gp.cnusambe.dto.ProjectListDto;
import gp.cnusambe.payload.response.ProjectPostResponse;
import gp.cnusambe.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectQueryRepository projectQueryRepository;
    private final OssLicenseRepository ossLicenseRepository;
    private final ProjectCategoryRepository projectCategoryRepository;
    private final UserRepository userRepository;
    private final VersionRepository versionRepository;

    private final ModelMapper strictMapper;

    public ProjectPostResponse create(ProjectPostRequest request){
        //Request -> Entity
        Optional<OssLicense> license = this.ossLicenseRepository.findById(request.getOssLicenseId());
        Optional<User> user = this.userRepository.findByUserId(request.getUserId());
        Optional<ProjectCategory> projectCategory = this.projectCategoryRepository.findById(request.getProjectCategoryName());
        Project project = this.makeProjectEntity(request, license.get(),user.get(),projectCategory.get());

        //Save Project
        Project newProject = this.projectRepository.save(project);

        //Entity -> Response
        ProjectPostResponse response = newProject.makeProjectResponse();

        return response;
    }

    public Page<ProjectListDto> getProjectList(String projectName, String userId, String category, Long licenseId, Pageable pageable){
        //Repository -> Entity : Page<Project>
        Page<Project> project;
        if(projectName.length() == 0 && userId.length() == 0 && category.length() == 0 && licenseId == -1L){
            project = this.projectRepository.findAll(pageable);
        }else{
            project = this.projectQueryRepository.findAllBy(projectName,userId,category,licenseId,pageable);
        }

        //Entity -> Dto
        List<ProjectListDto> projectListDtoList = new ProjectListDto().makeProjectListDto(project);
        return new PageImpl<>(projectListDtoList, pageable, project.getTotalElements());
    }

    public ProjectDto getProjectDetail(Long id){
        Optional<Project> project = this.projectRepository.findProjectById(id);

        List<Version> versionList = this.versionRepository.findAllByProject(project.get());

        ProjectDto projectDto = project.get().makeProjectDto(versionList);
        return projectDto;
    }

    public void deleteProject(Long id){
        Optional<Project> project = this.projectRepository.findProjectById(id);
        List<Version> versionList = this.versionRepository.findAllByProject(project.get());

        for(Version version : versionList){
            this.versionRepository.delete(version);
        }

        this.projectRepository.delete(project.get());
    }

    public Project getProject(Long id){
        Optional<Project> project = this.projectRepository.findProjectById(id);
        return project.get();
    }
    private Project makeProjectEntity(ProjectPostRequest request, OssLicense license, User user, ProjectCategory category){
        Project project = strictMapper.map(request, Project.class);

        project.setProjectStatus("C");
        project.setLicense(license);
        project.setUser(user);
        project.setProjectCategory(category);

        return project;
    }



}
