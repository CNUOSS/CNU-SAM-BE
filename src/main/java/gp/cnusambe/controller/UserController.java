package gp.cnusambe.controller;

import gp.cnusambe.domain.User;
import gp.cnusambe.error.InvalidPasswordException;
import gp.cnusambe.error.RefreshTokenException;
import gp.cnusambe.payload.request.LoginRequest;
import gp.cnusambe.payload.request.LogoutOrRefreshRequest;
import gp.cnusambe.payload.request.SignupRequest;
import gp.cnusambe.payload.response.LoginResponse;
import gp.cnusambe.security.JwtTokenProvider;
import gp.cnusambe.security.UserDetailsImpl;
import gp.cnusambe.service.UserDetailsServiceImpl;
import gp.cnusambe.service.UserService;
import gp.cnusambe.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsServiceImp;
    private final RedisUtil redisUtil;

    @PostMapping("/join")
    public ResponseEntity<Void> signUp(@RequestBody SignupRequest signUpRequest) throws Exception {
        if (userService.isDuplicateUserId(signUpRequest.getUserId())) {
            throw new Exception("userID가 중복되었습니다.");
        }
        User user = userService.signUp(signUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/").path("/{userId}")
                .buildAndExpand(user.getUserId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new InvalidPasswordException();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl)authentication.getPrincipal();
        LoginResponse jwtResponse = generateAndSaveToken(userDetailsImpl);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody LogoutOrRefreshRequest request) {
        String uuid = request.getUuid();
        String userId = request.getUserId();
        String oldAccessToken = request.getAccessToken();
        String oldRefreshToken = redisUtil.getData(uuid).orElseThrow(RefreshTokenException::new);

        if (!jwtTokenProvider.validateJwtToken(oldAccessToken)) {
            throw new RefreshTokenException();
        }

        if(!userId.equals(jwtTokenProvider.getUserIdFromJwtToken(oldRefreshToken)) && !userId.equals(jwtTokenProvider.getUserIdFromJwtToken(oldAccessToken))) {
            throw new RefreshTokenException();
        }

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsServiceImp.loadUserByUsername(userId);
        LoginResponse jwtResponse = generateAndSaveToken(userDetailsImpl);
        deleteToken(uuid, oldAccessToken);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutOrRefreshRequest request) {
        deleteToken(request.getUuid(), request.getAccessToken());
        return ResponseEntity.noContent().build();
    }

    private LoginResponse generateAndSaveToken(UserDetailsImpl userDetailsImpl) {
        String userId = userDetailsImpl.getUserId();
        String uuid = UUID.randomUUID().toString();
        String role = userDetailsImpl.getAuthorities().stream().findFirst().get().toString();
        String accessToken = jwtTokenProvider.generateJwtToken(userDetailsImpl);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userDetailsImpl);

        redisUtil.setDataExpire(uuid, refreshToken, (int) JwtTokenProvider.REFRESH_EXPIRATION_SECONDS);

        return new LoginResponse(userId, accessToken, uuid, role);
    }

    private void deleteToken(String uuid, String oldAccessToken) {
        if (redisUtil.getData(uuid).isPresent()) {
            redisUtil.deleteData(uuid);
        }
        redisUtil.setDataExpire(oldAccessToken, oldAccessToken, (int)JwtTokenProvider.TOKEN_EXPIRATION_SECONDS);
    }
}