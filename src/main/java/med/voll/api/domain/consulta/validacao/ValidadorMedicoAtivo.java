package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DTO.DadosAgendamento;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private MedicoRepository repository;
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DadosAgendamento dados){
        if(dados.idMedico()==null){
            return;
        }
        var medicoAtivo = repository.findByAtivoTrue(dados.idMedico());
        if(!medicoAtivo){
            throw new ValidacaoException("Médico está inativo no sistema");
        }

        var medicoPossuiConsultaNoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(),dados.data());
        if (medicoPossuiConsultaNoHorario){
            throw new ValidacaoException("Médico já possui consulta nesse horario");
        }
    }

}
