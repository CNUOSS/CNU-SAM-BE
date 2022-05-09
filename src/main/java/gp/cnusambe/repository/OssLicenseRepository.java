package gp.cnusambe.repository;

import gp.cnusambe.domain.OssLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OssLicenseRepository extends JpaRepository<OssLicense,Long> {

}
