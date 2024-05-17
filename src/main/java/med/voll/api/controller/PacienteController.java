package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.medicoDTO.MedicoListarDTO;
import med.voll.api.domain.paciente.DTO.PacienteAtualizaDTO;
import med.voll.api.domain.paciente.DTO.PacienteCadastroDTO;
import med.voll.api.domain.paciente.DTO.PacienteListaDTO;
import med.voll.api.domain.paciente.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid PacienteCadastroDTO pacienteDados, UriComponentsBuilder uriBuilder){
        var paciente = pacienteService.cadastrar(pacienteDados);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteListaDTO>> listar(@PageableDefault(size = 10, sort = "nome") Pageable pageable){
        var page = pacienteService.listar(pageable);
        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteListaDTO> detalhar(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.detalhar(id));
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid PacienteAtualizaDTO pacienteDados){
        pacienteService.atualizar(pacienteDados);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        pacienteService.deletar(id);
        return ResponseEntity.accepted().build();

    }

}
