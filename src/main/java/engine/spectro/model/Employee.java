package engine.spectro.model;

import engine.spectro.entity.EmployeeEntity;
import lombok.Getter;
import lombok.Setter;

public class Employee {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String numberphone;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String company;
    @Getter
    @Setter
    private String role;

    public static Employee toError(String er){
        Employee error = new Employee();
        error.setEmail(er);
        return error;
    }
    public static Employee toModel(EmployeeEntity entity){
        Employee model = new Employee();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setEmail(entity.getEmail());
        model.setNumberphone(entity.getNumberphone());
        model.setPassword(entity.getPassword());
        model.setCompany(entity.getCompany());
        model.setRole(entity.getRole());
        return model;
    }
}
