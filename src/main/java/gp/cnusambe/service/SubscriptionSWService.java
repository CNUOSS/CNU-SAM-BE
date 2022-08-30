package gp.cnusambe.service;

import gp.cnusambe.domain.SubscriptionSW;
import gp.cnusambe.dto.SubscriptionSWDto;
import gp.cnusambe.exception.custom.SWNotFoundException;
import gp.cnusambe.repository.SubscriptionSWRepository;
import gp.cnusambe.repository.SubscriptionSWQueryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionSWService {
    private final ModelMapper strictMapper;
    private final SubscriptionSWRepository subscriptionSWRepository;
    private final SubscriptionSWQueryRepository subscriptionSWQueryRepository;

    public SubscriptionSWDto createSubscriptionSW(SubscriptionSWDto swDto) {
        SubscriptionSW sw = subscriptionSWRepository.save(new SubscriptionSW(swDto));
        return strictMapper.map(sw, SubscriptionSWDto.class);
    }

    public Page<SubscriptionSWDto> readAllSubscriptionSW(String swType, String swManufacturer, String swName, Pageable pageable) {
        boolean search = swType.length()==0 && swManufacturer.length()==0 & swName.length()==0 ? false : true;
        Page<SubscriptionSW> pageOfSW = search
                ? subscriptionSWQueryRepository.findAllBy(swType, swManufacturer, swName, pageable)
                : subscriptionSWRepository.findAll(pageable);
        return pageOfSW.map(sw -> strictMapper.map(sw, SubscriptionSWDto.class));
    }

    public void deleteSubscriptionSW(Long swId) {
        SubscriptionSW sw = subscriptionSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        subscriptionSWRepository.delete(sw);
    }

    public SubscriptionSWDto readSubscriptionSW(Long swId){
        SubscriptionSW sw =  subscriptionSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        return strictMapper.map(sw, SubscriptionSWDto.class);
    }

    public boolean hasDuplicateSubscriptionSW(String swManufacturer, String swName, String license){
        return subscriptionSWRepository.existsBySwManufacturerAndSwNameAndLicense(swManufacturer, swName, license);
    }
}
