package udemy.spring.angular.vendas.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    @NotEmpty(message = "Descrição Não pode Ficar Vazia")
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "Selecione um Cliente")
    private Cliente cliente;

    @Positive(message = "Digite Algum Valor")
    private BigDecimal valor;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

}
