package gp.cnusambe.service;

import gp.cnusambe.domain.LicenseRestrictionMap;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.OssLicenseType;
import gp.cnusambe.domain.Restriction;
import gp.cnusambe.dto.OssLicenseDto;
import gp.cnusambe.repository.LicenseRestrictionMapRepository;
import gp.cnusambe.repository.OssLicenseRepository;
import gp.cnusambe.repository.OssLicenseTypeRepository;
import gp.cnusambe.repository.RestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OssLicenseService {
    private final OssLicenseRepository ossLicenseRepository;
    private final OssLicenseTypeRepository ossLicenseTypeRepository;
    private final RestrictionRepository restrictionRepository;
    private final LicenseRestrictionMapRepository licenseRestrictionMapRepository;


    public OssLicenseDto create(OssLicenseDto licenseDto){
        //Dto -> Entity
        OssLicense license = licenseDto.makeOssLicenseEntity(licenseDto);
        List<Restriction> restrictionList = licenseDto.makeRestrictionEntity();

        // TODO : restriction save 안되면 license도 생성 못하게 막아야함 (service 계층에서 transaction으로 막기)

        //Save OssLicense
        Optional<OssLicenseType> licenseType = this.ossLicenseTypeRepository.findById(license.getOssLicenseType().getLicenseTypeName());
        license.setOssLicenseType(licenseType.get());
        OssLicense new_license = this.ossLicenseRepository.save(license);

        //Save Restriction
        List<Restriction> newRestrictionList = new ArrayList<>();
        for(int index = 0; index < restrictionList.size(); index++){
            Optional<Restriction> restriction = this.restrictionRepository.findById(restrictionList.get(index).getRestrictionName());
            LicenseRestrictionMap newMap = this.licenseRestrictionMapRepository.save(new LicenseRestrictionMap(license,restriction.get()));
            if (newMap != null){
                newRestrictionList.add(restriction.get());
            }
            else{
                //TODO : 예외처리
            }
        }

        //Entity -> Dto
        OssLicenseDto newLicenseDto = makeOssLicenseDto(new_license,newRestrictionList);

        return newLicenseDto;
    }

    public OssLicenseDto makeOssLicenseDto(OssLicense license, List<Restriction> restriction){
        return OssLicenseDto.builder()
                .id(license.getId())
                .licenseName(license.getLicenseName())
                .licenseUrl(license.getLicenseName())
                .ossLicenseType(license.getOssLicenseType())
                .restriction(restriction).build();
    }
}

