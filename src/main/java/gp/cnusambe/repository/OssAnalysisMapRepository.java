package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.OssAnalysisMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OssAnalysisMapRepository extends JpaRepository<OssAnalysisMap,Long> {
    List<OssAnalysisMap> findAllByVersion_Id(Long id);
}
