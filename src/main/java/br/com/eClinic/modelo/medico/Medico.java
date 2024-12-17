package br.com.eClinic.modelo.medico;

import java.time.LocalDate;
import org.hibernate.annotations.SQLRestriction;
import br.com.eClinic.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // ele torna a classe em uma entidade manipulada pela JPA
@Table(name = "Medico") // a partir dessa classe ele vai virar uma tabela no banco
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Medico extends EntidadeAuditavel {

  @Column
  private String nomeCompleto;

  @Column
  private LocalDate dataNascimento;

  @Column
  private String email;

  @Column
  private String senha;

  @Column
  private String enderecoCidade;

  @Column
  private String enderecoUf;

  @Column
  private String descricao;

  @Column
  private String especialidades;

  @Column
  private String crm;
}