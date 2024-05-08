package med.voll.api.domain.medico.medicoDTO;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;

public record MedicoListarDTO (

        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
){
    public MedicoListarDTO(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());

    }

}
