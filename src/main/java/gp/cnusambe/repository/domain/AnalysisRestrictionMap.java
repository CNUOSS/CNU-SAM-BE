package gp.cnusambe.repository.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class AnalysisRestrictionMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "restriction_name")
    @JsonProperty("restriction_name")
    private Restriction restriction;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn
    @JsonProperty("analysis_map")
    private OssAnalysisMap analysisMap;

    public AnalysisRestrictionMap(Restriction restriction, OssAnalysisMap analysis) {
        this.restriction = restriction;
        this.analysisMap = analysis;
    }
}
