package br.com.eClinic.modelo.administrador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenciamentoMedicoRepository extends JpaRepository<GerenciadorMedico, Long> {

    List<GerenciadorMedico> findByEspecialidades(String especialidades);

    List<GerenciadorMedico> findByNomeCompletoContaining(String nomeCompleto);

}