package gp.cnusambe.repository;

import gp.cnusambe.domain.SubscriptionSW;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SwSubscriptionQueryRepository {
    private final SubscriptionSWRepository subscriptionSWRepository;

    public Page<SubscriptionSW> findAllBy(String swType, String swManufacturer, String swName, Pageable pageable) {
        Optional<String> swType_ = Optional.ofNullable(swType);
        Optional<String> swManufacturer_ = Optional.ofNullable(swManufacturer);
        Optional<String> swName_ = Optional.ofNullable(swName);

        Specification<SubscriptionSW> spec
                = Specification.where(likeData("swType", swType_.orElse("")))
                .and(likeData("swManufacturer", swManufacturer_.orElse("")))
                .and(likeData("swName", swName_.orElse("")));

        return subscriptionSWRepository.findAll(spec, pageable);
    }

    static private Specification<SubscriptionSW> likeData(String dataType, String data) {
        return new Specification<SubscriptionSW>() {
            @Override
            public Predicate toPredicate(Root<SubscriptionSW> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(dataType), "%" + data + "%");
            }
        };
    }
}
