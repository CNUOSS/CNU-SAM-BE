package gp.cnusambe.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LectureSWList {
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
    private RegistrationSW registrationSW;

    public LectureSWList(LectureSW lectureSW, RegistrationSW registrationSW){
        this.id = lectureSW.getId();
        this.ownerId = lectureSW.getOwnerId();
        this.year = lectureSW.getYear();
        this.semester = lectureSW.getSemester();
        this.lectureNum = lectureSW.getLectureNum();
        this.lectureName = lectureSW.getLectureName();
        this.lectureType = lectureSW.getLectureType();
        this.department = lectureSW.getDepartment();
        this.createDate = lectureSW.getCreateDate();
        this.latestUpdateDate = lectureSW.getLatestUpdateDate();
        this.registrationSW = registrationSW;
    }
}

