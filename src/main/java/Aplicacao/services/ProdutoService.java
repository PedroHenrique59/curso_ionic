package Aplicacao.services;

import Aplicacao.domain.Categoria;
import Aplicacao.domain.Pedido;
import Aplicacao.domain.Produto;
import Aplicacao.repositories.CategoriaRepository;
import Aplicacao.repositories.ProdutoRepository;
import Aplicacao.services.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto obterPorId(Integer id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer numeroPagina, Integer linhasPorPagina, String ordenacao, String ordemOrdenacao) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, linhasPorPagina, Sort.Direction.fromString(ordemOrdenacao), ordenacao);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}
