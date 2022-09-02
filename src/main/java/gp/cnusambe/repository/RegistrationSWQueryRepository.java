package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.RegistrationSW;
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
public class RegistrationSWQueryRepository {
    private final RegistrationSWRepository registrationSWRepository;

    public Page<RegistrationSW> findAllBy(String swManufacturer, String swName, Pageable pageable) {
        Specification<RegistrationSW> spec
                = Specification.where(equalData("isManaged", true)
                .and(likeData("swName", swName))
                .and(equalData("swManufacturer", swManufacturer)));
        return registrationSWRepository.findAll(spec, pageable);
    }

    private Specification<RegistrationSW> likeData(String dataType, String data) {
        return new Specification<RegistrationSW>() {
            @Override
            public Predicate toPredicate(Root<RegistrationSW> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(dataType), "%" + data + "%");
            }
        };
    }

    private Specification<RegistrationSW> equalData(String dataType, String data) {
        return new Specification<RegistrationSW>() {
            @Override
            public Predicate toPredicate(Root<RegistrationSW> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(dataType), data);
            }
        };
    }

    private Specification<RegistrationSW> equalData(String dataType, boolean data) {
        return new Specification<RegistrationSW>() {
            @Override
            public Predicate toPredicate(Root<RegistrationSW> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(dataType), data);
            }
        };
    }
}
