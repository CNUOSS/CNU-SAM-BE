package gp.cnusambe.exception.custom;

public class SWNotFoundException extends RuntimeException{
    public SWNotFoundException(){
        super("해당 SW를 찾을 수 없습니다.");
    }
}
