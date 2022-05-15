package gp.cnusambe.repository;

import gp.cnusambe.domain.LicenseRestrictionMap;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.Restriction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenseRestrictionMapRepository extends JpaRepository<LicenseRestrictionMap,Long> {

    List<LicenseRestrictionMap> findByOssLicense(OssLicense license);
    List<LicenseRestrictionMap> findByRestriction_RestrictionName(String restrictionName);
    void delete(LicenseRestrictionMap map);

}
