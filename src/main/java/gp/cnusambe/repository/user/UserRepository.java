package gp.cnusambe.repository.user;

import gp.cnusambe.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUserId(String userId);
    Optional<User> findByUserId(String userId);
}