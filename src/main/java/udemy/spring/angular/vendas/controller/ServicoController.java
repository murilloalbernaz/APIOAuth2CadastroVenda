package udemy.spring.angular.vendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import udemy.spring.angular.vendas.model.dto.ServicoDTO;
import udemy.spring.angular.vendas.model.entity.Servico;
import udemy.spring.angular.vendas.service.ServircoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/servico")
//@CrossOrigin("*")
public class ServicoController {

    @Autowired
    private ServircoService servircoService;


    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Servico salvarServico(@Valid @RequestBody ServicoDTO servicoDto){
        return servircoService.salvar(servicoDto);
    }

    @GetMapping("/")
    public List<Servico> findALL(){
        return servircoService.findAll();
    }

    @GetMapping("{id}")
    public Servico buscaServico(@PathVariable Long id){
        return servircoService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarServico(@PathVariable Long id){
        servircoService.deletarServico(id);
    }

    @GetMapping("/mesNome")
    public List<Servico> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes") Integer mes
    ){
        nome = '%'+nome+'%';
        return servircoService.pesquisar(nome, mes);
    }
}
