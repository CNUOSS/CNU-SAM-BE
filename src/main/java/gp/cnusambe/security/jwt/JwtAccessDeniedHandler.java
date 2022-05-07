package gp.cnusambe.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import gp.cnusambe.error.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        log.error("권한이 없는 사용자 접근");

        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorInfo errorInfo = new ErrorInfo("접근 권한이 없습니다.", HttpStatus.FORBIDDEN.toString(), request.getRequestURI());
            objectMapper.writeValue(os, errorInfo);
            os.flush();
        }
    }
}