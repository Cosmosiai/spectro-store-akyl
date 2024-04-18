package engine.spectro.repository;

import engine.spectro.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepo extends JpaRepository<PhoneEntity, Long> {
    Optional<PhoneEntity> findById(Long id);
    PhoneEntity findByModel(String model);
}
