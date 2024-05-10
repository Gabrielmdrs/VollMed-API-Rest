package med.voll.api.domain.paciente;

public record PacienteListaDTO(String nome, String email, String cpf) {
    public PacienteListaDTO(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());

    }
}
