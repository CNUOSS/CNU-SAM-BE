package gp.cnusambe.service;

import gp.cnusambe.exception.custom.SWDuplicatedException;
import gp.cnusambe.exception.custom.SWNotFoundException;
import gp.cnusambe.controller.payload.response.DepartmentResponse;
import gp.cnusambe.controller.payload.response.LectureTypeResponse;
import gp.cnusambe.repository.*;
import gp.cnusambe.repository.domain.*;
import gp.cnusambe.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureSWService {
    private final ModelMapper strictMapper;
    private final SubscriptionSWService subscriptionSWService;
    private final LectureTypeRepository lectureTypeRepository;
    private final DepartmentRepository departmentRepository;
    private final LectureSWRepository lectureSWRepository;
    private final LectureMapRepository lectureMapRepository;
    private final RegistrationSWRepository registrationSWRepository;

    public LectureSWDto createLectureSW(LectureSWDto swDto) {
        if (hasDuplicateLectureSW(swDto.getYear(), swDto.getSemester(), swDto.getLectureNum()))
            throw new SWDuplicatedException();
        LectureSW sw = lectureSWRepository.save(new LectureSW(swDto));
        return strictMapper.map(sw, LectureSWDto.class);
    }

    private boolean hasDuplicateLectureSW(String year, String semester, String lectureNum) {
        return lectureSWRepository.existsByYearAndSemesterAndLectureNum(year, semester, lectureNum);
    }

    public List<SWDto> createAllLectureMap(LectureSWDto lectureSW, List<SWDto> swMapDto) {
        List<SW> ssw = swMapDto.stream().filter(SWDto::isSubscriptionSW).map(element -> strictMapper.map(element, SW.class)).collect(Collectors.toList());
        List<SW> rsw = swMapDto.stream().filter(e -> !e.isSubscriptionSW()).map(element -> strictMapper.map(element, SW.class)).collect(Collectors.toList());
        List<SWDto> listOfSWDto = new ArrayList<>();

        for (SW sw : rsw) {
            RegistrationSW registrationSW = registrationSWRepository.findBySwManufacturerAndSwNameAndIsManaged(sw.getSwManufacturer(), sw.getSwName(), true)
                    .orElse(registrationSWRepository.save(new RegistrationSW(sw.getSwManufacturer(), sw.getSwName(), sw.getLicense())));
            createLectureMap(lectureSW, registrationSW, false);
            listOfSWDto.add(new SWDto(registrationSW, false));
        }

        for (SW sw : ssw) {
            SubscriptionSW subscriptionSW = subscriptionSWService.findSubscriptionSW(sw.getId());
            createLectureMap(lectureSW, subscriptionSW, true);
            listOfSWDto.add(new SWDto(subscriptionSW, true));
        }
        return listOfSWDto;
    }

    private LectureMap createLectureMap(LectureSWDto lectureSWDto, SW sw, boolean isSubscriptionSW) {
        return lectureMapRepository.save(new LectureMap(lectureSWDto.getId(), isSubscriptionSW, sw.getId(), lectureSWDto.getLatestUpdateDate()));
    }

    public LectureSWListDto readAllLectureSW(Pageable pageable) {
        Page<LectureSW> listOfSW = lectureSWRepository.findAll(pageable);
        List<LectureMap> pageOfMap = lectureMapRepository.findAllByLectureSWIdIn(getAllSWId(listOfSW));

        PageInfoDto pageInfo = new PageInfoDto(listOfSW.getTotalElements(), listOfSW.isLast(), listOfSW.getTotalPages(), listOfSW.getSize());
        return new LectureSWListDto(pageInfo, makeListOfLectureSWListDto(pageOfMap));
    }

    public LectureSWListDto searchAllLectureSW(String department, String year, String lectureType, String semester, String lectureName, String lectureNum, String owner, Pageable pageable) {
        Page<LectureSW> listOfSW = lectureSWRepository.findAllBy(department, year, lectureType, semester, lectureName, lectureNum, owner, pageable);
        List<LectureMap> pageOfMap = lectureMapRepository.findAllByLectureSWIdIn(getAllSWId(listOfSW));

        PageInfoDto pageInfo = new PageInfoDto(listOfSW.getTotalElements(), listOfSW.isLast(), listOfSW.getTotalPages(), listOfSW.getSize());
        return new LectureSWListDto(pageInfo, makeListOfLectureSWListDto(pageOfMap));
    }

    private List<Long> getAllSWId(Page<LectureSW> listOfSW) {
        return listOfSW.stream().map(LectureSW::getId).collect(Collectors.toList());
    }

    private List<LectureSWList> makeListOfLectureSWListDto(List<LectureMap> pageOfMap) {
        List<LectureSWList> listOfLectureSWList = new ArrayList<>();
        for (LectureMap lectureMap : pageOfMap) {
            LectureSW lectureSW = lectureSWRepository.findById(lectureMap.getLectureSWId()).orElseThrow(SWNotFoundException::new);
            if (lectureMap.isSubscriptionSW()) {
                SubscriptionSW subscriptionSW = subscriptionSWService.findSubscriptionSW(lectureMap.getSwId());
                listOfLectureSWList.add(new LectureSWList(lectureSW, subscriptionSW));
            } else {
                RegistrationSW registrationSW = registrationSWRepository.findById(lectureMap.getSwId()).orElseThrow(SWNotFoundException::new);
                listOfLectureSWList.add(new LectureSWList(lectureSW, registrationSW));
            }
        }
        return listOfLectureSWList;
    }

    public LectureSWDto readLectureSW(Long swId) {
        LectureSW lectureSW = lectureSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        return strictMapper.map(lectureSW, LectureSWDto.class);
    }

    public List<SWDto> readSWInLectureSW(Long swId) {
        List<LectureMap> listOfMap = lectureMapRepository.findAllByLectureSWId(swId);
        List<LectureSWList> listOfLectureSW = makeListOfLectureSWListDto(listOfMap);
        return listOfLectureSW.stream()
                .map(element -> strictMapper.map(element, SWDto.class))
                .collect(Collectors.toList());
    }

    public void deleteLectureSW(Long swId) {
        List<LectureMap> lectureMaps = lectureMapRepository.findAllByLectureSWId(swId);
        for (LectureMap map : lectureMaps)
            lectureMapRepository.deleteByLectureSWId(map.getLectureSWId());
        LectureSW sw = lectureSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        lectureSWRepository.delete(sw);
    }

    public List<LectureSWForChartDto> readLAllLectureSWForChart(String year) {
        List<LectureSW> a = lectureSWRepository.findAllByYear(year);
        List<Long> listOfSWId = a.stream().map(LectureSW::getId).collect(Collectors.toList());
        List<LectureMap> pageOfMap = lectureMapRepository.findAllByLectureSWIdIn(listOfSWId);

        Map<Long, Integer> map = new HashMap<>();
        for (LectureMap lectureMap : pageOfMap) {
            Long registrationSWId = lectureMap.getSwId();
            if (map.containsKey(registrationSWId))
                map.put(registrationSWId, map.get(registrationSWId) + 1);
            else map.put(registrationSWId, 1);
        }

        List<LectureSWForChartDto> listOfLectureSWForChartDto = new ArrayList<>();
        for (Long key : map.keySet()) {
            Optional<RegistrationSW> optionOfRegistrationSW = registrationSWRepository.findByIdAndIsManaged(key, true);
            optionOfRegistrationSW.ifPresent(sw -> listOfLectureSWForChartDto.add(new LectureSWForChartDto(sw, map.get(key))));
        }
        return listOfLectureSWForChartDto;
    }

    public List<LectureTypeResponse> readAllLectureTypes() {
        return lectureTypeRepository.findAll()
                .stream()
                .map(element -> strictMapper.map(element, LectureTypeResponse.class))
                .collect(Collectors.toList());
    }

    public List<DepartmentResponse> readAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(element -> strictMapper.map(element, DepartmentResponse.class))
                .collect(Collectors.toList());
    }
}