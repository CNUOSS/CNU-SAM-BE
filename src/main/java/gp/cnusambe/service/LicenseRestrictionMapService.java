package gp.cnusambe.service;

import gp.cnusambe.domain.LicenseRestrictionMap;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.repository.LicenseRestrictionMapRepository;
import gp.cnusambe.repository.OssLicenseRepository;
import gp.cnusambe.repository.RestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LicenseRestrictionMapService {
    private final OssLicenseRepository ossLicenseRepository;
    private final LicenseRestrictionMapRepository licenseRestrictionMapRepository;
    private final RestrictionRepository restrictionRepository;

    public List<LicenseRestrictionMap> getLicenseRestrictionMap(OssLicense license){
        return this.licenseRestrictionMapRepository.findByOssLicense(license);
    }



}
