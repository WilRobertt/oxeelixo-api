package br.com.ifpe.oxeelixo.modelo.usuario;

import java.time.LocalDate;

import org.hibernate.annotations.FetchMode;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxeelixo.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends EntidadeAuditavel {

   @ManyToOne
   @JoinColumn(nullable = false)
   private Usuario usuario;

   @Column
   private String nome;

   @Column
   private String email;

   @Column
   private String senha;

   @Column
   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dataNascimento;

}
