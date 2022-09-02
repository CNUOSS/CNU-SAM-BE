package gp.cnusambe.fixture;

import gp.cnusambe.repository.domain.SubscriptionSW;
import gp.cnusambe.service.dto.SubscriptionSWDto;
import org.springframework.data.domain.*;

import java.util.Date;
import java.util.List;

public class SubscriptionSWFixture {
    public static final Long SW_ID = 1L;
    public static final String LATEST_UPDATER_ID = "201902699";
    public static final String NEW_LATEST_UPDATER_ID = "996209102";
    public static final String SW_TYPE = "A";
    public static final String SW_MANUFACTURER = "B";
    public static final String SW_NAME = "C";
    public static final String NEW_SW_NAME = "Z";
    public static final String USAGE_RANGE = "D";
    public static final String LICENSE = "E";
    public static final Date DATE = new Date();
    private static final int PAGEABLE_PAGE = 0;
    private static final int PAGEABLE_SIZE = 9;
    private static final Sort PAGEABLE_SORT = Sort.by("latestUpdateDate").descending();
    public static final List<SubscriptionSW> LIST_OF_SSW = List.of(responseSsw(), responseSsw_ForTest2(), responseSsw_ForTest3());

    public static SubscriptionSWDto requestSswDto() {
        return new SubscriptionSWDto(null, LATEST_UPDATER_ID, SW_TYPE, SW_MANUFACTURER,
                SW_NAME, USAGE_RANGE, LICENSE, null, DATE, DATE);
    }

    public static SubscriptionSWDto requestSswDto_ForUpdate() {
        return new SubscriptionSWDto(SW_ID, NEW_LATEST_UPDATER_ID, NEW_SW_NAME, SW_MANUFACTURER,
                SW_NAME, USAGE_RANGE, LICENSE, DATE, DATE, DATE);
    }

    public static SubscriptionSWDto responseSswDto() {
        return new SubscriptionSWDto(SW_ID, LATEST_UPDATER_ID, SW_TYPE, SW_MANUFACTURER,
                SW_NAME, USAGE_RANGE, LICENSE, DATE, DATE, DATE);
    }

    public static SubscriptionSWDto responseSswDto_ForUpdate() {
        return new SubscriptionSWDto(SW_ID, NEW_LATEST_UPDATER_ID, NEW_SW_NAME, SW_MANUFACTURER,
                SW_NAME, USAGE_RANGE, LICENSE, DATE, DATE, DATE);
    }

    public static SubscriptionSW responseSsw() {
        return new SubscriptionSW(SW_ID, LATEST_UPDATER_ID, SW_TYPE, SW_MANUFACTURER,
                SW_NAME, USAGE_RANGE, LICENSE, DATE, DATE, DATE);
    }

    public static SubscriptionSW responseSsw_ForUpdate() {
        return new SubscriptionSW(SW_ID, NEW_LATEST_UPDATER_ID, NEW_SW_NAME, SW_MANUFACTURER,
                SW_NAME, USAGE_RANGE, LICENSE, DATE, DATE, DATE);
    }

    private static SubscriptionSW responseSsw_ForTest2() {
        return new SubscriptionSW(2L, LATEST_UPDATER_ID, "", SW_MANUFACTURER,
                SW_NAME, USAGE_RANGE, LICENSE, DATE, DATE, DATE);
    }

    private static SubscriptionSW responseSsw_ForTest3() {
        return new SubscriptionSW(3L, LATEST_UPDATER_ID, "", SW_MANUFACTURER,
                SW_NAME, USAGE_RANGE, LICENSE, DATE, DATE, DATE);
    }

    public static Pageable pageable() {
        return PageRequest.of(PAGEABLE_PAGE, PAGEABLE_SIZE, PAGEABLE_SORT);
    }

    public static Page<SubscriptionSW> pageOfSW_ForSearch() {
        return new PageImpl<>(List.of(responseSsw()), pageable(), 1);
    }

    public static Page<SubscriptionSW> pageOfSW() {
        return new PageImpl<>(LIST_OF_SSW, pageable(), 1);
    }
}
