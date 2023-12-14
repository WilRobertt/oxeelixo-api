package br.com.ifpe.oxeelixo.modelo.usuario;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ifpe.oxeelixo.modelo.acesso.Autenticacao;
import br.com.ifpe.oxeelixo.modelo.acesso.AutenticacaoService;

@Service
public class UsuarioService {
    
    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmailService emailService;


    @Transactional
    public Usuario save(Usuario usuario) {
        
        AutenticacaoService.save(usuario.getUsuario());


        usuario.setHabilitado(Boolean.TRUE);
        usuario.setVersao(1L);
        usuario.setDataCriacao(LocalDate.now());
        Usuario usuarioSalvo=repository.save(usuario);
    }

    //emailService.enviarEmailConfirmacaoCadastroUsuario(usuarioSalvo);


    public List<Usuario> findAll() {

        return repository.findAll();
    }

    public Usuario findById(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Usuario usuarioAlterado) {

        Usuario usuario = repository.findById(id).get();
        usuario.setNome(usuarioAlterado.getNome());
        usuario.setDataNascimento(usuarioAlterado.getDataNascimento());
        usuario.setSenha(usuarioAlterado.getSenha());

        usuario.setVersao(usuario.getVersao() + 1);
        repository.save(usuario);
    }

    @Transactional
    public void delete(Long id) {

        Usuario usuario = repository.findById(id).get();
        usuario.setHabilitado(Boolean.FALSE);
        usuario.setVersao(usuario.getVersao() + 1);

        repository.save(usuario);
    }

}
