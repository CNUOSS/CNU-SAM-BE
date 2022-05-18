package gp.cnusambe.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_description", nullable = true, length = 100)
    private String projectDescription;

    @Column(name = "project_name", nullable = false, length = 50)
    private String projectName;

    @Column(name = "project_status", nullable = false, length = 5)
    private String projectStatus;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn
    @JsonProperty("oss_license")
    private OssLicense license;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name="project_category_name")
    @JsonProperty("project_category")
    private ProjectCategory projectCategory;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @JsonProperty("user")
    private User user;
}
