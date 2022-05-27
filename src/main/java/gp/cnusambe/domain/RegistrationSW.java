package gp.cnusambe.domain;

import gp.cnusambe.payload.request.RegistrationSWRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class RegistrationSW {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String latestUpdaterId;

    @Column(length = 20, nullable = false)
    private String swManufacturer;

    @Column(length = 20, nullable = false)
    private String swName;

    @UpdateTimestamp
    @Column
    private Date latestUpdateDate;

    @Column
    private boolean isManaged;

    public RegistrationSW(RegistrationSWRequest request) {
        this.latestUpdaterId = request.getLatestUpdaterId();
        this.swManufacturer = request.getSwManufacturer();
        this.swName = request.getSwName();
        this.isManaged = request.getIsManaged();
    }
}
