package gp.cnusambe.repository.domain;

import gp.cnusambe.service.dto.LectureSWDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class LectureSW {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String ownerId;

    @Column(length = 10, nullable = false)
    private String year;

    @Column(length = 10, nullable = false)
    private String semester;

    @Column(length = 10, nullable = false)
    private String lectureNum;

    @Column(length = 20, nullable = false)
    private String lectureName;

    @Column(length = 10, nullable = false)
    private String lectureType;

    @Column(length = 20, nullable = false)
    private String department;

    @CreationTimestamp
    @Column
    private Date createDate;

    @UpdateTimestamp
    @Column
    private Date latestUpdateDate;

    public LectureSW(LectureSWDto swDto){
        this.ownerId = swDto.getOwnerId();
        this.year = swDto.getYear();
        this.semester = swDto.getSemester();
        this.lectureNum = swDto.getLectureNum();
        this.lectureName = swDto.getLectureName();
        this.lectureType = swDto.getLectureType();
        this.department = swDto.getDepartment();
    }
}
