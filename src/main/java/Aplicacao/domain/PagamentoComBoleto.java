package Aplicacao.domain;

import Aplicacao.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento {

    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    public PagamentoComBoleto() {

    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
