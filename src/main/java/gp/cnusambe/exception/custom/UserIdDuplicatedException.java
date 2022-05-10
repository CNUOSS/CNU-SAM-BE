package gp.cnusambe.exception.custom;

public class UserIdDuplicatedException extends RuntimeException{
    public UserIdDuplicatedException() {super("userID가 중복되었습니다.");}
}
