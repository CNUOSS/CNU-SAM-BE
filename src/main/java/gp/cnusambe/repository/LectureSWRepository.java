package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.LectureSW;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface LectureSWRepository extends JpaRepository<LectureSW, Long>, JpaSpecificationExecutor<LectureSW> {
    Page<LectureSW> findAll(Specification<LectureSW> spec, Pageable pageable);
    List<LectureSW> findAllByYear(String year);
    boolean existsByYearAndSemesterAndLectureNum(String year, String semester, String lectureNum);

    public default Page<LectureSW> findAllBy(String department, String year, String lectureType, String semester, String lectureName, String lectureNum, String owner, Pageable pageable) {
        Specification<LectureSW> spec
                = Specification.where(likeData("department", department)
                        .and(likeData("year", year))
                        .and(likeData("lectureType", lectureType)))
                .and(likeData("semester", semester))
                .and(likeData("lectureName", lectureName))
                .and(likeData("lectureNum", lectureNum))
                .and(likeData("ownerId", owner));
        return findAll(spec, pageable);
    }

    private Specification<LectureSW> likeData(String dataType, String data) {
        return new Specification<LectureSW>() {
            @Override
            public Predicate toPredicate(Root<LectureSW> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(dataType), "%" + data + "%");
            }
        };
    }
}
