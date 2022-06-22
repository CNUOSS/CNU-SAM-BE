package gp.cnusambe.exception.custom;

public class VersionNotFoundException extends RuntimeException{
    public VersionNotFoundException(){
        super("해당 Version을 찾을 수 없습니다.");
    }
}
