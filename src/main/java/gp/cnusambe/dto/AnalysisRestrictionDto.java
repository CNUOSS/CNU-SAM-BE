package gp.cnusambe.dto;

import gp.cnusambe.domain.Restriction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AnalysisRestrictionDto {
    String licenseName;
    List<Restriction> restriction;

}
