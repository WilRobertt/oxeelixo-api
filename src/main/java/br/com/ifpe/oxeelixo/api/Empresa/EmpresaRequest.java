package br.com.ifpe.oxeelixo.api.Empresa;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxeelixo.modelo.acesso.Autenticacao;
import br.com.ifpe.oxeelixo.modelo.empresa.Empresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequest {

    private String nomeEmpresa;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFundacao;

    private String email;

    private String senha;

    private String cnpj;

    private String site;

    private String password;

    public Empresa buildEmpresa() {

        return Empresa.builder()
                .nomeEmpresa(nomeEmpresa)
                .dataFundacao(dataFundacao)
                .email(email)
                .senha(senha)
                .cnpj(cnpj)
                .site(site)
                .build();
    }
    public Autenticacao buildAutenticacao() {
	
        return Autenticacao.builder()
           .username(email)
           .password(password)
           .roles(Arrays.asList(Autenticacao.ROLE_USUARIO))
           .build();
         }
}
