package br.com.eClinic.modelo.paciente;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import br.com.eClinic.modelo.acesso.Usuario;
import br.com.eClinic.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // ele torna a classe em uma entidade manipulada pela JPA
@Table(name = "Paciente") // a partir dessa classe ele vai virar uma tabela no banco
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends EntidadeAuditavel {

  @OneToOne
  @JoinColumn(nullable = false)
  private Usuario usuario;
  @Column(nullable = false, length = 100)
  private String nomeCompleto;

  @Column
  private LocalDate dataNascimento;

  @Column(nullable = false, unique = true)
  private String cpf;

  @Column(nullable = false, length = 100)
  private String email;

  @Column(nullable = false, length = 100)
  private String senha;

  @Column(nullable = false, length = 100)
  private String enderecoCidade;

  @Column(nullable = false, length = 2)
  private String enderecoUf;
}
