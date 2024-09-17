package urna.urna.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Candidato{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome não preenchido")
    private String nome;
    @CPF @NotBlank(message = "CPF não preenchido")
    private String cpf;
    @NotNull(message = "Numero de candidato não preenchido") @Column(unique = true)
    private Integer numeroCandidato;
    @Enumerated(EnumType.ORDINAL)
    private Cargo cargo;
    @Enumerated(EnumType.STRING)
    private StatusCandidato statusCandidato;
    @Transient
    private long votosApurados;

    public Candidato(long id, String nome, String cpf, int numeroCandidato, Cargo cargo, StatusCandidato statusCandidato) {
    }
}
