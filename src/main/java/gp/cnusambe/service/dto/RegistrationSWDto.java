package gp.cnusambe.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegistrationSWDto {
    private Long id;
    private String latestUpdaterId;
    private String swManufacturer;
    private String swName;
    private Date latestUpdateDate;
    private Boolean isManaged;
}
