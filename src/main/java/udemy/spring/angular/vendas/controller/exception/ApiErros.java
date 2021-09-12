package udemy.spring.angular.vendas.controller.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErros {
    @Getter
    private List<String> erros;

    public ApiErros(List<String> erros){
        this.erros=erros;
    }

    public ApiErros(String erro){
        this.erros = Arrays.asList(erro);
    }
}
