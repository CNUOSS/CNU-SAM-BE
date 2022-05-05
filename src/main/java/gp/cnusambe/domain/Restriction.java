package gp.cnusambe.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Restriction {

    @Id
    @Column(length = 50)
    @JsonProperty("restriction_name")
    private String restrictionName;

}
