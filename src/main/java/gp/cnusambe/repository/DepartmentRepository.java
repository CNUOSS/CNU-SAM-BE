package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    List<Department> findAll();
}
