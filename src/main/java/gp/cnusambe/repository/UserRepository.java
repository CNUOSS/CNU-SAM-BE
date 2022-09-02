package gp.cnusambe.repository;

import gp.cnusambe.repository.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsUserByUserId(String userId);
    Optional<User> findByUserId(String userId);
}