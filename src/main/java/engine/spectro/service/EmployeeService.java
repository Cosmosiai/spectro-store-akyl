package engine.spectro.service;

import engine.spectro.entity.EmployeeEntity;
import engine.spectro.exception.UserAlreadyExistException;
import engine.spectro.exception.UserNotFoundException;
import engine.spectro.model.Employee;
import engine.spectro.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    public EmployeeEntity registerEmployee(EmployeeEntity supplier) throws UserAlreadyExistException {
        if(employeeRepo.findByUsername(supplier.getUsername()) != null){
            throw new UserAlreadyExistException("Такой поставщик уже зарегестрирован");
        }
        else if(employeeRepo.findByEmail(supplier.getEmail())!=null){
            throw new UserAlreadyExistException("Такой email уже занят");
        }
        else if(employeeRepo.findByNumberphone(supplier.getNumberphone())!=null){
            throw new UserAlreadyExistException("Такой номер уже занят");
        }
        return employeeRepo.save(supplier);
    }

    public Employee getOne(Long id) throws UserNotFoundException {
        EmployeeEntity supplier = employeeRepo.findById(id).get();
        if(supplier == null){
            throw new UserNotFoundException("Пользователь не найден");
        }
        return Employee.toModel(supplier);
    }

    public Employee login(EmployeeEntity employee) throws UserAlreadyExistException {
        EmployeeEntity repairm =  employeeRepo.findByEmail(employee.getEmail());
        EmployeeEntity repairp = employeeRepo.findByPassword(employee.getPassword());
        if(repairm.equals(repairp)){
            return Employee.toModel(repairm);
        }
        return Employee.toError("error");
//        RepairmanEntity repid = repairmanRepo.findById(repairm.getId()).get();
    }

    public EmployeeEntity update(EmployeeEntity user, Long id) throws UserNotFoundException {
        EmployeeEntity supplier = employeeRepo.findById(id).get();
        if(supplier == null){
            throw new UserNotFoundException("Пользователь не найден");
        }
        supplier.setUsername(user.getUsername());
        supplier.setNumberphone(user.getNumberphone());
        supplier.setPassword(user.getPassword());
        return employeeRepo.save(supplier);
    }
    public Long delete(Long id){
        employeeRepo.deleteById(id);
        return id;
    }
}
