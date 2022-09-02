package gp.cnusambe.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.repository.domain.SubscriptionSW;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SimpleSubscriptionSWDto {
    private String swManufacturer;
    private String swName;
    private String license;

    public SimpleSubscriptionSWDto(SubscriptionSW sw){
        this.swManufacturer = sw.getSwManufacturer();
        this.swName = sw.getSwName();
        this.license = sw.getLicense();
    }
}
