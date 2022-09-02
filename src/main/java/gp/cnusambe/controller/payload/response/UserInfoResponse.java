package gp.cnusambe.controller.payload.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInfoResponse {
    private String userId;
    private String role;

    public UserInfoResponse(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }
}