package gp.cnusambe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class LectureMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long lectureSWId;

    @Column(nullable = false)
    private Long registrationSWId;

    @Column
    private Date latestUpdateDate;

    public LectureMap(Long lectureSWId, Long registrationSWId, Date latestUpdateDate) {
        this.lectureSWId = lectureSWId;
        this.registrationSWId = registrationSWId;
        this.latestUpdateDate = latestUpdateDate;
    }
}
