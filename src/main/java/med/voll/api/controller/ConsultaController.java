package med.voll.api.controller;

import med.voll.api.domain.consulta.DadosAgendamento;
import med.voll.api.domain.consulta.detalhamentoConsulta;
import med.voll.api.domain.medico.medicoDTO.MedicoListarDTO;
import med.voll.api.domain.paciente.DTO.PacienteListaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @PostMapping
    public ResponseEntity agendarConsulta(@RequestBody DadosAgendamento dados, UriComponentsBuilder uriBuilder){

        return ResponseEntity.ok(new detalhamentoConsulta(null, dados.idMedico(), dados.idPaciente(), dados.data()));
    }


}
