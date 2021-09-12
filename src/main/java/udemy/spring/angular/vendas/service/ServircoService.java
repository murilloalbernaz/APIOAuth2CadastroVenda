package udemy.spring.angular.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import udemy.spring.angular.vendas.model.dto.ServicoDTO;
import udemy.spring.angular.vendas.model.entity.Servico;
import udemy.spring.angular.vendas.respository.ServicoRepository;
import udemy.spring.angular.vendas.util.BigDecimalConverter;

import javax.validation.Valid;
import java.util.List;

@Service
public class ServircoService {

    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private BigDecimalConverter converter;

    public Servico salvar(ServicoDTO servicoDTO){
        Servico servico = ServicoDTO.generate(clienteService,servicoDTO, converter);
        return servicoRepository.save(servico);
    }

    public List<Servico> findAll(){
        return servicoRepository.findAll();
    }

    public Servico findById(Long id){
        return servicoRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço Não Encontrado")
                );
    }

    public void deletarServico(Long id){
        servicoRepository.findById(id)
                .map(
                        servico ->{
                                servicoRepository.delete(servico);
                        return Void.TYPE;
                        }).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço Não Encontrado")
        );
    }

    public void atualizarServico(Servico servico){
        servicoRepository.findById(servico.getId())
                .map(
                        servicof -> {
                            servico.setId(servicof.getId());
                            servicoRepository.save(servico);
                            return Void.TYPE;
                        }).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço Não Encontrado")
        );
    }

    public List<Servico> pesquisar(String nome, Integer mes){
        return servicoRepository.pesquisarMesCliente(nome,mes);
    }
}
