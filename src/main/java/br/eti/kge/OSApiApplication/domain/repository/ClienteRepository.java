package br.eti.kge.OSApiApplication.domain.repository;

import br.eti.kge.OSApiApplication.domain.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author adler
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeContaining(String nome);
    // List<Cliente> acharEmail(String email);
    Cliente findByEmail(String email);
}
