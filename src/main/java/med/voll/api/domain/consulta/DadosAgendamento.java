package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.medicoDTO.MedicoListarDTO;
import med.voll.api.domain.paciente.DTO.PacienteListaDTO;

import java.time.LocalDateTime;
import java.util.Date;

public record DadosAgendamento(
        @NotNull
        Long idPaciente,
        Long idMedico,
        @NotNull
        @Future
        LocalDateTime data) {
}
