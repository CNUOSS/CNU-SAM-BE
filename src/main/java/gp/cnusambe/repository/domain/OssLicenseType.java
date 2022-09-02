package gp.cnusambe.repository.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class OssLicenseType {

    @Id
    @Column(name = "license_type_name",length = 20)
    @JsonProperty("license_type_name")
    private String licenseTypeName;


}
