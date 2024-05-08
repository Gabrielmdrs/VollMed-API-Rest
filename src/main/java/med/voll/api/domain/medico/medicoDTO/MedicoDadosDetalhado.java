package med.voll.api.domain.medico.medicoDTO;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;

public record MedicoDadosDetalhado(Long id,
                                   String nome,
                                   String email,
                                   String crm,
                                   String telefone,
                                   Especialidade especialidade,
                                   Endereco endereco) {

    public MedicoDadosDetalhado(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(),medico.getEndereco());

    }
}
