package gp.cnusambe.controller.user;

import gp.cnusambe.domain.user.User;
import gp.cnusambe.payload.request.SignupRequest;
import gp.cnusambe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> signUp(@RequestBody SignupRequest signUpRequest) throws Exception {
        if (userService.isDuplicateUserId(signUpRequest.getUserId())) {
            throw new Exception("userID가 중복되었습니다.");
        }
        User user = userService.signUp(signUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/")
                .buildAndExpand(user.getUserId()).toUri();
        return ResponseEntity.created(location).body(user.getUserId());
    }
}
