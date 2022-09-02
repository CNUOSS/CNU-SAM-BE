package gp.cnusambe.controller.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SubscriptionSWUpdateRequest {
    private Long id;
    private String latestUpdaterId;
    private String swType;
    private String swManufacturer;
    private String swName;
    private String usageRange;
    private String license;
    private Date latestUpdateDate;
    private Date expireDate;
    private Date firstSubscribeDate;
}
