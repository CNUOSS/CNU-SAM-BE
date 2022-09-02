package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long>, JpaSpecificationExecutor<Project> {
    List<Project> findAll();
    Optional<Project> findProjectById(Long id);
    Page<Project> findAll(Specification specification, Pageable pageable);
}