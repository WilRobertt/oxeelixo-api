package br.com.ifpe.oxeelixo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.ifpe.oxeelixo.modelo.acesso.Autenticacao;

import static org.springframework.security.config.Customizer.withDefaults;
import br.com.ifpe.oxeelixo.modelo.acesso.AutenticacaoService;
import br.com.ifpe.oxeelixo.seguranca.jwt.JwtAuthenticationEntryPoint;
import br.com.ifpe.oxeelixo.seguranca.jwt.JwtTokenAuthenticationFilter;
import br.com.ifpe.oxeelixo.seguranca.jwt.JwtTokenProvider;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Configuration
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger*/**",
            "/v2/api-docs",
            "/webjars/**",
            "/routes/**",
            "/favicon.ico",
            "/ws/**",
            "/delifacil/**/dadosPedidoNew/**",
            "/delifacil/image/**"
    };

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
            AutenticacaoService userDetailService) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic(basic -> basic.disable()).csrf(csrf -> csrf.disable()).cors(withDefaults()).sessionManagement(management -> management
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)).exceptionHandling(handling -> handling
                .authenticationEntryPoint(authenticationEntryPoint)).authorizeHttpRequests(requests -> requests

                .requestMatchers(AUTH_WHITELIST).permitAll()

                .requestMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/empresa").permitAll()




                .anyRequest()
                .hasAnyAuthority(Autenticacao.ROLE_USUARIO, Autenticacao.ROLE_EMPRESA, Autenticacao.ROLE_USUARIO)
                .and().addFilterBefore(
                new JwtTokenAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class));
                
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}
