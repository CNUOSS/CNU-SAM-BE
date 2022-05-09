package gp.cnusambe.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LogoutOrRefreshRequest {
    private String userId;
    private String accessToken;
    private String uuid;

    public LogoutOrRefreshRequest(String userId, String accessToken, String uuid) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.uuid = uuid;
    }
}