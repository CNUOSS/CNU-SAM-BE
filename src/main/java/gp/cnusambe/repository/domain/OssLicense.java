package gp.cnusambe.repository.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class OssLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_name",nullable = false, length = 50)
    @JsonProperty("license_name")
    private String licenseName;

    @Column(name = "license_url",nullable = false, length = 2083)
    @JsonProperty("license_url")
    private String licenseUrl;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name="license_type_name")
    @JsonProperty("oss_license_type")
    private OssLicenseType ossLicenseType;

    @Builder
    public OssLicense(String licenseName, String licenseUrl, OssLicenseType ossLicenseType){
        this.licenseName = licenseName;
        this.licenseUrl = licenseUrl;
        this.ossLicenseType = ossLicenseType;
    }



}
