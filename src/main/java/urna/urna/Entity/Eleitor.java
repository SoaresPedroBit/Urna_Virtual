package urna.urna.Entity;

public class Eleitor extends Pessoa{

    public String profissao;
    public String celular;
    public String telefoneFixo;
    public String email;
    public enum statusEleitor{
        APTO,
        INATIVO,
        BLOQUEADO,
        PENDENTE,
        VOTOU
    };
}
