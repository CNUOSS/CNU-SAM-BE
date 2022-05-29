package gp.cnusambe.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.domain.LectureMap;
import gp.cnusambe.domain.SWInLectureSW;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LectureMapDto {
    private Long id;
    private String swManufacturer;
    private String swName;

    public LectureMapDto(LectureMap lectureMap, SWInLectureSW swInLecture){
        this.id = lectureMap.getId();
        this.swManufacturer = swInLecture.getSwManufacturer();
        this.swName = swInLecture.getSwName();
    }
}
