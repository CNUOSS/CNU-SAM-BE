package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.OssLicenseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OssLicenseTypeRepository extends JpaRepository<OssLicenseType,String> {
    List<OssLicenseType> findAll();
}
