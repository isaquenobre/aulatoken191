package dio.dio_spring_security_jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.dio_spring_security_jwt.model.User;
import dio.dio_spring_security_jwt.service.UserService;

@RestController // essa classe é uma RestController
@RequestMapping("/users") // todas as rotas/rescursos abaixo inicializarão com o prefixo users
public class UserController {
  @Autowired
  private UserService service;// a requisição abaixo solicita este serviço (service) a inclusão do usuario

  @PostMapping // a solicitação da requisição será via POST no body
  public void postUser(@RequestBody User user) {
    service.createUser(user);
  }
}

