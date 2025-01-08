package br.com.eClinic.api.medico;

import java.time.LocalDate;
import java.util.Arrays;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.eClinic.modelo.acesso.Perfil;
import br.com.eClinic.modelo.acesso.Usuario;
import br.com.eClinic.modelo.medico.Medico;
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
public class MedicoRequest {

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

  @Length(max = 100, message = "A descricao deverá ter no máximo {max} caracteres")
  private String descricao;

  @NotBlank(message = "Uso do Crm obrigatorio!")
  @Length(min = 10, max = 10, message = "O Crm deverá ter no máximo {max} caracteres")
  private String crm;

  public Usuario buildUsuario() {
    return Usuario.builder()
        .username(crm)
        .password(senha)
        .roles(Arrays.asList(new Perfil(Perfil.ROLE_FUNCIONARIO_MEDICO)))
        .build();
  }

  public Medico build() {

      return Medico.builder()
          .usuario(buildUsuario())
          .nomeCompleto(nomeCompleto)
          .dataNascimento(dataNascimento)
          .email(email)
          .senha(senha)
          .enderecoCidade(enderecoCidade)
          .enderecoUf(enderecoUf)
          .descricao(descricao)
          .crm(crm)
          .build();
  
    }

}
