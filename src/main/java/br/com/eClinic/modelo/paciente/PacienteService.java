package br.com.eClinic.modelo.paciente;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eClinic.modelo.acesso.Perfil;
import br.com.eClinic.modelo.acesso.PerfilRepository;
import br.com.eClinic.modelo.acesso.UsuarioService;
import br.com.eClinic.service.EmailService;
import jakarta.transaction.Transactional;

@Service
public class PacienteService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilRepository perfilUsuarioRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PacienteRepository repository;

    @Transactional
    public Paciente save(Paciente paciente) {

       usuarioService.save(paciente.getUsuario());

       for (Perfil perfil : paciente.getUsuario().getRoles()) {
           perfil.setHabilitado(Boolean.TRUE);
           perfilUsuarioRepository.save(perfil);
       }

        paciente.setHabilitado(Boolean.TRUE);
        return repository.save(paciente);
    }

    @Transactional
    public void update(Long id, Paciente pacienteAlterado) {
        Paciente paciente = repository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    
        boolean senhaAlterada = !paciente.getSenha().equals(pacienteAlterado.getSenha());
    
        paciente.setNomeCompleto(pacienteAlterado.getNomeCompleto());
        paciente.setDataNascimento(pacienteAlterado.getDataNascimento());
        paciente.setEmail(pacienteAlterado.getEmail());
        paciente.setCpf(pacienteAlterado.getCpf());
        paciente.setEnderecoCidade(pacienteAlterado.getEnderecoCidade());
        paciente.setEnderecoUf(pacienteAlterado.getEnderecoUf());
    
        if (senhaAlterada) {
            paciente.setSenha(pacienteAlterado.getSenha());
        }
    
        repository.save(paciente);
    
        // Envia o e-mail apenas se a senha foi alterada
        if (senhaAlterada) {
            emailService.enviarEmailTexto(
                paciente.getEmail(),
                "eClinic - Recuperação de senha",
                "http://localhost:5173/recuperaçãodesenha"
            );
        }
    }

     @Transactional
        public void delete(Long id) {
        Paciente paciente = repository.findById(id).get();
        paciente.setHabilitado(Boolean.FALSE);
        repository.save(paciente);
   }

    public List<Paciente> listarTodos() {

        return repository.findAll();
    }

    public Paciente obterPorID(Long id) {

        return repository.findById(id).get();
    }

    public Paciente obterPorCpf(String cpf) {

        return repository.buscarPorCpf(cpf);
    }

}