package gp.cnusambe.service;


import gp.cnusambe.domain.OssAnalysisMap;
import gp.cnusambe.dto.OssAnalysisDto;
import gp.cnusambe.repository.OssAnalysisMapRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OssAnalysisMapService {
    private final OssAnalysisMapRepository ossAnalysisMapRepository;
    private final ModelMapper modelMapper;

    public OssAnalysisMap create(OssAnalysisDto analysisDto){
        OssAnalysisMap analysisMap = modelMapper.map(analysisDto, OssAnalysisMap.class);
        return this.ossAnalysisMapRepository.save(analysisMap);
    }
}
