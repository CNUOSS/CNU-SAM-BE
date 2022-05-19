package gp.cnusambe.controller;

import gp.cnusambe.domain.SubscriptionSW;
import gp.cnusambe.payload.request.SubscriptionSWRequest;
import gp.cnusambe.payload.response.SubscriptionSWResponse;
import gp.cnusambe.service.SubscriptionSWService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SubscriptionSWController {
    private final SubscriptionSWService subscriptionSWService;

    @PostMapping("/subscriptions")
    public ResponseEntity<SubscriptionSWResponse> postSubscriptionSW(@RequestBody SubscriptionSWRequest request){
        SubscriptionSW sw = subscriptionSWService.createSubscriptionSW(request);
        return new ResponseEntity<>(new SubscriptionSWResponse(sw), HttpStatus.OK);
    }

    @DeleteMapping("/subscriptions/{ssw_id}")
    public ResponseEntity<Void> deleteSubscriptionSW(@PathVariable("ssw_id") Long swId){
        subscriptionSWService.deleteSubscriptionSW(swId);
        return ResponseEntity.noContent().build();
    }
}
