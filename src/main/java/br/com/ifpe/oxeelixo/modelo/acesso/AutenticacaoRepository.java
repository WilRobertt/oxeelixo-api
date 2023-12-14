package br.com.ifpe.oxeelixo.modelo.acesso;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AutenticacaoRepository extends JpaRepository<Autenticacao, Long>  {
        Autenticacao findByUsername(String username);

}
