package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DTO.DadosAgendamento;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPaciente implements ValidadorAgendamentoConsulta{
    @Autowired
    private PacienteRepository repository;
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamento dados){
        var pacienteAtivo = repository.findByAtivo(dados.idPaciente());
        if(!pacienteAtivo){
            throw new ValidacaoException("Paciente não está ativo no sistema!");
        }

        var horarioInicio = dados.data().withHour(7);
        var horarioFim = dados.data().withHour(18);
        var pacientePossuiOutraConsulta = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(),horarioInicio, horarioFim);
        if(pacientePossuiOutraConsulta){
            throw new ValidacaoException("Paciente já possui outra consulta nessa data!");
        }
    }
}
