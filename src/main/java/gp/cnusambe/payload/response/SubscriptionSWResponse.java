package gp.cnusambe.payload.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.domain.SubscriptionSW;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SubscriptionSWResponse {
    private Long id;
    private String updatorId;
    private String swType;
    private String swManufacturer;
    private String swName;
    private String usageRange;
    private String license;
    private Date latestUpdateDate;
    private Date expireDate;
    private Date firstSubscribeDate;

    public SubscriptionSWResponse(SubscriptionSW sw){
        this.id = sw.getId();
        this.updatorId = sw.getLatestUpdatorId();
        this.swType = sw.getSwType();
        this.swManufacturer = sw.getSwManufacturer();
        this.swName = sw.getSwName();
        this.usageRange = sw.getUsageRange();
        this.license = sw.getLicense();
        this.latestUpdateDate = sw.getLatestUpdateDate();
        this.expireDate = sw.getExpireDate();
        this.firstSubscribeDate = sw.getFirstSubscribeDate();
    }
}
