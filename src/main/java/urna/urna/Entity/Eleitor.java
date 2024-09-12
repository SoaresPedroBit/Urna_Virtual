package urna.urna.Entity;

public class Eleitor extends Pessoa{

    private String profissao;
    private String celular;
    private String telefoneFixo;
    private String email;
    private enum statusEleitor{
        APTO,
        INATIVO,
        BLOQUEADO,
        PENDENTE,
        VOTOU
    };
}
