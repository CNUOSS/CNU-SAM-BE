package gp.cnusambe.service.user;

import gp.cnusambe.domain.user.User;
import gp.cnusambe.error.UserNotFoundException;
import gp.cnusambe.repository.user.UserRepository;
import gp.cnusambe.security.jwt.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
        return UserDetailsImpl.create(user);
    }
}