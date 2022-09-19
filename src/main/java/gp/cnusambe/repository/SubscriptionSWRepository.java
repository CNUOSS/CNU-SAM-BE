package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.SubscriptionSW;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public interface SubscriptionSWRepository extends JpaRepository<SubscriptionSW, Long>, JpaSpecificationExecutor<SubscriptionSW>  {
    Optional<SubscriptionSW> findById(Long swId);

    Boolean existsBySwManufacturerAndSwNameAndLicense(String swManufacturer, String swName, String license);

    Page<SubscriptionSW> findAll(Specification spec, Pageable pageable);

    public default Page<SubscriptionSW> findAllBy(String swType, String swManufacturer, String swName, Pageable pageable) {
        Specification<SubscriptionSW> spec
                = Specification.where(likeData("swType", swType)
                .and(likeData("swManufacturer", swManufacturer))
                .and(likeData("swName", swName)));
        return findAll(spec, pageable);
    }

    private Specification<SubscriptionSW> likeData(String dataType, String data) {
        return new Specification<SubscriptionSW>() {
            @Override
            public Predicate toPredicate(Root<SubscriptionSW> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(dataType), "%" + data + "%");
            }
        };
    }
}
