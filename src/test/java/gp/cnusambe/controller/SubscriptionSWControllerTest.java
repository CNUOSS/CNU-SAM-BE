package gp.cnusambe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gp.cnusambe.controller.payload.request.SubscriptionSWRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static gp.cnusambe.fixture.SubscriptionSWFixture.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SubscriptionSWControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception {
        SubscriptionSWRequest request = SubscriptionSWRequest.builder()
                .latestUpdaterId(LATEST_UPDATER_ID)
                .swType(SW_TYPE)
                .swManufacturer(SW_MANUFACTURER)
                .swName(SW_NAME)
                .usageRange(USAGE_RANGE)
                .license(LICENSE)
                .expireDate(DATE)
                .firstSubscribeDate(DATE)
                .build();

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(post("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));
    }

    @Test
    void createSubscriptionSW() throws Exception {
        SubscriptionSWRequest request = SubscriptionSWRequest.builder()
                .latestUpdaterId(NEW_LATEST_UPDATER_ID)
                .swType(SW_TYPE)
                .swManufacturer(SW_MANUFACTURER)
                .swName(NEW_SW_NAME)
                .usageRange(USAGE_RANGE)
                .license(LICENSE)
                .expireDate(DATE)
                .firstSubscribeDate(DATE)
                .build();

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(post("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subscription_sw.latest_updater_id")
                        .value(NEW_LATEST_UPDATER_ID));
    }

    @Test
    void createSubscriptionSW_DuplicatedSW() throws Exception {
        SubscriptionSWRequest request = SubscriptionSWRequest.builder()
                .latestUpdaterId(LATEST_UPDATER_ID)
                .swType(SW_TYPE)
                .swManufacturer(SW_MANUFACTURER)
                .swName(SW_NAME)
                .usageRange(USAGE_RANGE)
                .license(LICENSE)
                .expireDate(DATE)
                .firstSubscribeDate(DATE)
                .build();

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(post("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error_msg")
                        .value("중복된 SW가 이미 존재합니다."));;
    }
}
