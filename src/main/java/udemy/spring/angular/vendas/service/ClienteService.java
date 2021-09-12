package udemy.spring.angular.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import udemy.spring.angular.vendas.model.entity.Cliente;
import udemy.spring.angular.vendas.respository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente Salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente FindById(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado")
                );
    }

    public void deletarCliente(Long id) {
        clienteRepository.findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return Void.TYPE;
                }
                )
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado")
                );
    }

    public void atualizaCliente(Long id, Cliente clienteAtualizado){
        clienteRepository.findById(id)
                .map(cliente -> {
                            clienteAtualizado.setId(cliente.getId());
                            return clienteRepository.save(clienteAtualizado);
                        }
                )
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado")
                );
    }

    public List<Cliente> findall() {
        return clienteRepository.findAll();
    }
}
