package gp.cnusambe.service;

import gp.cnusambe.domain.Restriction;
import gp.cnusambe.repository.RestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestrictionService {
    private final RestrictionRepository restrictionRepository;

    //TODO: NULL 처리
    public List<Restriction> getRestrictionData() {
        List<Restriction> restrictionList = this.restrictionRepository.findAll();
        return restrictionList;
    }
}
