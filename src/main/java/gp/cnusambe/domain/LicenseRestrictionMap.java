package gp.cnusambe.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LicenseRestrictionMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //column : oss_license_id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonProperty("oss_license")
    private OssLicense ossLicense;

    //column : restriction
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name="restriction_name")
    @JsonProperty("restriction")
    private Restriction restriction;


    public LicenseRestrictionMap(OssLicense license, Restriction restriction) {
        this.ossLicense = license;
        this.restriction = restriction;
    }

}
