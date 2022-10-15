package gp.cnusambe.service;

import gp.cnusambe.repository.RegistrationSWRepository;
import gp.cnusambe.repository.SubscriptionSWRepository;
import gp.cnusambe.service.dto.SWDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SWService {
    private final SubscriptionSWRepository subscriptionSWRepository;
    private final RegistrationSWRepository registrationSWRepository;

    public List<SWDto> readAllCoreSubscriptionSW() {
        Stream<SWDto> listOfSubscriptionSW = subscriptionSWRepository.findAll().stream().map(sw -> new SWDto(sw, true));
        Stream<SWDto> listOfRegistrationSW = registrationSWRepository.findAll().stream().map(sw -> new SWDto(sw, false));
        return Stream.concat(listOfSubscriptionSW, listOfRegistrationSW).collect(Collectors.toList());
    }
}
