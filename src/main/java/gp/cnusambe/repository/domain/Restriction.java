package gp.cnusambe.repository.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
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
@JsonRootName("restriction")
public class Restriction {

    @Id
    @Column(length = 50)
    @JsonProperty("restriction_name")
    private String restrictionName;


}
