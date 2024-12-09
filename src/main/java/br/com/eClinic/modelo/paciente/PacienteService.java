package br.com.eClinic.modelo.paciente;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Service
public class PacienteService {

    @Autowired
   private PacienteRepository repository;

   @Transactional
   public Paciente save(Paciente paciente) {

       paciente.setHabilitado(Boolean.TRUE);
       return repository.save(paciente);
   }


    private PacienteRepository repository;

    @Transactional
    public Paciente save(Paciente paciente) throws Exception {
        validarCadastroPaciente(paciente);
        return repository.save(paciente);
    }

    public void validarCadastroPaciente(Paciente paciente) throws Exception {
        
        if (!validarNomeCompleto(paciente.getNomeCompleto())) {
            throw new IllegalArgumentException("Formato inválido para o nome completo.");
        }

       
        if (repository.existsByCpf(paciente.getCpf())) {
            throw new IllegalArgumentException("CPF já registrado.");
        }

       
        if (!validarDataNascimento(paciente.getDataNascimento())) {
            throw new IllegalArgumentException("Data de nascimento inválida. A data deve ser no passado.");
        }

       
        if (!validarEmail(paciente.getEmail())) {
            throw new IllegalArgumentException("E-mail inválido. O e-mail deve estar em um formato válido.");
        }

        
        if (!validarSenha(paciente.getSenha())) {
            throw new IllegalArgumentException("Senha inválida. Somente letras (a-z) e números, e no mínimo 8 caracteres.");
        }

        
        if (!validarEnderecoCidade(paciente.getEnderecoCidade())) {
            throw new IllegalArgumentException("Nome da cidade inválido. A cidade deve ter entre 3 e 50 caracteres.");
        }

        
        if (!validarEnderecoUf(paciente.getEnderecoUf())) {
            throw new IllegalArgumentException("UF inválido. A UF deve ter exatamente 2 caracteres.");
        }
    }

   
    private boolean validarNomeCompleto(String nomeCompleto) {
        String regex = "^[A-Za-z\\s]+$"; // somente letras e espaços
        return nomeCompleto.matches(regex);
    }

   
    private boolean validarDataNascimento(LocalDate dataNascimento) {
        return dataNascimento != null && dataNascimento.isBefore(LocalDate.now()); //a data de nascimento deve ser antes de hoje
    }

    
    private boolean validarCpf(String cpf) {
        String regex = "^\\d{11}$"; // o CPF deve ter exatamente 11 dígitos numéricos
        return cpf != null && cpf.matches(regex);
    }

    // Validação do email
    private boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; //formato de Email válido
        return email != null && email.matches(regex);
    }

   
    private boolean validarSenha(String senha) {
        String regex = "^[A-Za-z0-9]{8,}$"; //pelo menos 8 caracteres, somente letras e números
        return senha != null && senha.matches(regex);
    }

    
    private boolean validarEnderecoCidade(String cidade) {
        return cidade != null && cidade.length() >= 3 && cidade.length() <= 50; //cidade deve ter entre 3 e 50 caracteres
    }

   
    private boolean validarEnderecoUf(String uf) {
        String regex = "^[A-Za-z]{2}$"; // UF deve ter exatamente 2 caracteres
        return uf != null && uf.matches(regex);
    }

}
