package gp.cnusambe.service;

import gp.cnusambe.domain.RegistrationSW;
import gp.cnusambe.dto.RegistrationSWDto;
import gp.cnusambe.exception.custom.SWNotFoundException;
import gp.cnusambe.payload.request.RegistrationSWRequest;
import gp.cnusambe.payload.response.ManufacturerResponse;
import gp.cnusambe.repository.ManufacturerRepository;
import gp.cnusambe.repository.RegistrationSWRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationSWService {
    private final ModelMapper modelMapper;
    private final ManufacturerRepository manufacturerRepository;
    private final RegistrationSWRepository registrationSWRepository;

    public RegistrationSWDto createRegistrationSW(RegistrationSWRequest request){
        RegistrationSW sw = registrationSWRepository.save(new RegistrationSW(request));
        return modelMapper.map(sw, RegistrationSWDto.class);
    }

    public List<ManufacturerResponse> getAllManufacturers() {
        return manufacturerRepository.findAll()
                .stream()
                .map(element -> modelMapper.map(element, ManufacturerResponse.class))
                .collect(Collectors.toList());
    }
}
