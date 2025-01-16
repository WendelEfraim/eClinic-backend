package br.com.eClinic.modelo.agendamento;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

 
      
      //List<Agendamento> findById(Long id); //id de agendamento


      @Query(value = "SELECT a FROM Agendamento a WHERE a.medico.nomeCompleto ilike %:nomeCompleto% ORDER BY a.medico.nomeCompleto")
      List<Agendamento> consultarPorNomeMedico (String nomeCompleto); //nome da entidade médico

      @Query(value = "SELECT a FROM Agendamento a WHERE  a.especialidade.nome ilike %:nome% ORDER BY a.especialidade.nome")
      List<Agendamento> consultarPorNome (String nome); //nome da entidade especialidade


      @Query(value = "SELECT a FROM Agendamento a WHERE a.dataAgendmento = :dataAgendmento AND a.horarioAgendamento = :horarioAgendamento ")
      List<Agendamento> consultarPorDataEHora(LocalDate dataAgendmento, LocalTime horarioAgendamento ); // data e hora 
  
}
