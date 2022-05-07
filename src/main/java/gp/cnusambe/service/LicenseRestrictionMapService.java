package gp.cnusambe.service;

import gp.cnusambe.domain.LicenseRestrictionMap;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.Restriction;
import gp.cnusambe.repository.LicenseRestrictionMapRepository;
import gp.cnusambe.repository.OssLicenseRepository;
import gp.cnusambe.repository.RestrictionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LicenseRestrictionMapService {
    private final OssLicenseRepository ossLicenseRepository;
    private final LicenseRestrictionMapRepository licenseRestrictionMapRepository;
    private final RestrictionRepository restrictionRepository;

    public LicenseRestrictionMapService(OssLicenseRepository ossLicenseRepository, LicenseRestrictionMapRepository licenseRestrictionMapRepository, RestrictionRepository restrictionRepository){
        this.licenseRestrictionMapRepository = licenseRestrictionMapRepository;
        this.ossLicenseRepository = ossLicenseRepository;
        this.restrictionRepository = restrictionRepository;
    }

    public LicenseRestrictionMap create(OssLicense license, Restriction req_restriction){
        Optional<Restriction> restriction = this.restrictionRepository.findById(req_restriction.getRestrictionName());
        LicenseRestrictionMap map = new LicenseRestrictionMap(license,restriction.get());
        LicenseRestrictionMap licenseRestrictionMap = this.licenseRestrictionMapRepository.save(map);

        return licenseRestrictionMap;
    }

}
