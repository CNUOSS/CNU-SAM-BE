package gp.cnusambe.exception.custom;

public class AccessTokenException extends RuntimeException {
    public AccessTokenException() {
        super("유효하지 않은 AccessToken 입니다.");
    }
}
