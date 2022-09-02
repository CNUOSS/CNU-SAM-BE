package gp.cnusambe.controller.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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