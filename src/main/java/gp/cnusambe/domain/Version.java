package gp.cnusambe.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "version_name", nullable = false, length = 30)
    private String versionName;

    @Column(name = "version_description", nullable = true, length = 100)
    private String versionDescription;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn
    @JsonProperty("project")
    private Project project;

}
