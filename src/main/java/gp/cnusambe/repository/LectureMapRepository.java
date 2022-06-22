package gp.cnusambe.repository;

import gp.cnusambe.domain.LectureMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface LectureMapRepository extends JpaRepository<LectureMap, Long> {
    List<LectureMap> findAllByLectureSWId(Long lectureSWId);
    Page<LectureMap> findAllByLectureSWIdIn(List<Long> lectureSWId, Pageable pageable);
    List<LectureMap> findAllByLectureSWIdIn(List<Long> lectureSWId);
    @Transactional
    void deleteByLectureSWId(Long lectureSWId);
}
