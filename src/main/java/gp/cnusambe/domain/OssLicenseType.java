package gp.cnusambe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class OssLicenseType {

    @Id
    @Column(length = 10)
    private String license_type_name;

    public OssLicenseType(String license_type_name){
        this.license_type_name = license_type_name;
    }

}
