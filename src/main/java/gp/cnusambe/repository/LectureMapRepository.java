package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.LectureMap;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface LectureMapRepository extends JpaRepository<LectureMap, Long> {
    List<LectureMap> findAllByLectureSWId(Long lectureSWId);
    List<LectureMap> findAllByLectureSWIdIn(List<Long> lectureSWId);
    @Transactional
    void deleteByLectureSWId(Long lectureSWId);
}
