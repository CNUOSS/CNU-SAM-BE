package gp.cnusambe.service;

import gp.cnusambe.repository.domain.RegistrationSW;
import gp.cnusambe.service.dto.RegistrationSWDto;
import gp.cnusambe.exception.custom.SWNotFoundException;
import gp.cnusambe.controller.payload.response.ManufacturerResponse;
import gp.cnusambe.controller.payload.response.SWNameResponse;
import gp.cnusambe.repository.ManufacturerRepository;
import gp.cnusambe.repository.RegistrationSWQueryRepository;
import gp.cnusambe.repository.RegistrationSWRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationSWService {
    private final ModelMapper strictMapper;
    private final ManufacturerRepository manufacturerRepository;
    private final RegistrationSWRepository registrationSWRepository;
    private final RegistrationSWQueryRepository registrationSWQueryRepository;

    public RegistrationSWDto createRegistrationSW(RegistrationSWDto swDto){
        RegistrationSW sw = registrationSWRepository.save(new RegistrationSW(swDto));
        return strictMapper.map(sw, RegistrationSWDto.class);
    }

    public Page<RegistrationSWDto> readAllRegistrationSW(String swManufacturer, String swName, Pageable pageable) {
        boolean search = swManufacturer.length()==0 & swName.length()==0 ? false : true;
        Page<RegistrationSW> pageOfSW = search
                ? registrationSWQueryRepository.findAllBy(swManufacturer, swName, pageable)
                : registrationSWRepository.findAllByIsManaged(true, pageable);
        return pageOfSW.map(sw -> strictMapper.map(sw, RegistrationSWDto.class));
    }

    public RegistrationSWDto updateRegistrationSW(Long swId, RegistrationSWDto swDto){
        RegistrationSW sw = registrationSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        sw.updateRegistrationSW(swDto);
        sw = registrationSWRepository.save(sw);
        return strictMapper.map(sw, RegistrationSWDto.class);
    }

    public void deleteRegistrationSW(Long swId){
        RegistrationSW sw = registrationSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        registrationSWRepository.delete(sw);
    }

    public List<ManufacturerResponse> readAllManufacturers() {
        return manufacturerRepository.findAll()
                .stream()
                .map(element -> strictMapper.map(element, ManufacturerResponse.class))
                .collect(Collectors.toList());
    }

    public List<SWNameResponse> readAllSwNames(){
        List<String> name = registrationSWRepository.findAllByIsManaged(true)
                .stream().map(RegistrationSW::getSwName).collect(Collectors.toList());
        return name.stream().map(SWNameResponse::new).collect(Collectors.toList());
    }
}
