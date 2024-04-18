package engine.spectro.controller;

import engine.spectro.entity.PhoneEntity;
import engine.spectro.enums.GeneralProductEnum;
import engine.spectro.exception.UserNotFoundException;
import engine.spectro.model.PhonePage;
import engine.spectro.model.PhoneSearchCriteria;
import engine.spectro.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping(value = "find-by-id")
    public PhoneEntity getPhoneById(Long id){
        return phoneService.findById(id);
    }

    @GetMapping(value = "find-by-model")
    public PhoneEntity getPhoneByModel(String model){
        return phoneService.findByModel(model);
    }

    @PostMapping(value = "/saveNewPhone")
    public ResponseEntity saveNewPhone(@RequestBody PhoneEntity phone){
        try {
            phone.setStatus(GeneralProductEnum.available);
            phoneService.save(phone);
            return ResponseEntity.ok("Сохранил");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка сохранения нового телефона");
        }
    }

    @PatchMapping(value = "/updatePhone")
    public String updatePhone(@RequestParam("model") String model, @RequestParam("amount") Integer amount) throws Exception {
        try {
            phoneService.update(model, amount);
            return "succsess";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
    @PatchMapping(value = "/updatePhoneByEntity")
    public String updatePhone(@RequestBody PhoneEntity phone) throws Exception {
        try {
            phoneService.update(phone);
            return "succsess";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @DeleteMapping(value = "/deletePhone")
    public String deletePhone(@RequestParam("model") String model){
        try {
            phoneService.delete(model);
            return "Success";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/deletePhone-by-id")
    public String deletePhone(@RequestParam("id") Long id){
        try {
            phoneService.delete(id);
            return "Success";
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<PhoneEntity>> getPhones(PhonePage phonePage, PhoneSearchCriteria phoneSearchCriteria){
        Page<PhoneEntity> p = phoneService.filter(phonePage,phoneSearchCriteria);
        List<PhoneEntity> pa = p.getContent();
        System.out.println(pa.get(pa.toArray().length-1).toString());
        System.out.println(pa.toArray().length-1);
        return new ResponseEntity<>(pa, HttpStatus.OK);
    }

    @GetMapping(value = "/searchModel")
    public ResponseEntity<PhoneEntity> getOnePhone(@RequestParam(name = "model") String model){
        return new ResponseEntity<>(phoneService.findByModel(model), HttpStatus.OK);
    }

    @PatchMapping(value = "/sell")
    public String sell(@RequestParam("model") String model, @RequestParam("amount") int amount) throws Exception {
        try {
            PhoneEntity l = phoneService.findByModel(model);
            int newAmount = l.getAmount() - amount;
            if (newAmount==0){
                phoneService.update(model,newAmount, GeneralProductEnum.sold_out);
            } else if (newAmount>0) {
                phoneService.update(model,newAmount);
            }
            return "succsess";
        } catch (Exception e){
            throw new Exception(e);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
