package engine.spectro.repository;

import engine.spectro.entity.BasketEntity;
import engine.spectro.entity.UserEntity;
import engine.spectro.enums.ProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepo extends CrudRepository<BasketEntity,Long> {
    Optional<BasketEntity> findById(Long id);
    BasketEntity findByModelAndProductIdAndTypeAndUser(String model, Long productId, ProductType type, UserEntity user);
    List<BasketEntity> findAllByUser(UserEntity user);

}
