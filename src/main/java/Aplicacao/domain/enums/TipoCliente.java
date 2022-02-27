package Aplicacao.domain.enums;

public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private Integer id;
    private String descricao;

    TipoCliente(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer id) {
        if (id == null) {
            return null;
        }

        for (TipoCliente tipo : TipoCliente.values()) {
            if (id.equals(tipo.getId())) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Id inválido:" + id);
    }
}
