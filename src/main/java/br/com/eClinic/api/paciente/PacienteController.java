package br.com.eClinic.api.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eClinic.modelo.paciente.Paciente;
import br.com.eClinic.modelo.paciente.PacienteService;


@RestController
@RequestMapping("/api/paciente")
@CrossOrigin
public class PacienteController {
  
  @Autowired
    private PacienteService pacienteService;

    @PostMapping
    //public ResponseEntity<Paciente> save(@RequestBody PacienteRequest pacienteRequest) {

    public ResponseEntity<Paciente> save(@RequestBody PacienteRequest pacienteRequest) throws Exception {

        Paciente paciente = pacienteService.save(pacienteRequest.build());
        return new ResponseEntity<Paciente>(paciente, HttpStatus.CREATED);
    }
}
