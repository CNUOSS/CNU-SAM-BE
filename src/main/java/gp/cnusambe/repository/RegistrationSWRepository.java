package gp.cnusambe.repository;

import gp.cnusambe.domain.RegistrationSW;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegistrationSWRepository extends JpaRepository<RegistrationSW, Long>{
    Optional<RegistrationSW> findById(Long swId);
}