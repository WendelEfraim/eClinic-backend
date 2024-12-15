package br.com.eClinic.api.paciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eClinic.modelo.paciente.Paciente;
import br.com.eClinic.modelo.paciente.PacienteService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/paciente")
@CrossOrigin
public class PacienteController {
  
  @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Paciente obterPorID(@PathVariable Long id) {
        return pacienteService.obterPorID(id);
    }

    @PostMapping
    //public ResponseEntity<Paciente> save(@RequestBody PacienteRequest pacienteRequest) {

    public ResponseEntity<Paciente> save(@RequestBody @Valid PacienteRequest pacienteRequest) throws Exception {

        Paciente paciente = pacienteService.save(pacienteRequest.build());
        return new ResponseEntity<Paciente>(paciente, HttpStatus.CREATED);
    }
        @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable("id") Long id, @RequestBody PacienteRequest request) {

        pacienteService.update(id, request.build());
        return ResponseEntity.ok().build();
    }
}
