package gp.cnusambe.controller;

import gp.cnusambe.payload.request.SubscriptionSWRequest;
import gp.cnusambe.payload.response.SubscriptionSWResponse;
import gp.cnusambe.service.SubscriptionSWService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SubscriptionSWController {
    private final SubscriptionSWService subscriptionSWService;

    @PostMapping("/subscriptions")
    public ResponseEntity<SubscriptionSWResponse> postSubscriptionSW(@RequestBody SubscriptionSWRequest request){
        return new ResponseEntity<>(subscriptionSWService.createSubscriptionSW(request), HttpStatus.OK);
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<Page<SubscriptionSWResponse>> getAllSubscriptionSW(
            @RequestParam(value="search", required=false) boolean search,
            @RequestParam(value="sw-type", required=false) String swType,
            @RequestParam(value="sw-mfr", required=false) String swManufacturer,
            @RequestParam(value="sw-name", required=false) String swName,
            @PageableDefault(size=9, page = 0, sort = "latestUpdateDate", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return new ResponseEntity<>(subscriptionSWService.readAllSubscriptionSW(search, swType, swManufacturer, swName, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/subscriptions/{ssw_id}")
    public ResponseEntity<Void> deleteSubscriptionSW(@PathVariable("ssw_id") Long swId){
        subscriptionSWService.deleteSubscriptionSW(swId);
        return ResponseEntity.noContent().build();
    }
}
