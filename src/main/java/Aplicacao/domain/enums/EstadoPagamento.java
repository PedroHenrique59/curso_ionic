package Aplicacao.domain.enums;

public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private Integer id;
    private String descricao;

    EstadoPagamento(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer id) {
        if (id == null) {
            return null;
        }

        for (EstadoPagamento estado : EstadoPagamento.values()) {
            if (id.equals(estado.getId())) {
                return estado;
            }
        }

        throw new IllegalArgumentException("Id inválido:" + id);
    }
}
