package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.DTO.DadosAgendamento;
import med.voll.api.domain.consulta.DTO.DadosCancelamento;
import med.voll.api.domain.consulta.DTO.DetalhamentoConsulta;
import med.voll.api.domain.consulta.validacao.ValidadorAgendamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;

    public DetalhamentoConsulta agendarConsulta(DadosAgendamento dados) {

        var consulta = new Consulta(validar(dados));
        consultaRepository.save(consulta);
        return new DetalhamentoConsulta(consulta);
    }

    private DadosValidados validar(DadosAgendamento dados) {
        if (dados.idMedico()!= null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id informado não existe");
        }
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id informado não existe");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();

        return new DadosValidados(medico, paciente, dados.data());
    }

    private Medico escolherMedico(DadosAgendamento dados) {

        if(dados.idMedico()!= null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade()==null){
            throw new ValidacaoException("Especialidade é obrigatória se medico não for escolhido!");
        }

        var medico = medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
        if (medico.isPresent()){
            return medico.get();
        }else {
            throw new ValidacaoException("Não possui medico disponivel neste horario");
        }
    }

    public void cancelar(DadosCancelamento dados) {
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        if(consulta.getData().minusHours(24).isAfter(LocalDateTime.now())){
            consulta.deletar(dados.motivo());
        }else {
            throw new ValidacaoException("Tempo para cancelamento expirou!");
        }

    }
}

 record DadosValidados(Medico medico, Paciente paciente, LocalDateTime data){}
