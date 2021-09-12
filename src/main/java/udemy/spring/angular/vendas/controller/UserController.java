package udemy.spring.angular.vendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import udemy.spring.angular.vendas.model.entity.User;
import udemy.spring.angular.vendas.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@Valid @RequestBody User user){
        userService.cadastro(user);
    }
}
