package gp.cnusambe.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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