package gp.cnusambe.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginResponse {
    @JsonProperty("token_type")
    private final String TOKEN_TYPE = "Bearer";
    private String userId;
    private String accessToken;
    private String uuid;

    public LoginResponse(String userId, String accessToken, String uuid) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.uuid = uuid;
    }
}