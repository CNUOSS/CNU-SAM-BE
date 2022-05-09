package gp.cnusambe.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
public class LoginResponse {
    private final String tokenType = "Bearer";
    private String userId;
    private String accessToken;
    private String uuid;
    private String role;

    public LoginResponse(String userId, String accessToken, String uuid, String role) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.uuid = uuid;
        this.role = role;
    }
}