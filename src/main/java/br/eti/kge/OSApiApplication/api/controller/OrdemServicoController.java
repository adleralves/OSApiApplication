/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eti.kge.OSApiApplication.api.controller;

import br.eti.kge.OSApiApplication.domain.model.OrdemServico;
import br.eti.kge.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.eti.kge.OSApiApplication.domain.service.OrdemServicoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adler
 */
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }
    
    @GetMapping("/listar")
    public List<OrdemServico> listar (OrdemServico ordemServico) {
        return ordemServicoRepository.findAll();
    }
    
    @DeleteMapping("/{ordemServID}")
    public ResponseEntity<Void> excluir (@PathVariable Long ordemServID) {
        
        if(!ordemServicoRepository.existsById(ordemServID)) {
            return ResponseEntity.notFound().build();
        }
        ordemServicoService.excluir(ordemServID);
        return ResponseEntity.noContent().build();
    }
   
}