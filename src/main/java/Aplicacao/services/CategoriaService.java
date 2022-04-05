package Aplicacao.services;

import Aplicacao.domain.Categoria;
import Aplicacao.domain.Cliente;
import Aplicacao.dto.CategoriaDTO;
import Aplicacao.repositories.CategoriaRepository;
import Aplicacao.services.execptions.DataIntegrityException;
import Aplicacao.services.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria obterPorId(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Categoria categoria) {
        Categoria newObj = obterPorId(categoria.getId());
        updateData(newObj, categoria);
        return categoriaRepository.save(newObj);
    }

    public void excluir(Integer id) {
        try {
            categoriaRepository.delete(obterPorId(id));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria com produtos!");
        }
    }

    public List<Categoria> obterTodos() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> obterPorPaginacao(Integer numeroPagina, Integer linhasPorPagina, String ordenacao, String ordemOrdenacao) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, linhasPorPagina, Sort.Direction.fromString(ordemOrdenacao), ordenacao);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
    }
}
