package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.paciente.DTO.PacienteAtualizaDTO;
import med.voll.api.domain.paciente.DTO.PacienteCadastroDTO;
import org.hibernate.annotations.processing.SQL;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    @Column
    private Boolean ativo;

    public Paciente(PacienteCadastroDTO pacienteDados) {
        this.ativo = true;
        this.nome = pacienteDados.nome();
        this.cpf = pacienteDados.cpf();
        this.email = pacienteDados.email();
        this.telefone = pacienteDados.telefone();
        this.endereco = new Endereco(pacienteDados.endereco());
    }



    public Paciente atualizar(PacienteAtualizaDTO dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizaInformacoes(dados.endereco());
        }

        return this;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    public void excluir() {
        this.ativo = false;
    }
}
