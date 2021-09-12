package udemy.spring.angular.vendas.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true, name = "login")
    @NotEmpty(message = "{campo.login.vazio}")
    private String username;

    @Column(name = "senha")
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;
}
