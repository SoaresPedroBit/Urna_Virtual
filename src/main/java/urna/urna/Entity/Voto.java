package urna.urna.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String dataHora;
    @NotNull(message = "Preencha o numero do prefeito") @ManyToOne @JoinColumn(name = "prefeito_id")
    private Candidato prefeito;
    @NotNull(message = "Preencha o numero do  vereador") @ManyToOne @JoinColumn(name = "vereador_id")
    private Candidato vereador;
    @NotBlank
    private String comprovante;


}
