package gp.cnusambe.domain;

import gp.cnusambe.dto.RegistrationSWDto;
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

    @Column(nullable = false)
    private Boolean isManaged;

    public RegistrationSW(RegistrationSWDto swDto) {
        this.latestUpdaterId = swDto.getLatestUpdaterId();
        this.swManufacturer = swDto.getSwManufacturer();
        this.swName = swDto.getSwName();
        this.isManaged = swDto.getIsManaged();
    }
}
