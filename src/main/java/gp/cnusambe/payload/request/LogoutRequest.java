package gp.cnusambe.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LogoutRequest {
    private String uuid;
    private String userId;
    private String accessToken;

    public LogoutRequest(String uuid, String userId, String accessToken) {
        this.uuid = uuid;
        this.userId = userId;
        this.accessToken = accessToken;
    }
}