package gp.cnusambe.repository;

import gp.cnusambe.domain.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, String> {
    List<Manufacturer> findAll();
}
