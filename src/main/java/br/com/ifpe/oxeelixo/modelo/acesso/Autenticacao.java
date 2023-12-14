package br.com.ifpe.oxeelixo.modelo.acesso;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxeelixo.util.entity.Entidade;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "Autenticacao")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autenticacao extends Entidade implements UserDetails {

   private static final long serialVersionUID = -2660334839251150243L;

   public static final String ROLE_CLIENTE = "CLIENTE";
   public static final String ROLE_EMPRESA = "EMPRESA";

   @Column(nullable = false, unique = true)
   private String username;

   @JsonIgnore
   @Column(nullable = false)
   private String password;

   @JsonIgnore
   @ElementCollection(fetch = FetchType.EAGER)
   @Builder.Default
   private List<String> roles = new ArrayList<>();

   @JsonIgnore
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
   }

   public boolean isAccountNonExpired() {
       return true;
   }

   public boolean isAccountNonLocked() {
       return true;
   }

   public boolean isCredentialsNonExpired() {
       return true;
   }

   public boolean isEnabled() {
       return getHabilitado();
   }
}

