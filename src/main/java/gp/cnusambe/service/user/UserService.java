package gp.cnusambe.service.user;

import gp.cnusambe.domain.user.User;
import gp.cnusambe.payload.request.SignupRequest;
import gp.cnusambe.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User signUp(SignupRequest request) {
        User user = User.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
        return userRepository.save(user);
    }

    public boolean isDuplicateUserId(String userId) {
        return userRepository.existsUserByUserId(userId);
    }
}
