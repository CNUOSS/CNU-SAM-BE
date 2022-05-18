package gp.cnusambe.service;

import gp.cnusambe.domain.LectureType;
import gp.cnusambe.repository.LectureTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SWService {
    private final LectureTypeRepository lectureTypeRepository;

    public List<LectureType> getAllLectureTypes(){
        return lectureTypeRepository.findAll();
    }
}
