package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.medicoDTO.MedicoAtualizarDTO;
import med.voll.api.domain.medico.medicoDTO.MedicoDTO;

@Entity(name = "Medico")
@Table(name = "Medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @Column(unique = true)
    private String email;

    private String telefone;

    @Column(unique = true)
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;
    @Column
    private Boolean ativo;

    public Medico(MedicoDTO dadosMedico) {
        this.ativo = true;
        this.nome = dadosMedico.nome();
        this.crm = dadosMedico.crm();
        this.email = dadosMedico.email();
        this.telefone = dadosMedico.telefone();
        this.especialidade = dadosMedico.especialidade();
        this.endereco = new Endereco(dadosMedico.endereco());

    }


    public void atualizarDados(MedicoAtualizarDTO dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizaInformacoes(dados.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
