package gp.cnusambe.repository.user;

import gp.cnusambe.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUserId(String userId);
}