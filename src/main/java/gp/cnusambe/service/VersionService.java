package gp.cnusambe.service;

import gp.cnusambe.domain.Version;
import gp.cnusambe.dto.VersionDto;
import gp.cnusambe.repository.OssLicenseRepository;
import gp.cnusambe.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VersionService {
    private final OssLicenseRepository licenseRepository;
    private final VersionRepository versionRepository;
    private final ModelMapper modelMapper;

    public Version create(VersionDto versionDto){
        Version version = this.modelMapper.map(versionDto, Version.class);
        Version newVersion = this.versionRepository.save(version);
        return newVersion;
    }
}
