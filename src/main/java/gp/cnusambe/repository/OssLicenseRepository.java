package gp.cnusambe.repository;

import gp.cnusambe.domain.OssLicense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OssLicenseRepository extends JpaRepository<OssLicense,Long> {

    Page<OssLicense> findAll(Pageable pageable);
    List<OssLicense> findByLicenseNameContaining(String keyword, Pageable pageable);
    List<OssLicense> findByOssLicenseType_LicenseTypeName(String keyword,Pageable pageable);
    List<OssLicense> findByLicenseNameIn(Set<String> ossLicensesList,Pageable pageable);

}
