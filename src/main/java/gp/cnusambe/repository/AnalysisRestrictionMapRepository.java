package gp.cnusambe.repository;

import gp.cnusambe.domain.AnalysisRestrictionMap;
import gp.cnusambe.domain.OssAnalysisMap;
import gp.cnusambe.domain.Restriction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisRestrictionMapRepository extends JpaRepository<AnalysisRestrictionMap, Long> {
}
