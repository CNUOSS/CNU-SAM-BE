package gp.cnusambe.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import gp.cnusambe.controller.payload.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        log.error("가입되지 않은 사용자 접근");

        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorResponse errorInfo = new ErrorResponse("가입되지 않은 사용자입니다. 회원 가입을 먼저 진행해주세요.", HttpStatus.UNAUTHORIZED.toString(), request.getRequestURI());
            objectMapper.writeValue(os, errorInfo);
            os.flush();
        }
    }
}