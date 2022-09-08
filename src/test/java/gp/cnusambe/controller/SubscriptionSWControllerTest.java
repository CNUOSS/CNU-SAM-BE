package gp.cnusambe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gp.cnusambe.controller.payload.request.SubscriptionSWRequest;
import gp.cnusambe.controller.payload.request.SubscriptionSWUpdateRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static gp.cnusambe.fixture.SubscriptionSWFixture.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SubscriptionSWControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    private final long UPDATE_SW_ID = 1;
    private final long DELETE_SW_ID = 3;
    private final long NO_SW_ID = 10;

    private int sizeOfSW = 0;

    void addFixtureForUpdate() throws Exception {
        SubscriptionSWRequest requestForUpdate = SubscriptionSWRequest.builder()
                .latestUpdaterId(LATEST_UPDATER_ID)
                .swType(SW_TYPE)
                .swManufacturer(SW_MANUFACTURER)
                .swName(SW_NAME)
                .usageRange(USAGE_RANGE)
                .license(LICENSE)
                .expireDate(DATE)
                .firstSubscribeDate(DATE)
                .build();

        String requestBodyForUpdate = mapper.writeValueAsString(requestForUpdate);

        mockMvc.perform(post("/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyForUpdate));

        sizeOfSW++;
    }

    void addFixtureForDuplicate() throws Exception {
        SubscriptionSWRequest requestForDuplicate = SubscriptionSWRequest.builder()
                .latestUpdaterId(LATEST_UPDATER_ID)
                .swType(SW_TYPE)
                .swManufacturer(NEW_SW_MANUFACTURER)
                .swName(SW_NAME)
                .usageRange(USAGE_RANGE)
                .license(LICENSE)
                .expireDate(DATE)
                .firstSubscribeDate(DATE)
                .build();

        String requestBodyForDuplicated = mapper.writeValueAsString(requestForDuplicate);

        mockMvc.perform(post("/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyForDuplicated));

        sizeOfSW++;
    }

    void addFixtureForDelete() throws Exception {
        SubscriptionSWRequest requestForDelete = SubscriptionSWRequest.builder()
                .latestUpdaterId(LATEST_UPDATER_ID)
                .swType(SW_TYPE)
                .swManufacturer(SW_MANUFACTURER)
                .swName(NEW_SW_NAME)
                .usageRange(USAGE_RANGE)
                .license(LICENSE)
                .expireDate(DATE)
                .firstSubscribeDate(DATE)
                .build();

        String requestBodyForDelete = mapper.writeValueAsString(requestForDelete);

        mockMvc.perform(post("/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyForDelete));

        sizeOfSW++;
    }

    @BeforeAll
    void setUp() throws Exception {
        addFixtureForUpdate();
        addFixtureForDuplicate();
        addFixtureForDelete();
    }

    @Test
    void createSubscriptionSW() throws Exception {
        SubscriptionSWRequest request = SubscriptionSWRequest.builder()
                .latestUpdaterId(NEW_LATEST_UPDATER_ID)
                .swType(SW_TYPE)
                .swManufacturer(SW_MANUFACTURER)
                .swName(SW_NAME)
                .usageRange(USAGE_RANGE)
                .license(NEW_LICENSE)
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

        sizeOfSW++;
    }

    @Test
    void createSubscriptionSW_DuplicatedSW() throws Exception {
        SubscriptionSWRequest request = SubscriptionSWRequest.builder()
                .latestUpdaterId(LATEST_UPDATER_ID)
                .swType(SW_TYPE)
                .swManufacturer(NEW_SW_MANUFACTURER)
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
                        .value("중복된 SW가 이미 존재합니다."));
    }

    @Test
    void updateSubscriptionSW() throws Exception {
        SubscriptionSWUpdateRequest request = SubscriptionSWUpdateRequest.builder()
                .id(UPDATE_SW_ID)
                .latestUpdaterId(NEW_LATEST_UPDATER_ID)
                .swType(SW_TYPE)
                .swManufacturer(NEW_SW_MANUFACTURER)
                .swName(SW_NAME)
                .usageRange(USAGE_RANGE)
                .license(NEW_LICENSE)
                .latestUpdateDate(DATE)
                .expireDate(DATE)
                .firstSubscribeDate(DATE)
                .build();

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(put("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subscription_sw.id")
                        .value(SW_ID))
                .andExpect(jsonPath("$.subscription_sw.license")
                        .value(NEW_LICENSE))
                .andExpect(jsonPath("$.subscription_sw.sw_manufacturer")
                        .value(NEW_SW_MANUFACTURER));
    }

    @Test
    void updateSubscriptionSW_DuplicatedSW() throws Exception {
        SubscriptionSWUpdateRequest request = SubscriptionSWUpdateRequest.builder()
                .id(UPDATE_SW_ID)
                .latestUpdaterId(NEW_LATEST_UPDATER_ID)
                .swType(SW_TYPE)
                .swManufacturer(NEW_SW_MANUFACTURER)
                .swName(SW_NAME)
                .usageRange(USAGE_RANGE)
                .license(LICENSE)
                .latestUpdateDate(DATE)
                .expireDate(DATE)
                .firstSubscribeDate(DATE)
                .build();

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(put("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error_msg")
                        .value("중복된 SW가 이미 존재합니다."));
    }

    @Test
    void updateSubscriptionSW_NoId() throws Exception {
        SubscriptionSWUpdateRequest request = SubscriptionSWUpdateRequest.builder()
                .id(NO_SW_ID)
                .latestUpdaterId(NEW_LATEST_UPDATER_ID)
                .swType(SW_TYPE)
                .swManufacturer(SW_MANUFACTURER)
                .swName(NEW_SW_NAME)
                .usageRange(USAGE_RANGE)
                .license(NEW_LICENSE)
                .latestUpdateDate(DATE)
                .expireDate(DATE)
                .firstSubscribeDate(DATE)
                .build();

        String requestBody = mapper.writeValueAsString(request);

        mockMvc.perform(put("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error_msg")
                        .value("해당 SW를 찾을 수 없습니다."));
    }

    @Test
    void deleteSubscriptionSW() throws Exception {
        mockMvc.perform(delete("/subscriptions/{id}", DELETE_SW_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        sizeOfSW--;
    }

    @Test
    void deleteSubscriptionSW_NoId() throws Exception {
        mockMvc.perform(delete("/subscriptions/{id}", NO_SW_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error_msg")
                        .value("해당 SW를 찾을 수 없습니다."));
    }

    @Test
    void getAllSubscriptionSW() throws Exception {
        mockMvc.perform(get("/subscriptions/search")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subscription_sw").isArray())
                .andExpect(jsonPath("$.subscription_sw.length()").value(sizeOfSW));
    }

}
