package gp.cnusambe.service;

import gp.cnusambe.domain.SubscriptionSW;
import gp.cnusambe.exception.custom.SWNotFoundException;
import gp.cnusambe.payload.request.SubscriptionSWRequest;
import gp.cnusambe.payload.response.SubscriptionSWResponse;
import gp.cnusambe.repository.SubscriptionSWRepository;
import gp.cnusambe.repository.SwSubscriptionQueryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionSWService {
    private final ModelMapper modelMapper;
    private final SubscriptionSWRepository subscriptionSWRepository;
    private final SwSubscriptionQueryRepository swSubscriptionQueryRepository;

    public SubscriptionSWResponse createSubscriptionSW(SubscriptionSWRequest request) {
        SubscriptionSW sw = subscriptionSWRepository.save(new SubscriptionSW(request));
        return modelMapper.map(sw, SubscriptionSWResponse.class);
    }

    public Page<SubscriptionSWResponse> readAllSubscriptionSW
            (String swType, String swManufacturer, String swName, Pageable pageable) {
        boolean search = swType.length()==0 && swManufacturer.length()==0 & swName.length()==0 ? false : true;
        Page<SubscriptionSW> sw = search
                ? swSubscriptionQueryRepository.findAllBy(swType, swManufacturer, swName, pageable)
                : subscriptionSWRepository.findAll(pageable);
        return sw.map(element -> modelMapper.map(element, SubscriptionSWResponse.class));
    }

    public void deleteSubscriptionSW(Long swId) {
        SubscriptionSW sw = subscriptionSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        subscriptionSWRepository.delete(sw);
    }
}
