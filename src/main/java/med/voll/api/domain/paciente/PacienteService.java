package med.voll.api.domain.paciente;

import med.voll.api.domain.paciente.DTO.PacienteAtualizaDTO;
import med.voll.api.domain.paciente.DTO.PacienteCadastroDTO;
import med.voll.api.domain.paciente.DTO.PacienteListaDTO;
import med.voll.api.infra.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;


    public Paciente cadastrar(PacienteCadastroDTO pacienteDados) {

        return repository.save(new Paciente(pacienteDados));

    }

    public Page<PacienteListaDTO> listar(Pageable pageable) {
        return repository.findAll(pageable).map(PacienteListaDTO::new);
    }

    public PacienteListaDTO atualizar(PacienteAtualizaDTO pacienteDados) {
        var paciente = repository.getReferenceById(pacienteDados.id());
        var pacienteAtualizado = paciente.atualizar(pacienteDados);
        System.out.println(pacienteAtualizado);
         return new PacienteListaDTO(repository.save(pacienteAtualizado));
    }

    public void deletar(Long id) {
        repository.findById(id).ifPresentOrElse((paciente) -> paciente.excluir() , ()->{
            throw new BadRequestException("Id informado não existe!");
        });
    }

    public PacienteListaDTO detalhar(Long id) {

        return new PacienteListaDTO(repository.getReferenceById(id));
    }
}
