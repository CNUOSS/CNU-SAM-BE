package gp.cnusambe.domain;

import gp.cnusambe.payload.request.SubscriptionSWRequest;
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
    private String latestUpdatorId;

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

    public SubscriptionSW(SubscriptionSWRequest request) {
        this.latestUpdatorId = request.getLatestUpdatorId();
        this.swType = request.getSwType();
        this.swManufacturer = request.getSwManufacturer();
        this.swName = request.getSwName();
        this.usageRange = request.getUsageRange();
        this.license = request.getLicense();
        this.expireDate = request.getExpireDate();
        this.firstSubscribeDate = request.getFirstSubscribeDate();
    }
}
