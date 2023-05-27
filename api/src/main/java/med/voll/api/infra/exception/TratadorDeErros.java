package med.voll.api.infra.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacaoException;

@RestControllerAdvice


public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
     public ResponseEntity TratarErro404(){
        return ResponseEntity.notFound().build();
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)

     public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
      var erros = ex.getFieldErrors();
      return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
     }

     private record DadosErroValidacao(String campo, String mensagem){

      public DadosErroValidacao(FieldError error){
         this(error.getField(), error.getDefaultMessage());
      }

     }

     @ExceptionHandler(ValidacaoException.class)
     public ResponseEntity TratadorErroRegraDeNegocio(ValidacaoException ex){

        return ResponseEntity.badRequest().body(ex.getMessage());
     }


     
    
}


