package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.LectureType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LectureTypeRepository extends JpaRepository<LectureType, String> {
    List<LectureType> findAll();
}
