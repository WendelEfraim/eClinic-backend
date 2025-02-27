package br.com.eClinic.modelo.medico;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.eClinic.modelo.acesso.Perfil;
import br.com.eClinic.modelo.acesso.PerfilRepository;
import br.com.eClinic.modelo.acesso.UsuarioService;
import jakarta.transaction.Transactional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;

    @Autowired
    @Lazy // Adicionando @Lazy para evitar dependência circular
    private UsuarioService usuarioService;

    @Autowired
    private PerfilRepository perfilUsuarioRepository;

    public List<Medico> listarPorEspecialidade(Long idEspecialidade) {
        return repository.findByEspecialidadeId(idEspecialidade);
    }

    @Transactional
    public Medico save(Medico medico) {

        usuarioService.save(medico.getUsuario());

        for (Perfil perfil : medico.getUsuario().getRoles()) {
            perfil.setHabilitado(Boolean.TRUE);
            perfilUsuarioRepository.save(perfil);
        }

        medico.setHabilitado(Boolean.TRUE);
        return repository.save(medico);
    }

    public List<Medico> listarTodos() {

        return repository.findAll();
    }

    public Medico obterPorID(Long id) {

        return repository.findById(id).get();
    }

    public Medico obterPorCrm(String crm) {

        return repository.findByCrm(crm);
    }

    
    @Transactional
        public void delete(Long id) {
        Medico medico = repository.findById(id).get();
        medico.setHabilitado(Boolean.FALSE);
        repository.save(medico);
   }


    @Transactional
    public void update(Long id, Medico medicoAlterado) {

        Medico medico = repository.findById(id).get();
        medico.setEspecialidade(medicoAlterado.getEspecialidade());
        medico.setNomeCompleto(medicoAlterado.getNomeCompleto());
        medico.setDataNascimento(medicoAlterado.getDataNascimento());
        medico.setEmail(medicoAlterado.getEmail());
        medico.setSenha(medicoAlterado.getSenha());
        medico.setEnderecoCidade(medicoAlterado.getEnderecoCidade());
        medico.setDescricao(medicoAlterado.getDescricao());
        medico.setCrm(medicoAlterado.getCrm());
        medico.setLinkMeet(medicoAlterado.getLinkMeet());

        repository.save(medico);
    }
}
