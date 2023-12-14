package br.com.ifpe.oxeelixo.modelo.acesso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
