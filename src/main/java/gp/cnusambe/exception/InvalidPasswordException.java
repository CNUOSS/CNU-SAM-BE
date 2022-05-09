package gp.cnusambe.exception;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("아이디와 비밀번호를 다시 확인해주세요.");
    }
}
