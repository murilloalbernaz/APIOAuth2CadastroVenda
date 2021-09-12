package udemy.spring.angular.vendas.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import udemy.spring.angular.vendas.controller.exception.ApiErros;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleValidationErros(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();
        List<String> erros =result.getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErros(erros);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex){
        String messagemErro = ex.getMessage();
        HttpStatus codigoStatus = ex.getStatus();
        ApiErros erros = new ApiErros(messagemErro);
        return new ResponseEntity(erros,codigoStatus);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleSqlIntegrity(SQLIntegrityConstraintViolationException sqlex) {
        ApiErros erros = new ApiErros("");
        if (sqlex.getMessage().toUpperCase().contains("USER"))
            erros = new ApiErros("Login Ja cadastrado");
        else
            if (sqlex.getMessage().toUpperCase().contains("CLIENTE"))
                erros = new ApiErros("Cliente Ja Cadastrado");
        return erros;
    }
}
