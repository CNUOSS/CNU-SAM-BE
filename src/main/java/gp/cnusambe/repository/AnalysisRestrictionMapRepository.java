package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.AnalysisRestrictionMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalysisRestrictionMapRepository extends JpaRepository<AnalysisRestrictionMap, Long> {
    List<AnalysisRestrictionMap> findAllByAnalysisMap_Id(Long id);
}
