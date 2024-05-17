package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity handleBadRequest(BadRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity handleEntityNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Id não encontrado!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity handleArgumentNotValid(MethodArgumentNotValidException exception){
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(fieldError -> new DadosErroValidacao(fieldError)).toList());
    }

    @ExceptionHandler(ValidacaoException.class)
    private ResponseEntity validacao(ValidacaoException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}


