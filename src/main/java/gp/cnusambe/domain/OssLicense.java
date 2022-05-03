package gp.cnusambe.domain;

import lombok.*;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="license_type_name")
    private OssLicenseType license_type_name;

    @Builder
    public OssLicense(String license_name, String license_url, OssLicenseType license_type_name){
        this.license_name = license_name;
        this.license_url = license_url;
        this.license_type_name = license_type_name;
    }
}
