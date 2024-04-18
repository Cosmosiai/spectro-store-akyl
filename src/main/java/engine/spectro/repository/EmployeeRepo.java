package engine.spectro.repository;

import engine.spectro.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<EmployeeEntity, Long> {
    EmployeeEntity findByUsername(String username);
    EmployeeEntity findByEmail(String email);
    EmployeeEntity findByNumberphone(String number);
    EmployeeEntity findByPassword(String password);
}
