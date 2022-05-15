package gp.cnusambe.repository;

import gp.cnusambe.domain.OssLicense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OssLicenseRepository extends JpaRepository<OssLicense,Long> {

    Page<OssLicense> findAll(Pageable pageable);
    Page<OssLicense> findByLicenseNameContaining(String keyword, Pageable pageable);
    Page<OssLicense> findByOssLicenseType_LicenseTypeName(String keyword,Pageable pageable);
    Page<OssLicense> findByLicenseNameIn(Set<String> ossLicensesList,Pageable pageable);
    Optional<OssLicense> findOssLicenseById(Long id);
    Optional<OssLicense> findById(Long id);
    void deleteOssLicenseById(Long id);

}
