package br.com.eClinic.modelo.paciente;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eClinic.modelo.acesso.Perfil;
import br.com.eClinic.modelo.acesso.PerfilRepository;
import br.com.eClinic.modelo.acesso.UsuarioService;
import jakarta.transaction.Transactional;

@Service
public class PacienteService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilRepository perfilUsuarioRepository;

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

        Paciente paciente = repository.findById(id).get();
        paciente.setNomeCompleto(pacienteAlterado.getNomeCompleto());
        paciente.setDataNascimento(pacienteAlterado.getDataNascimento());
        paciente.setCpf(pacienteAlterado.getCpf());
        paciente.setSenha(pacienteAlterado.getSenha());
        paciente.setEnderecoCidade(pacienteAlterado.getEnderecoCidade());
        paciente.setEnderecoUf(pacienteAlterado.getEnderecoUf());

        repository.save(paciente);
    }

    public List<Paciente> listarTodos() {

        return repository.findAll();
    }

    public Paciente obterPorID(Long id) {

        return repository.findById(id).get();
    }
}