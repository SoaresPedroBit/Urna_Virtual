package urna.urna.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Voto {

    private String dataHora;
    private Candidato prefeito;
    private Candidato vereador;
    private String comprovante;


}
