package gp.cnusambe.repository;

import gp.cnusambe.domain.Restriction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestrictionRepository extends JpaRepository<Restriction,String> {
    List<Restriction> findAll();
}
