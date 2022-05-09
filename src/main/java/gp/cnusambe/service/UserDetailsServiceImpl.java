package gp.cnusambe.service;

import gp.cnusambe.domain.User;
import gp.cnusambe.exception.UserNotFoundException;
import gp.cnusambe.repository.UserRepository;
import gp.cnusambe.security.UserDetailsImpl;
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