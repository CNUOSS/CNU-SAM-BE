package gp.cnusambe.controller.payload;

import gp.cnusambe.controller.payload.response.SWListResponse;
import gp.cnusambe.service.SWService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SWController {
    private final SWService swService;

    @GetMapping("/sw")
    public ResponseEntity<SWListResponse> getAllSW() {
        SWListResponse response = new SWListResponse(swService.readAllCoreSubscriptionSW());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
