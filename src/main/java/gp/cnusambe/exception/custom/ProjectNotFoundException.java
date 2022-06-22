package gp.cnusambe.exception.custom;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(){
        super("해당 project를 찾을 수 없습니다.");
    }
}
