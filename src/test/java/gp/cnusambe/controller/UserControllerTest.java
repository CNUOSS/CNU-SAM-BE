package gp.cnusambe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gp.cnusambe.domain.User;
import gp.cnusambe.payload.request.SignupRequest;
import gp.cnusambe.security.JwtTokenProvider;
import gp.cnusambe.service.UserDetailsServiceImpl;
import gp.cnusambe.service.UserService;
import gp.cnusambe.util.RedisUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource(locations = "/application-local.properties")
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private UserService userService;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    @MockBean
    private UserDetailsServiceImpl userDetailsServiceImp;
    @MockBean
    private RedisUtil redisUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void join() throws Exception{
        User user = new User("201901234", "123", "ROLE_USER");
        SignupRequest request = new SignupRequest(user.getUserId(), user.getPassword(), user.getRole());
        given(userService.signUp(any())).willReturn(user);

        String content = objectMapper.writeValueAsString(request);
        ResultActions signup = mockMvc.perform(post("/join")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        signup.andExpect(status().isCreated());
    }
}
