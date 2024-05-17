package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.DTO.DadosAgendamento;

public interface ValidadorAgendamentoConsulta {
    void validar(DadosAgendamento dados);
}
