package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import med.voll.api.domain.consulta.*;
import med.voll.api.domain.consulta.DTO.DadosAgendamento;
import med.voll.api.domain.consulta.DTO.DadosCancelamento;
import med.voll.api.domain.consulta.DTO.DetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendamentoService agenda;

    @PostMapping
    public ResponseEntity agendarConsulta(@RequestBody DadosAgendamento dados){
        var consultaAgendada = agenda.agendarConsulta(dados);
        return ResponseEntity.ok(consultaAgendada);
    }

    @DeleteMapping
    public ResponseEntity deletarConsulta(@RequestBody DadosCancelamento dados){
        agenda.cancelar(dados);
        return ResponseEntity.ok().build();
    }


}
