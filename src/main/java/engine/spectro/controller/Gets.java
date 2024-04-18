package engine.spectro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class Gets {
    @GetMapping("/")
    public String hello(){
        return "Hello";
    }
}
