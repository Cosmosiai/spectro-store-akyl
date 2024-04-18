package engine.spectro.controller;

import engine.spectro.entity.LaptopEntity;
import engine.spectro.enums.GeneralProductEnum;
import engine.spectro.exception.UserNotFoundException;
import engine.spectro.model.LaptopPage;
import engine.spectro.model.LaptopSearchCriteria;
import engine.spectro.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/laptop")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;

    @GetMapping(value = "find-by-id")
    public LaptopEntity getLaptopById(Long id){
        return laptopService.findById(id);
    }

    @GetMapping(value = "find-by-model")
    public LaptopEntity getLaptopByModel(String model){
        return laptopService.findByModel(model);
    }


    @PostMapping(value = "/saveNewLaptop")
    public ResponseEntity saveNewLaptop(@RequestBody LaptopEntity laptop){
        try {
            laptop.setStatus(GeneralProductEnum.available);
            laptopService.save(laptop);
            return ResponseEntity.ok("Сохранил");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка сохранения нового ноутбука");
        }
    }

    @PatchMapping(value = "/updateLaptop")
    public String updateLaptop(@RequestParam("model") String model, @RequestParam("amount") Integer amount) throws Exception {
        try {
            laptopService.update(model, amount);
            return "succsess";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @PatchMapping(value = "/updateLaptopByEntity")
    public String updateLaptop(@RequestBody LaptopEntity laptop) throws Exception {
        try {
            laptopService.update(laptop);
            return "succsess";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @DeleteMapping(value = "/deleteLaptop")
    public String deleteLaptop(@RequestParam("model") String model){
        try {
            laptopService.delete(model);
            return "Success";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<LaptopEntity>> getLaptops( LaptopPage laptopPage, LaptopSearchCriteria laptopSearchCriteria){
        Page<LaptopEntity> p = laptopService.filter(laptopPage,laptopSearchCriteria);
        List<LaptopEntity> pa = p.getContent();
        return new ResponseEntity<>(pa, HttpStatus.OK);
    }

    @GetMapping(value = "/searchModel")
    public ResponseEntity<LaptopEntity> getOneLaptop(String model){
        return new ResponseEntity<>(laptopService.findByModel(model), HttpStatus.OK);
    }

    @PatchMapping(value = "/sell")
    public String sell(@RequestParam("model") String model, @RequestParam("amount") int amount) throws Exception {
        try {
            LaptopEntity l = laptopService.findByModel(model);
            int newAmount = l.getAmount() - amount;
            if (newAmount==0){
                laptopService.update(model,newAmount,GeneralProductEnum.sold_out);
            } else if (newAmount>0) {
                laptopService.update(model,newAmount);
            }
            return "succsess";
        } catch (Exception e){
            throw new Exception(e);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
