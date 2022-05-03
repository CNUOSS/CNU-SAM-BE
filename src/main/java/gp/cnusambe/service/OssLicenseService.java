package gp.cnusambe.service;

import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.repository.OssLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OssLicenseService {
    private final OssLicenseRepository ossLicenseRepository;

    @Autowired
    public OssLicenseService(OssLicenseRepository ossLicenseRepository){
        this.ossLicenseRepository=ossLicenseRepository;
    }

    public OssLicense create(OssLicense license){
        OssLicense new_license = ossLicenseRepository.save(license);
        return new_license;
    }
}
