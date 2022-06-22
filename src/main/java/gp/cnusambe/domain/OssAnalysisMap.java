package gp.cnusambe.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OssAnalysisMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String ossLocation;

    @Column(nullable = false, length = 100)
    private String ossName;

    @Column(nullable = false, length = 20)
    private String ossVersion;

    @Column(nullable = false, length = 2083)
    private String ossUrl;

    @Column(length = 50)
    private String licenseName;

    @Column(length = 2083)
    private String licenseUrl;

    @Column(length = 10)
    private String licenseTypeName;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn
    @JsonProperty("version")
    private Version version;
}
