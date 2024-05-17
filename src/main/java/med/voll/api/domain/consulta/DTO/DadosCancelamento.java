package med.voll.api.domain.consulta.DTO;

import med.voll.api.domain.consulta.MotivoCancelamento;

public record DadosCancelamento(Long idConsulta,

                                MotivoCancelamento motivo) {
}
