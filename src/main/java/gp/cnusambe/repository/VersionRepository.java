package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.Project;
import gp.cnusambe.repository.domain.Version;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VersionRepository extends JpaRepository<Version, Long> {
    List<Version> findAllByProject(Project project);
    List<Version> findAllByProjectOrderByCreateDateDesc(Project project);
}
