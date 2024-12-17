package br.com.eClinic.api.medico;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.eClinic.modelo.medico.Medico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicoRequest {

  private String nomeCompleto;

  @JsonFormat(pattern = "dd/MM/yyyy") // mascara de campos
  private LocalDate dataNascimento;

  private String email;

  private String senha;

  private String enderecoCidade;

  private String enderecoUf;

  private String descricao;

  private String crm;

  private String especialidades;

  public Medico build() {

    return Medico.builder()
        .nomeCompleto(nomeCompleto)
        .dataNascimento(dataNascimento)
        .email(email)
        .senha(senha)
        .enderecoCidade(enderecoCidade)
        .enderecoUf(enderecoUf)
        .descricao(descricao)
        .especialidades(especialidades)
        .crm(crm)
        .build();

  }

}
