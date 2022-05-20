package gp.cnusambe.repository;

import gp.cnusambe.domain.SubscriptionSW;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SubscriptionSWRepository extends JpaRepository<SubscriptionSW, Long> {
    Optional<SubscriptionSW> findById(Long swId);
}
