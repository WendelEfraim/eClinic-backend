package br.com.eClinic.api.paciente;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;



import br.com.eClinic.modelo.paciente.Paciente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequest {
  
  @NotBlank(message = "O nome é de preenchimento obrigatório")
  @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")
  private String nomeCompleto;


  @JsonFormat(pattern = "dd/MM/yyyy") // mascara de campos
  private LocalDate dataNascimento;


  @Email(message = "Preencha um email valido")
  private String email;


  @NotBlank(message = "O uso da senha é obrigatorio")
  private String senha;


  @NotBlank(message = "Digite seu endereco")
  private String enderecoCidade;


  @NotBlank(message = "o  UF é de preenchimento obrigatorio")
  @Length(min = 2, max = 2, message = "O UF deverá ter no máximo {max} caracteres")
  private String enderecoUf;


  @NotBlank(message = "O CPF é de preenchimento obrigatório")
  @CPF
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

