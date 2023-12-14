package br.com.ifpe.oxeelixo.api.Acesso;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxeelixo.modelo.acesso.Autenticacao;
import br.com.ifpe.oxeelixo.modelo.acesso.AutenticacaoService;
import br.com.ifpe.oxeelixo.seguranca.jwt.JwtTokenProvider;


@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class AutenticaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public Map<Object, Object> signin(@RequestBody AutenticacaoRequest data) {

        try {

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));

            Autenticacao autenticacao = this.autenticacaoService.findByUsername(data.getUsername());
            String token = jwtTokenProvider.createToken(autenticacao.getUsername(), autenticacao.getRoles());
            String refreshToken = jwtTokenProvider.createRefreshToken(autenticacao.getUsername());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", autenticacao.getUsername());
            model.put("token", token);
            model.put("refresh", refreshToken);

            return model;

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

}

