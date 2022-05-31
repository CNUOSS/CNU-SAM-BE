package gp.cnusambe.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gp.cnusambe.domain.RegistrationSW;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LectureSWDto {
    private Long id;
    private String ownerId;
    private String year;
    private String semester;
    private String lectureNum;
    private String lectureName;
    private String lectureType;
    private String department;
    private Date createDate;
    private Date latestUpdateDate;
    private List<RegistrationSW> registrationSW;
}
