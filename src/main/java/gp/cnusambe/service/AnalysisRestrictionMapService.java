package gp.cnusambe.service;

import gp.cnusambe.domain.AnalysisRestrictionMap;
import gp.cnusambe.domain.OssAnalysisMap;
import gp.cnusambe.domain.Restriction;
import gp.cnusambe.dto.OssAnalysisDto;
import gp.cnusambe.repository.AnalysisRestrictionMapRepository;
import gp.cnusambe.repository.OssAnalysisMapRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnalysisRestrictionMapService {
    private final AnalysisRestrictionMapRepository analysisRestrictionMapRepository;
    private final OssAnalysisMapRepository ossAnalysisMapRepository;
    private final ModelMapper modelMapper;

    public void create(OssAnalysisDto analysisDto, List<Restriction> restrictionList){
        OssAnalysisMap analysisMap = modelMapper.map(analysisDto, OssAnalysisMap.class);

        //분석결과 저장
        OssAnalysisMap newAnalysisMap = this.ossAnalysisMapRepository.save(analysisMap);

        //분석결과규제매핑 저장
        for(Restriction restriction : restrictionList){
            this.analysisRestrictionMapRepository.save(new AnalysisRestrictionMap(restriction, newAnalysisMap));
        }
    }
}
