package udemy.spring.angular.vendas.model.dto;

import lombok.Data;
import udemy.spring.angular.vendas.model.entity.Cliente;
import udemy.spring.angular.vendas.model.entity.Servico;
import udemy.spring.angular.vendas.service.ClienteService;
import udemy.spring.angular.vendas.util.BigDecimalConverter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class ServicoDTO {

    private Long id;
    @NotEmpty(message = "{campo.descricao.vazio}")
    private String descricao;
    @NotNull(message = "{campo.cliente.vazio}")
    private Long clienteId;
    @NotEmpty(message = "{campo.valor.vazio}")
    private String valor;
    @NotEmpty(message = "{campo.data.vazio}")
    private String data;

    public static Servico generate(ClienteService clienteService, ServicoDTO servicoDTO, BigDecimalConverter converter){
        Cliente cliente = clienteService.FindById(servicoDTO.clienteId);
        Servico servico = new Servico();
        servico.setCliente(cliente);
        servico.setData(LocalDate.parse(servicoDTO.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        servico.setDescricao(servicoDTO.descricao);
        servico.setValor(converter.bigDecimal(servicoDTO.getValor()));
        return servico;
    }
}
