package gp.cnusambe.repository.domain;

import gp.cnusambe.service.dto.SubscriptionSWDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionSW extends SW {

    @Column(length = 10, nullable = false)
    private String latestUpdaterId;

    @Column(length = 10)
    private String swType;

    @Column(length = 20)
    private String usageRange;

    @UpdateTimestamp
    @Column
    private Date latestUpdateDate;

    @Column
    private Date expireDate;

    @Column
    private Date firstSubscribeDate;

    public SubscriptionSW(SubscriptionSWDto swDto) {
        this.id = swDto.getId();
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
