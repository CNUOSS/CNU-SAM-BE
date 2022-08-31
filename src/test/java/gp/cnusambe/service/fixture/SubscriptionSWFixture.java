package gp.cnusambe.service.fixture;

import gp.cnusambe.domain.SubscriptionSW;
import gp.cnusambe.dto.SubscriptionSWDto;
import org.springframework.data.domain.*;

import java.util.Date;

public class SubscriptionSWFixture {
    public static final Long SW_ID = 1L;
    public static final String LATEST_UPDATER_ID = "201902699";
    public static final String SW_TYPE = "A";
    public static final String SW_MANUFACTURER = "B";
    public static final String SW_NAME = "C";
    public static final String USAGE_RANGE = "D";
    public static final String LICENSE = "E";
    public static final Date DATE = new Date();
    private static final int PAGEABLE_PAGE = 0;
    private static final int PAGEABLE_SIZE = 9;
    private static final Sort PAGEABLE_SORT = Sort.by("latestUpdateDate").descending();

    public static SubscriptionSWDto requestSswDto() {
        return new SubscriptionSWDto(null, LATEST_UPDATER_ID, SW_TYPE, SW_MANUFACTURER,
                SW_NAME, USAGE_RANGE, LICENSE, null, DATE, DATE);
    }

    public static SubscriptionSWDto responseSswDto() {
        return new SubscriptionSWDto(SW_ID, LATEST_UPDATER_ID, SW_TYPE, SW_MANUFACTURER,
                SW_NAME, USAGE_RANGE, LICENSE, DATE, DATE, DATE);
    }
    
    public static SubscriptionSW responseSsw() {
        return new SubscriptionSW(SW_ID, LATEST_UPDATER_ID, SW_TYPE, SW_MANUFACTURER,
                SW_NAME, USAGE_RANGE, LICENSE, DATE, DATE, DATE);
    }
}
