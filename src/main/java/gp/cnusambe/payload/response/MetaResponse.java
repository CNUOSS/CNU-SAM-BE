package gp.cnusambe.payload.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.dto.OssLicenseDto;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor @AllArgsConstructor
public class MetaResponse {

    private Long totalElements;
    private boolean last;
    private int totalPages;
    private int size;

    public MetaResponse makeMetaResponse(Page<OssLicenseDto> licenseDtoPage){
        return MetaResponse.builder()
                .totalElements(licenseDtoPage.getTotalElements())
                .last(licenseDtoPage.isLast())
                .totalPages(licenseDtoPage.getTotalPages())
                .size(licenseDtoPage.getSize()).build();
    }
}
