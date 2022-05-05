package gp.cnusambe.service;

import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.OssLicenseType;
import gp.cnusambe.domain.Restriction;
import gp.cnusambe.repository.OssLicenseRepository;
import gp.cnusambe.repository.OssLicenseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OssLicenseService {
    private final OssLicenseRepository ossLicenseRepository;
    private final OssLicenseTypeRepository ossLicenseTypeRepository;

    @Autowired
    public OssLicenseService(OssLicenseRepository ossLicenseRepository, OssLicenseTypeRepository ossLicenseTypeRepository){
        this.ossLicenseRepository=ossLicenseRepository;
        this.ossLicenseTypeRepository = ossLicenseTypeRepository;
    }

    public OssLicense create(OssLicense license){
        Optional<OssLicenseType> licenseType = this.ossLicenseTypeRepository.findById(license.getOssLicenseType().getLicenseTypeName());
        license.setOssLicenseType(licenseType.get());
        OssLicense new_license = this.ossLicenseRepository.save(license);
        return new_license;
    }
}
