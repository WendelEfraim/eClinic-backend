package br.com.eClinic.modelo.administrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class GerenciamentoMedicoService {
    @Autowired
    private GerenciamentoMedicoRepository repository;

    @Transactional
    public GerenciadorMedico save(GerenciadorMedico gerenciadormedico) {

        gerenciadormedico.setHabilitado(Boolean.TRUE);
        return repository.save(gerenciadormedico);
    }

    public List<GerenciadorMedico> listarTodos() {

        return repository.findAll();
    }

    public GerenciadorMedico obterPorID(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com ID: " + id));
    }

    @Transactional
    public void update(Long id, GerenciadorMedico gerenciadormedicoAlterado) {

        GerenciadorMedico gerenciadormedico = repository.findById(id).get();
        gerenciadormedico.setNomeCompleto(gerenciadormedicoAlterado.getNomeCompleto());
        gerenciadormedico.setEspecialidades(gerenciadormedicoAlterado.getEspecialidades());

        repository.save(gerenciadormedico);
    }

    public List<GerenciadorMedico> filtroMedicos(String especialidade, Long id, String nome) {
        if (especialidade != null && !especialidade.isEmpty()) {
            return repository.findByEspecialidades(especialidade);
        }
        if (id != null) {
            return repository.findById(id).map(List::of).orElse(List.of());
        }
        if (nome != null && !nome.isEmpty()) {
            return repository.findByNomeCompletoContaining(nome);
        }
        return listarTodos();
    }

    @Transactional
    public void delete(Long id) {

        GerenciadorMedico gerenciadorMedico = repository.findById(id).get();
        gerenciadorMedico.setHabilitado(Boolean.FALSE);

        repository.save(gerenciadorMedico);
    }
}
