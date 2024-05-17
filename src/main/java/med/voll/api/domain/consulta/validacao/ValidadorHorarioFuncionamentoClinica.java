package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.DTO.DadosAgendamento;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta{

    public void validar(DadosAgendamento dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAbrir = dataConsulta.getHour() < 7;
        var horarioDepoisDeFechar = dataConsulta.getHour() > 18;
        if(domingo || horarioAntesDeAbrir || horarioDepoisDeFechar){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");
        }
    }
}
