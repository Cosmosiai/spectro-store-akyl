package engine.spectro.repository;

import engine.spectro.entity.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaptopRepo extends JpaRepository<LaptopEntity, Long>, JpaSpecificationExecutor<LaptopEntity> {
    LaptopEntity findByModel(String model);
    Optional<LaptopEntity> findById(Long id);
}
