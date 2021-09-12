package udemy.spring.angular.vendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import udemy.spring.angular.vendas.model.entity.Cliente;
import udemy.spring.angular.vendas.service.ClienteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
//@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvaCliente(@Valid @RequestBody Cliente cliente){
        cliente.tiraFormatacao(cliente.getCpf());
        return clienteService.Salvar(cliente);
    }

    @GetMapping("/")
    public List<Cliente> findall(){
        return clienteService.findall();
    }

    @GetMapping("/{id}")
    public Cliente acharPorId(@PathVariable Long id){
        return clienteService.FindById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id){
        clienteService.deletarCliente(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        clienteService.atualizaCliente(id,cliente);
    }

}
