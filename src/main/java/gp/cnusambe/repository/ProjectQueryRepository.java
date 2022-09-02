package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
@RequiredArgsConstructor
public class ProjectQueryRepository {
    private final ProjectRepository projectRepository;

    public Page<Project> findAllBy(String projectName, String userId, String category, Long licenseId, Pageable pageable){
        Specification<Project> specification = Specification.where(
                likeData("projectName",projectName))
                .and(likeData("user", "userId", userId))
                .and(likeData("projectCategory", "projectCategoryName", category));

        if (licenseId != -1L){
            specification = specification.and(equalData("license","id", licenseId));
        }

        return projectRepository.findAll(specification,pageable);
    }

    private static Specification<Project> likeData(String dataType, String data){
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(dataType), "%" + data + "%");
            }
        };
    }
    private static Specification<Project> likeData(String dataType, String dataAttribute, String data){
        //for foreign key
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(dataType).get(dataAttribute), "%" + data + "%");
            }
        };
    }

    private static Specification<Project> equalData(String dataType, String dataAttribute, Long data){
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(dataType).get(dataAttribute), data);
            }
        };
    }

}
