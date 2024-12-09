package br.com.eClinic.modelo.paciente;

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
@Table(name = "Paciente") // a partir dessa classe ele vai virar uma tabela no banco
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends EntidadeAuditavel {
  @Column
  private String nomeCompleto;

  @Column
  private LocalDate dataNascimento;

  @Column
  private String cpf;

  @Column
  private String email;

  @Column
  private String senha;

  @Column
  private String enderecoCidade;

  @Column
  private String enderecoUf;

    // Método para exibir o menu do paciente
    public void exibirMenu() {
      System.out.println("Menu do Paciente");
    
  }
  


 
}
