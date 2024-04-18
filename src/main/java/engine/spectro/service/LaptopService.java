package engine.spectro.service;

import engine.spectro.entity.LaptopEntity;
import engine.spectro.enums.GeneralProductEnum;
import engine.spectro.exception.UserAlreadyExistException;
import engine.spectro.exception.UserNotFoundException;
import engine.spectro.model.LaptopPage;
import engine.spectro.model.LaptopSearchCriteria;
import engine.spectro.repository.LaptopCriteriaRepo;
import engine.spectro.repository.LaptopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepo laptopRepo;
    @Autowired
    private LaptopCriteriaRepo laptopCriteriaRepo;

    public void save(LaptopEntity laptop) throws UserAlreadyExistException {
        if(laptopRepo.findByModel(laptop.getModel())==null){
            if(laptop.getAmount()>0){
                laptop.setStatus(GeneralProductEnum.available);
            }
        }
        laptopRepo.save(laptop);
    }

    public void update(LaptopEntity laptop) throws UserNotFoundException {
        LaptopEntity laptop1 = laptopRepo.findByModel(laptop.getModel());
        if(laptopRepo.findByModel(laptop.getModel())!=null){
            if (laptop.getAmount()>=0)laptop1.setAmount(laptop.getAmount());
            if(laptop.getCamera()!=null)laptop1.setCamera(laptop.getCamera());
            if(laptop.getConnectors()!=null)laptop1.setConnectors(laptop.getConnectors());
            if(laptop.getDescription()!=null)laptop1.setDescription(laptop.getDescription());
            if(laptop.getDimensions()!=null)laptop1.setDimensions(laptop.getDimensions());
            if(laptop.getHeadphoneJack()!=null)laptop1.setHeadphoneJack(laptop.getHeadphoneJack());
            if(laptop.getHousingMaterial()!=null)laptop1.setHousingMaterial(laptop.getHousingMaterial());
            if(laptop.getMemory()!=null)laptop1.setMemory(laptop.getMemory());
            if(laptop.getManufacturer()!=null)laptop1.setManufacturer(laptop.getManufacturer());
            if(laptop.getModel()!=null)laptop1.setModel(laptop.getModel());
            if(laptop.getNumberOfCores()!=null)laptop1.setNumberOfCores(laptop.getNumberOfCores());
            if(laptop.getOS()!=null)laptop1.setOS(laptop.getOS());
            if(laptop.getPrice()!=null)laptop1.setPrice(laptop.getPrice());
            if(laptop.getProcessorType()!=null)laptop1.setProcessorType(laptop.getProcessorType());
            if(laptop.getProducingCountry()!=null)laptop1.setProducingCountry(laptop.getProducingCountry());
            if(laptop.getRAM()!=null)laptop1.setRAM(laptop.getRAM());
            if(laptop.getSpeaker()!=null)laptop1.setSpeaker(laptop.getSpeaker());
            if(laptop.getScreenSize()!=null)laptop1.setScreenSize(laptop.getScreenSize());
            if(laptop.getScreenResolution()!=null)laptop1.setScreenResolution(laptop.getScreenResolution());
            if(laptop.getVideoCard()!=null)laptop1.setVideoCard(laptop.getVideoCard());
            if(laptop.getStatus()!=null)laptop1.setStatus(laptop.getStatus());
            laptopRepo.save(laptop1);
        }else{
            throw new UserNotFoundException("Can not update laptop list. It doesn't exist");
        }
    }

    public void update(String model, Integer amount) throws UserNotFoundException {
        LaptopEntity laptop1 = laptopRepo.findByModel(model);
        if(laptop1!=null){
            laptop1.setAmount(amount);
            laptopRepo.save(laptop1);
        }else{
            throw new UserNotFoundException("Can not update laptop list. It doesn't exist");
        }
    }

    public void update(String model, Integer amount, GeneralProductEnum status) throws UserNotFoundException {
        LaptopEntity laptop1 = laptopRepo.findByModel(model);
        if(laptop1!=null){
            laptop1.setAmount(amount);
            laptop1.setStatus(status);
            laptopRepo.save(laptop1);
        }else{
            throw new UserNotFoundException("Can not update laptop list. It doesn't exist");
        }
    }

    public void delete(Long id) throws UserNotFoundException {
        laptopRepo.findById(id).get();
        LaptopEntity laptop = laptopRepo.findById(id).get();
        laptop.setStatus(GeneralProductEnum.deleted);
    }

    public void delete(String model) throws UserNotFoundException {
        if(laptopRepo.findByModel(model)!=null) {
            LaptopEntity laptop = laptopRepo.findByModel(model);
            laptop.setStatus(GeneralProductEnum.deleted);
            laptopRepo.save(laptop);
        }else throw new UserNotFoundException("Can not update laptop list. It doesn't exist");
    }

    public Page<LaptopEntity> filter(LaptopPage page, LaptopSearchCriteria laptopSearchCriteria){
        return laptopCriteriaRepo.findAllWithFilters(page,laptopSearchCriteria);
    }

    public LaptopEntity findByModel(String model){
        return laptopRepo.findByModel(model);
    }
    public LaptopEntity findById(Long id){
        return laptopRepo.findById(id).get();
    }

}
