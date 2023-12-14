package br.com.ifpe.oxeelixo.api.Usuario;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxeelixo.modelo.acesso.Autenticacao;
import br.com.ifpe.oxeelixo.modelo.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

   private String nome;

   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dataNascimento;

   private String email;

   private String senha;

   @NotBlank(message = "A senha é de preenchimento obrigatório")
   private String password;


   public Usuario build() {

      return Usuario.builder()
            .autenticacao(buildAutenticao())
            .nome(nome)
            .dataNascimento(dataNascimento)
            .email(email)
            .senha(senha)
            .build();
   }
   public Autenticacao buildAutenticacao() {
	
      return Autenticacao.builder()
         .username(email)
         .password(password)
         .roles(Arrays.asList(Usuario.ROLE_CLIENTE))
         .build();
       }
   
}
