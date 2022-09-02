package gp.cnusambe.controller.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SubscriptionSWRequest {
    private String latestUpdaterId;
    private String swType;
    private String swManufacturer;
    private String swName;
    private String usageRange;
    private String license;
    private Date expireDate;
    private Date firstSubscribeDate;
}
