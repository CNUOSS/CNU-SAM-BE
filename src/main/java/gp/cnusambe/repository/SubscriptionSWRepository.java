package gp.cnusambe.repository;

import gp.cnusambe.domain.SubscriptionSW;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

public interface SubscriptionSWRepository extends JpaRepository<SubscriptionSW, Long>, JpaSpecificationExecutor<SubscriptionSW>  {
    Optional<SubscriptionSW> findById(Long swId);
    Page<SubscriptionSW> findAll(Specification spec, Pageable pageable);
}
