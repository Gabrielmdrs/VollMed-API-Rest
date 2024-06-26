package med.voll.api.domain.paciente.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record PacienteCadastroDTO(@NotBlank
                                  String nome,
                                  @NotBlank
                                  String email,
                                  @NotBlank
                                  String telefone,
                                  @NotBlank
                                  String cpf,
                                  @Valid
                                  @NotNull
                                  DadosEndereco endereco){
}
