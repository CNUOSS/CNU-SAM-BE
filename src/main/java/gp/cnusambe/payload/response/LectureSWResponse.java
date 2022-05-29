package gp.cnusambe.payload.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.dto.LectureMapDto;
import gp.cnusambe.dto.LectureSWDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LectureSWResponse {
    private LectureSWDto lectureSW;
    private List<LectureMapDto> sw;
}
