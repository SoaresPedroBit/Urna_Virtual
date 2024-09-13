package urna.urna.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Eleitor extends Pessoa{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String profissao;
    private String celular;
    private String telefoneFixo;
    private String email;
    private StatusEleitor statusEleitor;


}
