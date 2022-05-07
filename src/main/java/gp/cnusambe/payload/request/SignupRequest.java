package gp.cnusambe.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {
    private String userId;
    private String password;
    private String role;

    public SignupRequest(String userId, String password, String role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }
}