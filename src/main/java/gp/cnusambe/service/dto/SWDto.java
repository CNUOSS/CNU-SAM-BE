package gp.cnusambe.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.repository.domain.SW;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SWDto {
    private long id;
    private boolean isSubscriptionSW;
    private String swManufacturer;
    private String swName;
    private String license;

    public SWDto(SW sw, boolean isSubscriptionSW) {
        this.id = sw.getId();
        this.isSubscriptionSW = isSubscriptionSW;
        this.swManufacturer = sw.getSwManufacturer();
        this.swName = sw.getSwName();
        this.license = sw.getLicense();
    }
}
