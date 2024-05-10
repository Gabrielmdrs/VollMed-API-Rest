package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record PacienteAtualizaDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
){

}
