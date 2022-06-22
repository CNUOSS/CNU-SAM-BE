package gp.cnusambe.service;

import gp.cnusambe.domain.LicenseRestrictionMap;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.Restriction;
import gp.cnusambe.repository.LicenseRestrictionMapRepository;
import gp.cnusambe.repository.RestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RestrictionService {
    private final RestrictionRepository restrictionRepository;
    private final LicenseRestrictionMapRepository licenseRestrictionMapRepository;


    //TODO: NULL 처리
    public List<Restriction> getRestrictionData() {
        List<Restriction> restrictionList = this.restrictionRepository.findAll();
        return restrictionList;
    }

    public List<Restriction> getRestrictionByOssLicense(OssLicense ossLicense){
        List<LicenseRestrictionMap> restrictionMapList = this.licenseRestrictionMapRepository.findByOssLicense(ossLicense);
        List<Restriction> restrictionList = restrictionMapList.stream().map(r -> r.getRestriction()).collect(Collectors.toList());

        return restrictionList;
    }
}
