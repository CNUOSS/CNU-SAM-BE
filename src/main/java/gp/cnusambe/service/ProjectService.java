package gp.cnusambe.service;

import gp.cnusambe.domain.*;
import gp.cnusambe.dto.ProjectDto;
import gp.cnusambe.payload.request.ProjectPostRequest;
import gp.cnusambe.payload.response.ProjectPostResponse;
import gp.cnusambe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final OssLicenseRepository ossLicenseRepository;
    private final ProjectCategoryRepository projectCategoryRepository;
    private final UserRepository userRepository;
    private final VersionRepository versionRepository;

    public ProjectPostResponse create(ProjectPostRequest request){
        //Request -> Entity
        Optional<OssLicense> license = this.ossLicenseRepository.findById(request.getOssLicenseId());
        Optional<User> user = this.userRepository.findByUserId(request.getUserId());
        Optional<ProjectCategory> projectCategory = this.projectCategoryRepository.findById(request.getProjectCategoryName());
        Project project = request.makeProjectEntity(license.get(),user.get(),projectCategory.get());

        //Save Project
        Project newProject = this.projectRepository.save(project);

        //Entity -> Response
        ProjectPostResponse response = newProject.makeProjectResponse();

        return response;
    }

    }




}
