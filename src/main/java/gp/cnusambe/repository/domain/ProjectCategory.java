package gp.cnusambe.repository.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProjectCategory {

    @Id
    @Column(length = 10)
    private String projectCategoryName;
}
