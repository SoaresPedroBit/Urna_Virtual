package urna.urna.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Eleitor extends Pessoa{

    private String profissao;
    private String celular;
    private String telefoneFixo;
    private String email;
    private StatusEleitor statusEleitor;


}
