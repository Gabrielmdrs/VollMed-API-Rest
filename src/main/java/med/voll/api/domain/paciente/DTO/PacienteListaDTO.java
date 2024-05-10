package med.voll.api.domain.paciente.DTO;

import med.voll.api.domain.paciente.Paciente;

public record PacienteListaDTO(String nome, String email, String cpf, Boolean ativo) {
    public PacienteListaDTO(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getAtivo());

    }
}
