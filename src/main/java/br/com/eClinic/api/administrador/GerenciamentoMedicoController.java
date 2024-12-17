package br.com.eClinic.api.administrador;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eClinic.modelo.administrador.GerenciadorMedico;
import br.com.eClinic.modelo.administrador.GerenciamentoMedicoService;

@RestController
@RequestMapping("api/gerenciadormedicos")
@CrossOrigin
public class GerenciamentoMedicoController {
    @Autowired
    private GerenciamentoMedicoService gerenciamentoMedicoService;

    @PostMapping
    public ResponseEntity<GerenciadorMedico> save(@RequestBody GerenciamentoMedicoRequest gerenciamentoMedicoRequest) {
        GerenciadorMedico gerenciadorMedico = gerenciamentoMedicoService.save(gerenciamentoMedicoRequest.build());
        return new ResponseEntity<GerenciadorMedico>(gerenciadorMedico, HttpStatus.CREATED);
    }

    @GetMapping("/filtro") // filtro no gerenciamento de medicos
    public List<GerenciadorMedico> filtroMedicos(@RequestParam(required = false) String especialidade,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome) {
        return gerenciamentoMedicoService.filtroMedicos(especialidade, id, nome);
    }

    @GetMapping
    public List<GerenciadorMedico> listarTodos() {
        return gerenciamentoMedicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public GerenciadorMedico obterPorID(@PathVariable Long id) {
        return gerenciamentoMedicoService.obterPorID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GerenciadorMedico> update(@PathVariable("id") Long id,
            @RequestBody GerenciamentoMedicoRequest request) {

        gerenciamentoMedicoService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        gerenciamentoMedicoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
// Requisição para postman
// {
// "nomeCompleto":"Juliana Lucena",
// "especialidades":"neurologista infantil"
// }