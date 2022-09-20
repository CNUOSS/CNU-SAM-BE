package gp.cnusambe.controller;

import gp.cnusambe.service.dto.PageInfoDto;
import gp.cnusambe.service.dto.SubscriptionSWDto;
import gp.cnusambe.exception.custom.SWDuplicatedException;
import gp.cnusambe.controller.payload.request.SubscriptionSWRequest;
import gp.cnusambe.controller.payload.request.SubscriptionSWUpdateRequest;
import gp.cnusambe.controller.payload.response.SimpleSubscriptionSWListResponse;
import gp.cnusambe.controller.payload.response.SubscriptionSWListResponse;
import gp.cnusambe.controller.payload.response.SubscriptionSWResponse;
import gp.cnusambe.service.SubscriptionSWService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/subscriptions")
public class SubscriptionSWController {
    private final ModelMapper strictMapper;
    private final SubscriptionSWService subscriptionSWService;

    @PostMapping
    public ResponseEntity<SubscriptionSWResponse> postSubscriptionSW(@RequestBody SubscriptionSWRequest request) {
        SubscriptionSWDto swDto = strictMapper.map(request, SubscriptionSWDto.class);
        if (subscriptionSWService.hasDuplicateSubscriptionSW(swDto.getSwManufacturer(), swDto.getSwName(), swDto.getLicense()))
            throw new SWDuplicatedException();
        SubscriptionSWResponse response = new SubscriptionSWResponse(subscriptionSWService.createSubscriptionSW(swDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<SubscriptionSWListResponse> getAllSubscriptionSW(
            @RequestParam(value = "sw-type", required = false) String swType,
            @RequestParam(value = "sw-mfr", required = false) String swManufacturer,
            @RequestParam(value = "sw-name", required = false) String swName,
            @PageableDefault(size = 9, page = 0, sort = "latestUpdateDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        String swType_ = Optional.ofNullable(swType).orElse("");
        String swManufacturer_ = Optional.ofNullable(swManufacturer).orElse("");
        String swName_ = Optional.ofNullable(swName).orElse("");

        boolean search = swType_.length() != 0 || swManufacturer_.length() != 0 || swName_.length() != 0;
        Page<SubscriptionSWDto> pageOfSW = search
                ? subscriptionSWService.searchAllSubscriptionSW(swType_, swManufacturer_, swName_, pageable)
                : subscriptionSWService.readAllSubscriptionSW(pageable);
        PageInfoDto pageInfo = new PageInfoDto(pageOfSW.getTotalElements(), pageOfSW.isLast(), pageOfSW.getTotalPages(), pageOfSW.getSize());
        SubscriptionSWListResponse response = new SubscriptionSWListResponse(pageInfo, pageOfSW.stream().collect(Collectors.toList()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SubscriptionSWResponse> updateSubscriptionSW(@RequestBody SubscriptionSWUpdateRequest request) {
        SubscriptionSWDto newSWDto = strictMapper.map(request, SubscriptionSWDto.class);
        if (subscriptionSWService.hasDuplicateSubscriptionSW(newSWDto.getSwManufacturer(), newSWDto.getSwName(), newSWDto.getLicense()))
            throw new SWDuplicatedException();
        SubscriptionSWResponse response = new SubscriptionSWResponse(subscriptionSWService.updateSubscriptionSW(newSWDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{ssw_id}")
    public ResponseEntity<Void> deleteSubscriptionSW(@PathVariable("ssw_id") Long swId) {
        subscriptionSWService.deleteSubscriptionSW(swId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<SimpleSubscriptionSWListResponse> getAllSubscriptionSW(){
        SimpleSubscriptionSWListResponse response = new SimpleSubscriptionSWListResponse(subscriptionSWService.readAllSubscriptionSW());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
