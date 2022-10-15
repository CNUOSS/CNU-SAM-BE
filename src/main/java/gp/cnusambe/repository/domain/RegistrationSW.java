package gp.cnusambe.repository.domain;

import gp.cnusambe.service.dto.RegistrationSWDto;
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
public class RegistrationSW extends SW{
    @Column(length = 10, nullable = false)
    private String latestUpdaterId;

    @UpdateTimestamp
    @Column
    private Date latestUpdateDate;

    @Column(nullable = false)
    private Boolean isManaged;

    public RegistrationSW(String swManufacturer, String swName, String license){
        this.latestUpdaterId = "manager";
        this.swManufacturer = swManufacturer;
        this.swName = swName;
        this.license =license;
        this.isManaged = false;
    }

    public RegistrationSW(RegistrationSWDto swDto) {
        this.latestUpdaterId = swDto.getLatestUpdaterId();
        this.swManufacturer = swDto.getSwManufacturer();
        this.swName = swDto.getSwName();
        this.license =swDto.getLicense();
        this.isManaged = swDto.getIsManaged();
    }

    public void updateRegistrationSW(RegistrationSWDto swDto){
        this.latestUpdaterId = swDto.getLatestUpdaterId();
        this.swManufacturer = swDto.getSwManufacturer();
        this.swName = swDto.getSwName();
        this.license =swDto.getLicense();
        this.isManaged = swDto.getIsManaged();
    }
}
