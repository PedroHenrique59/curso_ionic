package Aplicacao.resources;

import Aplicacao.domain.Categoria;
import Aplicacao.domain.Pedido;
import Aplicacao.domain.Produto;
import Aplicacao.dto.CategoriaDTO;
import Aplicacao.dto.ProdutoDTO;
import Aplicacao.resources.Utils.URL;
import Aplicacao.services.PedidoService;
import Aplicacao.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> obterPorId(@PathVariable Integer id) {
        Produto produto = produtoService.obterPorId(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> obterPorPagina(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(value = "linhasPorPagina", defaultValue = "nome") Integer linhasPorPagina,
            @RequestParam(value = "ordenacao", defaultValue = "24") String ordenacao,
            @RequestParam(value = "ordemOrdenacao", defaultValue = "ASC") String ordemOrdenacao){

        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> produtos = produtoService.search(nomeDecoded, ids, numeroPagina, linhasPorPagina, ordenacao, ordemOrdenacao);
        Page<ProdutoDTO> produtoDTOS = produtos.map(ProdutoDTO::new);
        return ResponseEntity.ok().body(produtoDTOS);
    }

}
