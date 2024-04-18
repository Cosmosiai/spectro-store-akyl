package engine.spectro.service;

import engine.spectro.entity.BasketEntity;
import engine.spectro.entity.UserEntity;
import engine.spectro.repository.BasketRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    private BasketRepo basketRepo;

    public void save(BasketEntity basket){
        if (basketRepo.findByModelAndProductIdAndTypeAndUser(basket.getModel(),basket.getProductId(),basket.getType(), basket.getUser())==null){
            basketRepo.save(basket);
        }
    }

    public void delete(BasketEntity basket){
        BasketEntity basket1 = basketRepo.findByModelAndProductIdAndTypeAndUser(basket.getModel(),basket.getProductId(),basket.getType(), basket.getUser());
        if (basket1!=null){
            basketRepo.delete(basket1);
        }
    }

    public void delete(Long id){
        BasketEntity basket1 = basketRepo.findById(id).get();
        basketRepo.delete(basket1);
    }

    public List<BasketEntity> getAllBasket(UserEntity user){
        return basketRepo.findAllByUser(user);
    }

}
