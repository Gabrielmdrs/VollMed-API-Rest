package med.voll.api.domain.medico;

import med.voll.api.domain.medico.medicoDTO.MedicoAtualizarDTO;
import med.voll.api.domain.medico.medicoDTO.MedicoDTO;
import med.voll.api.domain.medico.medicoDTO.MedicoDadosDetalhado;
import med.voll.api.domain.medico.medicoDTO.MedicoListarDTO;
import med.voll.api.infra.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@org.springframework.stereotype.Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;

    public Medico cadastrar(MedicoDTO dadosMedico){
        return repository.save(new Medico(dadosMedico));
    }

    public Page<MedicoListarDTO> listar(Pageable pageable){
       return repository.findAllByAtivoTrue(pageable).map(MedicoListarDTO::new);
    }
    public MedicoDadosDetalhado atualizar(MedicoAtualizarDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarDados(dados);
        return new MedicoDadosDetalhado(medico);
    }


    public MedicoListarDTO detalhar(Long id) {
        return new MedicoListarDTO(repository.getReferenceById(id));
         
    }

    public void deletar(Long id) {
        repository.findById(id).ifPresentOrElse((medicoExistente) -> repository.delete(medicoExistente),() ->{
            throw new BadRequestException("Medico com id %d nao existe!".formatted(id));
        });
    }
}
