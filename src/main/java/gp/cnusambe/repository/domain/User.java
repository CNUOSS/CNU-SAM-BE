package gp.cnusambe.repository.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @Column(length = 10, nullable = false, unique = true)
    private String userId;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String role;

    @Builder
    public User(String userId, String password, String role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }
}