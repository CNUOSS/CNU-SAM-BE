package gp.cnusambe.service;

import gp.cnusambe.repository.domain.User;
import gp.cnusambe.controller.payload.request.SignupRequest;
import gp.cnusambe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public User signUp(SignupRequest request) {
        User user = User.builder()
                .userId(request.getUserId())
                .password(encoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        return userRepository.save(user);
    }

    public boolean isDuplicateUserId(String userId) {
        return userRepository.existsUserByUserId(userId);
    }
}
