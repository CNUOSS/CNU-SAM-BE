package gp.cnusambe.repository;

import gp.cnusambe.domain.RegistrationSW;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

public interface RegistrationSWRepository extends JpaRepository<RegistrationSW, Long>, JpaSpecificationExecutor<RegistrationSW> {
    Optional<RegistrationSW> findById(Long swId);
    Optional<RegistrationSW> findBySwManufacturerAndSwNameAndIsManaged(String swManufacturer, String swName, boolean isManaged);
    Page<RegistrationSW> findAllByIsManaged(boolean isManaged, Pageable pageable);
    Optional<RegistrationSW> findByIdAndIsManaged(Long swId, boolean isManaged);
    Page<RegistrationSW> findAll(Specification spec, Pageable pageable);
}