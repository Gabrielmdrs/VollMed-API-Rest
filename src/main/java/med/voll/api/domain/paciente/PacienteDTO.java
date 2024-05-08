package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.DadosEndereco;

public record PacienteDTO (String nome,
                          String email,
                          String telefone,
                          String cpf,
                          DadosEndereco endereco){
}
