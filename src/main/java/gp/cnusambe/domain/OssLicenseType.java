package gp.cnusambe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class OssLicenseType {
    @Id
    @Column(length = 10)
    private static String license_type_name;

}
