package gp.cnusambe.service;

import gp.cnusambe.exception.custom.SWDuplicatedException;
import gp.cnusambe.repository.domain.SubscriptionSW;
import gp.cnusambe.service.dto.SWDto;
import gp.cnusambe.service.dto.SubscriptionSWDto;
import gp.cnusambe.exception.custom.SWNotFoundException;
import gp.cnusambe.repository.SubscriptionSWRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionSWService {
    private final ModelMapper strictMapper;
    private final SubscriptionSWRepository subscriptionSWRepository;

    public SubscriptionSWDto createSubscriptionSW(SubscriptionSWDto swDto) {
        if (hasDuplicateSubscriptionSW(swDto.getSwManufacturer(), swDto.getSwName(), swDto.getLicense()))
            throw new SWDuplicatedException();
        SubscriptionSW sw = subscriptionSWRepository.save(new SubscriptionSW(swDto));
        return strictMapper.map(sw, SubscriptionSWDto.class);
    }

    public Page<SubscriptionSWDto> readAllCoreSubscriptionSW(Pageable pageable) {
        Page<SubscriptionSW> pageOfSW = subscriptionSWRepository.findAll(pageable);
        return pageOfSW.map(sw -> strictMapper.map(sw, SubscriptionSWDto.class));
    }

    public Page<SubscriptionSWDto> searchAllSubscriptionSW(String swType, String swManufacturer, String swName, Pageable pageable) {
        Page<SubscriptionSW> pageOfSW = subscriptionSWRepository.findAllBy(swType, swManufacturer, swName, pageable);
        return pageOfSW.map(sw -> strictMapper.map(sw, SubscriptionSWDto.class));
    }

    public void deleteSubscriptionSW(Long swId) {
        SubscriptionSW sw = findSubscriptionSW(swId);
        subscriptionSWRepository.delete(sw);
    }

    private boolean hasDuplicateSubscriptionSW(String swManufacturer, String swName, String license) {
        return subscriptionSWRepository.existsBySwManufacturerAndSwNameAndLicense(swManufacturer, swName, license);
    }

    public SubscriptionSWDto updateSubscriptionSW(SubscriptionSWDto newSWDto) {
        if (hasDuplicateSubscriptionSW(newSWDto.getSwManufacturer(), newSWDto.getSwName(), newSWDto.getLicense()))
            throw new SWDuplicatedException();
        findSubscriptionSW(newSWDto.getId());
        SubscriptionSW sw = subscriptionSWRepository.save(new SubscriptionSW(newSWDto));
        return strictMapper.map(sw, SubscriptionSWDto.class);
    }

    public SubscriptionSW findSubscriptionSW(Long swId) {
        return subscriptionSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
    }
}
