package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.LectureSW;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureSWQueryRepository {
    private final LectureSWRepository lectureSWRepository;

    public List<LectureSW> findAllBy(String department, String year, String lectureType, String semester, String lectureName, String lectureNum, String owner) {
        Specification<LectureSW> spec
                = Specification.where(likeData("department", department)
                .and(likeData("year", year))
                .and(likeData("lectureType", lectureType)))
                .and(likeData("semester", semester))
                .and(likeData("lectureName", lectureName))
                .and(likeData("lectureNum", lectureNum))
                .and(likeData("ownerId", owner));
        return lectureSWRepository.findAll(spec);
    }

    private Specification<LectureSW> likeData(String dataType, String data) {
        return new Specification<LectureSW>() {
            @Override
            public Predicate toPredicate(Root<LectureSW> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(dataType), "%" + data + "%");
            }
        };
    }

    private Specification<LectureSW> equalData(String dataType, String data) {
        return new Specification<LectureSW>() {
            @Override
            public Predicate toPredicate(Root<LectureSW> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(dataType), data);
            }
        };
    }
}
