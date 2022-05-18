package gp.cnusambe.service;

import gp.cnusambe.domain.LicenseRestrictionMap;
import gp.cnusambe.domain.OssLicense;
import gp.cnusambe.domain.OssLicenseType;
import gp.cnusambe.domain.Restriction;
import gp.cnusambe.dto.OssLicenseDto;
import gp.cnusambe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
            newRestrictionList.add(restriction.get());
        }

        //Entity -> Dto
        OssLicenseDto newLicenseDto = makeOssLicenseDto(new_license,newRestrictionList);

        return newLicenseDto;
    }

    public Page<OssLicenseDto> searchAll(Pageable pageable){
        Page<OssLicense> licenseList = this.ossLicenseRepository.findAll(pageable);
        List<OssLicenseDto> licenseDtoList = licenseList.stream().map(l-> makeOssLicenseDto(l)).collect(Collectors.toList());
        return new PageImpl<>(licenseDtoList,pageable,licenseList.getTotalElements());
    }

    public Page<OssLicenseDto> searchByLicenseName(String licenseName,Pageable pageable){
        Page<OssLicense> licenseList = this.ossLicenseRepository.findByLicenseNameContaining(licenseName,pageable);
        List<OssLicenseDto> licenseDtoList = licenseList.stream().map(l-> makeOssLicenseDto(l)).collect(Collectors.toList());
        return new PageImpl<>(licenseDtoList,pageable,licenseList.getTotalElements());
    }


    public Page<OssLicenseDto> searchByLicenseTypeName(String licenseTypeName,Pageable pageable){
        Page<OssLicense> licenseList = this.ossLicenseRepository.findByOssLicenseType_LicenseTypeName(licenseTypeName,pageable);
        List<OssLicenseDto> licenseDtoList = licenseList.stream().map(l -> makeOssLicenseDto(l)).collect(Collectors.toList());
        return new PageImpl<>(licenseDtoList,pageable,licenseList.getTotalElements());
    }

    public Page<OssLicenseDto> searchByRestrictionName(String restrictionName,Pageable pageable){
        Set<String> licenseNameSet = new HashSet<>();  // 동일한 이름의 ossLicense은 한가지만 담기위해

        List<LicenseRestrictionMap> mapList = this.licenseRestrictionMapRepository.findByRestriction_RestrictionName(restrictionName);
        for(LicenseRestrictionMap map : mapList){
            licenseNameSet.add(map.getOssLicense().getLicenseName());
        }
        Page<OssLicense> licenseList = this.ossLicenseRepository.findByLicenseNameIn(licenseNameSet,pageable);

        List<OssLicenseDto> licenseDtoList = licenseList.stream().map(l -> makeOssLicenseDto(l)).collect(Collectors.toList());
        return new PageImpl<>(licenseDtoList,pageable,licenseList.getTotalElements());
    }

    //TOD0 : NULL 일때 예외처리
    public List<OssLicense> getOssLicenseData(){
        List<OssLicense> licenseList = this.ossLicenseRepository.findAll();
        return licenseList;
    }

    @Transactional
    public void delete(Long id){
        Optional<OssLicense> license = this.ossLicenseRepository.findOssLicenseById(id);
        List<LicenseRestrictionMap> maps = this.licenseRestrictionMapRepository.findByOssLicense(license.get());
        for (LicenseRestrictionMap map : maps){
             this.licenseRestrictionMapRepository.delete(map);
        }
    }

    /*
     * description : license만 주어졌을때 OssLicenseDto로 변환
     */
    private OssLicenseDto makeOssLicenseDto(OssLicense license){
        //license에 해당되는 restricion 찾기
        List<LicenseRestrictionMap> licenseRestrictionMapList = this.licenseRestrictionMapRepository.findByOssLicense(license);
        List<Restriction> restrictionList = licenseRestrictionMapList.stream().map(m -> m.getRestriction()).collect(Collectors.toList());

        //license와 restriction list를 OssLicenseDto로 변환
        OssLicenseDto licenseDto = makeOssLicenseDto(license,restrictionList);
        return licenseDto;
    }

    /*
     * description : license와 restriction list 주어졌을때 OssLicenseDto로 변환
     * */
    private OssLicenseDto makeOssLicenseDto(OssLicense license, List<Restriction> restriction){
        return OssLicenseDto.builder()
                .id(license.getId())
                .licenseName(license.getLicenseName())
                .licenseUrl(license.getLicenseUrl())
                .ossLicenseType(license.getOssLicenseType())
                .restriction(restriction).build();
    }
}

