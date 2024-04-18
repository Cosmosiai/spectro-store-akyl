package engine.spectro.controller;

import engine.spectro.entity.*;
import engine.spectro.enums.ProductType;
import engine.spectro.repository.BasketRepo;
import engine.spectro.service.BasketService;
import engine.spectro.service.LaptopService;
import engine.spectro.service.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Basket")
public class BasketController {
    private BasketService basketService;

    private LaptopService laptopService;
    private PhoneService phoneService;
    private final BasketRepo basketRepo;
    private LaptopController laptopController;
    private PhoneController phoneController;

    public BasketController(BasketRepo basketRepo) {
        this.basketRepo = basketRepo;
    }

    @PostMapping(value = "/add-to-basket")
    public <T extends ProductEntity> String addToBasket(T t, UserEntity user) {
        BasketEntity basket = new BasketEntity();
        if (t.getClass().equals(LaptopEntity.class)){
            basket.setType(ProductType.laptop);
            basket.setLaptop(laptopService.findByModel(t.getModel()));
        }
        else if (t.getClass().equals(PhoneEntity.class)){
            basket.setType(ProductType.phone);
            basket.setPhone(phoneService.findByModel(t.getModel()));
        }
        basket.setModel(t.getModel());
        basket.setProductId(t.getId());
        basket.setAmount(t.getAmount());
        basket.setUser(user);
        basket.setImage(t.getImage());
        basketService.save(basket);
        return "success";
    }

    @DeleteMapping(value = "/delete-basket-by-id")
    public <T extends ProductEntity> String delete(Long id) {
        basketService.delete(id);
        return "success";
    }

    @GetMapping(value = "/get-all-in-basket")
    public List<BasketEntity> getAllInBasket(UserEntity user) {
        return basketService.getAllBasket(user);
    }

    @GetMapping(value = "get-product-in-basket")
    public ProductEntity getProdFromBusket(BasketEntity basket){
        if (basket.getType()==ProductType.laptop)return laptopService.findById(basket.getProductId());
        else return  phoneService.findById(basket.getProductId());
    }

    @DeleteMapping(value = "/sell")
    public String sell(BasketEntity basket) throws Exception {
        if(basket.getType()==ProductType.laptop){
            return laptopController.sell(basket.getModel(),basket.getAmount());
        } else if (basket.getType()==ProductType.phone) {
            return phoneController.sell(basket.getModel(),basket.getAmount());
        }
        return "error";
    }
}
