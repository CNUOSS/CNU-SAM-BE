package gp.cnusambe.repository;

import gp.cnusambe.domain.LectureSW;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface LectureSWRepository extends JpaRepository<LectureSW, Long>, JpaSpecificationExecutor<LectureSW> {
    List<LectureSW> findAll();
    List<LectureSW> findAll(Specification<LectureSW> spec);
    List<LectureSW> findAllByYear(String year);
}
