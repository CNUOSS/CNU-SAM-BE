package gp.cnusambe.repository.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Department {
    @Id
    @Column(length = 20, nullable = false, unique = true)
    private String department;
}

