package gp.cnusambe.exception.custom;

public class SWDuplicatedException extends RuntimeException {

    public SWDuplicatedException() {
        super("중복된 SW가 이미 존재합니다.");
    }
}
