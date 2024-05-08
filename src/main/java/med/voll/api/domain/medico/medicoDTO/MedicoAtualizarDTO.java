package med.voll.api.domain.medico.medicoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record MedicoAtualizarDTO(
        @NotNull
        Long id,
        @NotBlank
        String crm,
        String nome,
        String telefone,

        DadosEndereco endereco) {
}
