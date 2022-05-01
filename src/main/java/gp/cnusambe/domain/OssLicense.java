package gp.cnusambe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class OssLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String license_name;

    @Column(nullable = false, length = 2083)
    private String license_url;

    @ManyToOne
    @JoinColumn(name="license_type_name")
    private OssLicenseType license_type_name;
}
