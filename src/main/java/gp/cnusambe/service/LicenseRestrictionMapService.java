package gp.cnusambe.service;

import gp.cnusambe.repository.LicenseRestrictionMapRepository;
import gp.cnusambe.repository.OssLicenseRepository;
import gp.cnusambe.repository.RestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LicenseRestrictionMapService {
    private final OssLicenseRepository ossLicenseRepository;
    private final LicenseRestrictionMapRepository licenseRestrictionMapRepository;
    private final RestrictionRepository restrictionRepository;


}
