package dio.dio_spring_security_jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dio.dio_spring_security_jwt.model.User;
import dio.dio_spring_security_jwt.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;// repository "comunica" para o BD o usuario a ser salvo
  @Autowired
  private PasswordEncoder encoder;// mas para salvar ele habilita uma criptografia na senha

  public void createUser(User user) {
    String pass = user.getPassword();// senha informada pelo usuario
    // criptografando antes de salvar no banco
    user.setPassword(encoder.encode(pass));// pega a senha e criptografa ela
    repository.save(user);// salva
  }
}
