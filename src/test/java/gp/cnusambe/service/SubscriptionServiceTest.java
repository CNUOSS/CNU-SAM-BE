package gp.cnusambe.service;

import gp.cnusambe.domain.SubscriptionSW;
import gp.cnusambe.dto.SubscriptionSWDto;
import gp.cnusambe.repository.SubscriptionSWQueryRepository;
import gp.cnusambe.repository.SubscriptionSWRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import static gp.cnusambe.service.fixture.SubscriptionSWFixture.*;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SubscriptionServiceTest {

    private SubscriptionSWService subscriptionSWService;
    private ModelMapper strictMapper;
    private SubscriptionSWRepository subscriptionSWRepository;
    private SubscriptionSWQueryRepository subscriptionSWQueryRepository;

    @BeforeEach
    void setUp() {
        strictMapper = mock(ModelMapper.class);
        subscriptionSWQueryRepository = mock(SubscriptionSWQueryRepository.class);
        subscriptionSWRepository = mock(SubscriptionSWRepository.class);
        subscriptionSWService = new SubscriptionSWService(strictMapper, subscriptionSWRepository, subscriptionSWQueryRepository);
    }

    @Test
    void createSubscriptionSW() {
        given(subscriptionSWRepository.save(any(SubscriptionSW.class))).willReturn(responseSsw());
        given(strictMapper.map(any(SubscriptionSW.class), eq(SubscriptionSWDto.class))).willReturn(responseSswDto());

        SubscriptionSWDto rtnSWDto = subscriptionSWService.createSubscriptionSW(requestSswDto());
        assertThat(rtnSWDto.getId()).isEqualTo(SW_ID);
    }

    @Test
    void readAllSubscriptionSW() {
        given(subscriptionSWRepository.findAll(pageable())).willReturn(pageOfSW());
        given(strictMapper.map(any(SubscriptionSW.class), eq(SubscriptionSWDto.class))).willReturn(responseSswDto());

        Page<SubscriptionSWDto> rtnPageOfDto =  subscriptionSWService.readAllSubscriptionSW("", "", "", pageable());
        assertThat(rtnPageOfDto.getContent().size()).isEqualTo(pageOfSW().getTotalElements());
    }

    @Test
    void readAllSubscriptionSW_Search() {
        given(subscriptionSWQueryRepository.findAllBy(SW_TYPE, "", "", pageable())).willReturn(pageOfSW_ForSearch());
        given(strictMapper.map(any(SubscriptionSW.class), eq(SubscriptionSWDto.class))).willReturn(responseSswDto());

        Page<SubscriptionSWDto> rtnPageOfDto =  subscriptionSWService.readAllSubscriptionSW(SW_TYPE, "", "", pageable());
        assertThat(rtnPageOfDto.getContent().size()).isEqualTo(1);
        assertThat(rtnPageOfDto.getContent().get(0).getSwType()).isEqualTo(SW_TYPE);
    }

}
