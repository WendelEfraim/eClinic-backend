package br.com.eClinic.api.paciente;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.eClinic.modelo.paciente.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequest {

  private String nomeCompleto;

  @JsonFormat(pattern = "dd/MM/yyyy") // mascara de campos
  private LocalDate dataNascimento;

  private String email;

  private String senha;

  private String enderecoCidade;

  private String enderecoUf;

  private String especialidades;

  private String cpf;

  public Paciente build() {

    return Paciente.builder()
        .nomeCompleto(nomeCompleto)
        .dataNascimento(dataNascimento)
        .email(email)
        .senha(senha)
        .enderecoCidade(enderecoCidade)
        .enderecoUf(enderecoUf)
        .cpf(cpf)
        .build();

  }
}
