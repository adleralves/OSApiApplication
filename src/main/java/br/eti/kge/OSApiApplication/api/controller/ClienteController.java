package br.eti.kge.OSApiApplication.api.controller;

import br.eti.kge.OSApiApplication.api.exceptionhandler.ProblemaException;
import br.eti.kge.OSApiApplication.domain.exception.DomainException;
import br.eti.kge.OSApiApplication.domain.model.Cliente;
import br.eti.kge.OSApiApplication.domain.repository.ClienteRepository;
import br.eti.kge.OSApiApplication.domain.service.ClienteService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author adler
 */

@RestController
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/clientes")
    public List<Cliente> listas () {
        
        return clienteRepository.findAll();
        //return clienteRepository.findByNome("Adler");
        //return clienteRepository.findByEmail("jamily@teste.com");
    }
    
    @GetMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
        
        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /*
    @GetMapping("/clientes/email/{clienteEmail}")
    public List<Cliente> buscarPorEmail(@PathVariable String clienteEmail) {
        return clienteRepository.acharEmail(clienteEmail);

    }
    */
    
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        
        return clienteService.salvar(cliente);
    }
    
    @PutMapping("clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteID,
                                             @RequestBody Cliente cliente) {
        //Verifica se o cliente existe
        if(!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        
        cliente.setId(clienteID);
        cliente = clienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
        
        //@PutMapping mapeia o método para PUT e espera receber uma clienteID na
        // URI da requisição.
        
        //@PathVariable vincula o {clienteID} do @PutMapping com a variável clienteID
        
        //@RequestBody faz o mapeamento do corpo da requisição com um objeto cliente.
        
        //O IF verifica se clienteID existe na base (existsById) e caso não
        //tenha retorna um 404 not
        
        //Logo após o IF definimos um ID para o objeto cliente (lembre-se que
        //ele vem sem ID no corpo da requisição) e chamamos o mesmo método SAVE
        //para atualizar.
    }
    
    @DeleteMapping("/clientes/{clienteID}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID) {
        //Verifica se cliente existe ou não
        
        if(!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        
        clienteService.excluir(clienteID);
        return ResponseEntity.noContent().build();
    }
    
}
