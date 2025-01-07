package br.com.eClinic.api.agendamento;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eClinic.modelo.agendamento.Agendamento;
import br.com.eClinic.modelo.agendamento.AgendamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agendamento")
@CrossOrigin
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService ;

    @PostMapping
        public ResponseEntity<Agendamento> save(@RequestBody @Valid AgendamentoRequest request) {
        
        Agendamento agendamentoNovo = request.build(null);
        Agendamento agendamento = agendamentoService.save(agendamentoNovo);
        return new ResponseEntity<Agendamento>(agendamento, HttpStatus.CREATED);
    }

    @GetMapping
        public List<Agendamento> listarTodos() {
        return agendamentoService.listarTodos();
    }

    @GetMapping("/{id}")
        public Agendamento obterPorID(@PathVariable Long id) {
        return agendamentoService.obterPorID(id);
    }

    @PutMapping("/{id}")
        public ResponseEntity<Agendamento> update(@PathVariable("id") Long id, @RequestBody AgendamentoRequest request) {

        Agendamento agendamentoAtualizado = request.build(agendamentoService.obterPorID(id).getDataAgendmento());
        agendamentoService.update(id,  agendamentoAtualizado);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
       public ResponseEntity<Void> delete(@PathVariable Long id) {
        agendamentoService.delete(id);
       return ResponseEntity.ok().build();
   }
}

