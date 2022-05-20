package gp.cnusambe.service;

import gp.cnusambe.payload.response.ManufacturerResponse;
import gp.cnusambe.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterSWService {
    private final ModelMapper modelMapper;
    private final ManufacturerRepository manufacturerRepository;

    public List<ManufacturerResponse> getAllManufacturers() {
        return manufacturerRepository.findAll()
                .stream()
                .map(element -> modelMapper.map(element, ManufacturerResponse.class))
                .collect(Collectors.toList());
    }
}
