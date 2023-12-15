package br.com.ifpe.oxeelixo.modelo.empresa;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxeelixo.modelo.acesso.Autenticacao;
import br.com.ifpe.oxeelixo.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Empresa")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa extends EntidadeAuditavel {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Autenticacao autenticacao;

    @Column
    private String nomeEmpresa;

    @Column
    private String email;

    @Column
    private String senha;

    @Column
    private String cnpj;

    @Column
    private String site;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFundacao;
}
