package br.com.ifpe.oxeelixo.modelo.acesso;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ifpe.oxeelixo.modelo.acesso.Autenticacao;
import br.com.ifpe.oxeelixo.modelo.acesso.AutenticacaoRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private AutenticacaoRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Autenticacao save(Autenticacao user) {

	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	user.setHabilitado(Boolean.TRUE);
	return repository.save(user);
    }
    
    @Transactional
    public Autenticacao findByUsername(String username) {

	return repository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
	return repository.findByUsername(username);
    }

}
