package gp.cnusambe.repository;

import gp.cnusambe.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findAll();
    Optional<Project> findProjectById(Long id);
}
