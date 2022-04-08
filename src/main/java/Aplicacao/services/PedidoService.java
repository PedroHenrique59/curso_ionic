package Aplicacao.services;

import Aplicacao.domain.ItemPedido;
import Aplicacao.domain.PagamentoComBoleto;
import Aplicacao.domain.Pedido;
import Aplicacao.domain.enums.EstadoPagamento;
import Aplicacao.repositories.ItemPedidoRepository;
import Aplicacao.repositories.PagamentoRepository;
import Aplicacao.repositories.PedidoRepository;
import Aplicacao.repositories.ProdutoRepository;
import Aplicacao.services.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido obterPorId(Integer id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);

        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagamentoComBoleto, obj.getInstante());
        }
        obj = pedidoRepository.save(obj);

        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.obterPorId(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }

        itemPedidoRepository.saveAll(obj.getItens());

        return obj;
    }

}
