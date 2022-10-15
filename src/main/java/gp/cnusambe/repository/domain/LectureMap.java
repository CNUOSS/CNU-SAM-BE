package gp.cnusambe.repository.domain;

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
    private boolean isSubscriptionSW;

    @Column(nullable = false)
    private Long swId;

    @Column(nullable = false)
    private Date latestUpdateDate;

    public LectureMap(Long lectureSWId, boolean isSubscriptionSW, Long swId, Date latestUpdateDate) {
        this.lectureSWId = lectureSWId;
        this.isSubscriptionSW = isSubscriptionSW;
        this.swId = swId;
        this.latestUpdateDate = latestUpdateDate;
    }
}
