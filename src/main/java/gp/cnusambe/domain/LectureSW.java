package gp.cnusambe.domain;

import gp.cnusambe.dto.LectureSWDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(length = 5, nullable = false)
    private String year;

    @Column(length = 5, nullable = false)
    private String semester;

    @Column(length = 5, nullable = false)
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

    @ManyToMany
    @JoinTable(name="LectureMap",
    joinColumns = @JoinColumn(name="lectureSWId"),
    inverseJoinColumns = @JoinColumn(name="RegistrationSWId")
    )
    private List<RegistrationSW> registrationSW;

    public LectureSW(LectureSWDto swDto){
        this.ownerId = swDto.getOwnerId();
        this.year = swDto.getYear();
        this.semester = swDto.getSemester();
        this.lectureNum = swDto.getLectureNum();
        this.lectureName = swDto.getLectureName();
        this.lectureType = swDto.getLectureType();
        this.department = swDto.getDepartment();
        this.registrationSW = swDto.getRegistrationSW();
    }
}
