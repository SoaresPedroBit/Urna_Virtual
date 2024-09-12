package urna.urna.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Candidato extends Pessoa{

    private Integer numeroCandidato;
    private Cargo cargo;
    private StatusCandidato statusCandidato;


}
