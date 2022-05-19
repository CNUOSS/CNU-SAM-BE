package gp.cnusambe.payload.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SubscriptionSWResponse {
    private Long id;
    private String latestUpdatorId;
    private String swType;
    private String swManufacturer;
    private String swName;
    private String usageRange;
    private String license;
    private Date latestUpdateDate;
    private Date expireDate;
    private Date firstSubscribeDate;
}
