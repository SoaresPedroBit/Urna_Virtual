package urna.urna.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Eleitor{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome não preenchido")
    private String nome;
    @CPF
    private String cpf;
    @NotBlank(message = "Profissão não preenchida")
    private String profissao;
    @NotBlank(message = "Celular não preenchido") @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Número de Celular inválido")
    private String celular;
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4}-\\d{4}", message = "Número de telefone inválido")
    private String telefoneFixo;
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private StatusEleitor statusEleitor;


}
