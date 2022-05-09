package gp.cnusambe.exception;

public class RefreshTokenException extends RuntimeException {
    public RefreshTokenException() {
        super("유효하지 않은 RefreshToken 입니다.");
    }
}