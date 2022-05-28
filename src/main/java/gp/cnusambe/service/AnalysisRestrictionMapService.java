package gp.cnusambe.service;

import gp.cnusambe.domain.AnalysisRestrictionMap;
import gp.cnusambe.domain.OssAnalysisMap;
import gp.cnusambe.domain.Restriction;
import gp.cnusambe.repository.AnalysisRestrictionMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnalysisRestrictionMapService {
    private final AnalysisRestrictionMapRepository analysisRestrictionMapRepository;

    public List<AnalysisRestrictionMap> create(List<Restriction> restrictionList, OssAnalysisMap analysis){
        return restrictionList.stream().map(r -> this.analysisRestrictionMapRepository.save(new AnalysisRestrictionMap(r, analysis))).collect(Collectors.toList());
    }
}
