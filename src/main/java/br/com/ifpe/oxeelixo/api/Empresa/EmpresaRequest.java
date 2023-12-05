package br.com.ifpe.oxeelixo.api.Empresa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    public Empresa build() {

        return Empresa.builder()
                .nomeEmpresa(nomeEmpresa)
                .dataFundacao(dataFundacao)
                .email(email)
                .senha(senha)
                .cnpj(cnpj)
                .build();
    }
}
