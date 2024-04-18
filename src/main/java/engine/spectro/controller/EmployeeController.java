package engine.spectro.controller;

import engine.spectro.entity.EmployeeEntity;
import engine.spectro.exception.UserAlreadyExistException;
import engine.spectro.exception.UserNotFoundException;
import engine.spectro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @CrossOrigin
    @PostMapping
    public ResponseEntity reg(@RequestBody EmployeeEntity employee){
        try {
            employeeService.registerEmployee(employee);
            return ResponseEntity.ok("Сохранил");
        }catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody EmployeeEntity employee){
        try {
            return ResponseEntity.ok(employeeService.login(employee));
        }catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошв");
        }
    }
    @CrossOrigin
    @GetMapping
    public ResponseEntity getOneClient(@RequestParam Long id){
        try{
            return ResponseEntity.ok(employeeService.getOne(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin
    @PatchMapping("{id}")
    public ResponseEntity updates(@PathVariable("id") Long id, @RequestBody EmployeeEntity user){
        try {
            employeeService.update(user, id);
            return ResponseEntity.ok("Изменил");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла оышв");
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity deleteClient(@PathVariable Long id){
        try{
            return ResponseEntity.ok(employeeService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
