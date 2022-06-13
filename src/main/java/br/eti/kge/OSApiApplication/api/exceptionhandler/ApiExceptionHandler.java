/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eti.kge.OSApiApplication.api.exceptionhandler;

import br.eti.kge.OSApiApplication.domain.exception.DomainException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author adler
 */

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, 
            HttpHeaders headers, 
            HttpStatus status, 
            WebRequest request) {
        
        ProblemaException problema = new ProblemaException();
        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos inválidos! tente novamente.");
        problema.setDataHora(LocalDateTime.now());
        
        List<ProblemaException.CampoProblema> camposComErro = new ArrayList<ProblemaException.CampoProblema>();
        
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nomeCampo = ((FieldError)error).getField();
            String mensagemCampo = error.getDefaultMessage();
            
            camposComErro.add(new ProblemaException.CampoProblema(nomeCampo, mensagemCampo));
        }
        
        problema.setCampos(camposComErro);
        
        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }
    
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        ProblemaException problema = new ProblemaException();
        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(LocalDateTime.now());
        
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
    
}
