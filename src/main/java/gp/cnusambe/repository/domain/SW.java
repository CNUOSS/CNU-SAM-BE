package gp.cnusambe.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class SW {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(length = 20, nullable = false)
    protected String swManufacturer;

    @Column(length = 20, nullable = false)
    protected String swName;

    @Column(length = 20)
    protected String license;
}
