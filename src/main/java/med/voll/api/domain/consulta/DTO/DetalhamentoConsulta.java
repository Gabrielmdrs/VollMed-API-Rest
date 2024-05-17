package med.voll.api.domain.consulta.DTO;

import med.voll.api.domain.consulta.Consulta;

import java.time.LocalDateTime;

public record DetalhamentoConsulta(Long id, Long idPaciente, Long idMedico, LocalDateTime data) {

    public DetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getData());
    }
}
