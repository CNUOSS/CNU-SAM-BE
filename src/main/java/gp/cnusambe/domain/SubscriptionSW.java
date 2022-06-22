package gp.cnusambe.domain;

import gp.cnusambe.dto.SubscriptionSWDto;
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
public class SubscriptionSW {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String latestUpdaterId;

    @Column(length = 10)
    private String swType;

    @Column(length = 20, nullable = false)
    private String swManufacturer;

    @Column(length = 20, nullable = false)
    private String swName;

    @Column(length = 20)
    private String usageRange;

    @Column(length = 20)
    private String license;

    @UpdateTimestamp
    @Column
    private Date latestUpdateDate;

    @Column
    private Date expireDate;

    @Column
    private Date firstSubscribeDate;

    public SubscriptionSW(SubscriptionSWDto swDto) {
        this.latestUpdaterId = swDto.getLatestUpdaterId();
        this.swType = swDto.getSwType();
        this.swManufacturer = swDto.getSwManufacturer();
        this.swName = swDto.getSwName();
        this.usageRange = swDto.getUsageRange();
        this.license = swDto.getLicense();
        this.expireDate = swDto.getExpireDate();
        this.firstSubscribeDate = swDto.getFirstSubscribeDate();
    }
}
