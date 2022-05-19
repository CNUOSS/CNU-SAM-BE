package gp.cnusambe.service;

import gp.cnusambe.domain.SubscriptionSW;
import gp.cnusambe.exception.custom.SWNotFoundException;
import gp.cnusambe.payload.request.SubscriptionSWRequest;
import gp.cnusambe.repository.SubscriptionSWRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionSWService {
    private final SubscriptionSWRepository subscriptionSWRepository;

    public SubscriptionSW createSubscriptionSW(SubscriptionSWRequest request){
        SubscriptionSW sw = new SubscriptionSW(request);
        return subscriptionSWRepository.save(sw);
    }

    public void deleteSubscriptionSW(Long swId){
        SubscriptionSW sw = subscriptionSWRepository.findById(swId).orElseThrow(SWNotFoundException::new);
        subscriptionSWRepository.delete(sw);
    }
}
