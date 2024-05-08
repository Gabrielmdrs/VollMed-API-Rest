package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.medicoDTO.MedicoAtualizarDTO;
import med.voll.api.domain.medico.medicoDTO.MedicoDTO;
import med.voll.api.domain.medico.medicoDTO.MedicoDadosDetalhado;
import med.voll.api.domain.medico.medicoDTO.MedicoListarDTO;
import med.voll.api.domain.medico.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoDTO dadosMedico, UriComponentsBuilder uriBuilder) {
        var medico = service.cadastrar(dadosMedico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoDadosDetalhado(medico));

    }

    @GetMapping
    public ResponseEntity<Page<MedicoListarDTO>> listar(@PageableDefault(size = 10, sort = "nome") Pageable pageable){
         var page = service.listar(pageable);
         return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoListarDTO> detalhar(@PathVariable Long id){
        return ResponseEntity.ok(service.detalhar(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid MedicoAtualizarDTO dados){
        return ResponseEntity.ok(service.atualizar(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }



}
