package gp.cnusambe.service;

import gp.cnusambe.repository.domain.OssLicenseType;
import gp.cnusambe.repository.OssLicenseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OssLicenseTypeService {
    private final OssLicenseTypeRepository ossLicenseTypeRepository;

    //TODO: NULL 처리
    public List<OssLicenseType> getOssLicenseTypeData(){
        List<OssLicenseType> licenseTypeList = this.ossLicenseTypeRepository.findAll();
        return licenseTypeList;
    }
}
