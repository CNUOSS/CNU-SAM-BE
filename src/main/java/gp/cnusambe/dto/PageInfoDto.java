package gp.cnusambe.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor @AllArgsConstructor
public class PageInfoDto {
    private Long totalElements;
    private boolean last;
    private int totalPages;
    private int size;

    public PageInfoDto makeMetaResponse(Page<OssLicenseDto> licenseDtoPage){
        return PageInfoDto.builder()
                .totalElements(licenseDtoPage.getTotalElements())
                .last(licenseDtoPage.isLast())
                .totalPages(licenseDtoPage.getTotalPages())
                .size(licenseDtoPage.getSize()).build();
    }


}
