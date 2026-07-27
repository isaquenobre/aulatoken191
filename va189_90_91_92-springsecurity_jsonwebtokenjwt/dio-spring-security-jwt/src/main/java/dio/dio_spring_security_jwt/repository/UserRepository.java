package dio.dio_spring_security_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dio.dio_spring_security_jwt.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {// JpaRepository é uma interface do Spring Data
                                                                      // JPA que fornece métodos para realizar operações
                                                                      // de banco de dados em entidades JPA. Ela é
                                                                      // parametrizada com a classe da entidade (User) e
                                                                      // o tipo do identificador da entidade (Integer).
                                                                      // Isso significa que o UserRepository terá acesso
                                                                      // a métodos como save, findById, findAll,
                                                                      // deleteById, entre outros, para manipular
                                                                      // objetos User no banco de dados.
  @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.username= (:username)")
  public User findByUsername(@Param("username") String username);

  boolean existsByUsername(String username);
}
