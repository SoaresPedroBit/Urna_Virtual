package urna.urna.Entity;

public enum Cargo {
    Prefeito(1),
    Vereador(2);

    private final int codigo;

    // Construtor do enum
    Cargo(int codigo) {
        this.codigo = codigo;
    }

    // Getter para o código
    public int getCodigo() {
        return codigo;
    }

    // Método para encontrar um Cargo pelo código
    public static Cargo fromCodigo(int codigo) {
        for (Cargo cargo : Cargo.values()) {
            if (cargo.getCodigo() == codigo) {
                return cargo;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}

