package gp.cnusambe.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.domain.SWInLectureSW;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LectureSWRequest {
    private String ownerId;
    private String year;
    private String semester;
    private String lectureNum;
    private String lectureName;
    private String lectureType;
    private String department;
    private List<SWInLectureSW> sw;
}
