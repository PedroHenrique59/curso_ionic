package Aplicacao.resources;

import Aplicacao.domain.Categoria;
import Aplicacao.dto.CategoriaDTO;
import Aplicacao.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> obterPorId(@PathVariable Integer id) {
        Categoria categoria = categoriaService.obterPorId(id);
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Categoria categoria) {
        categoria = categoriaService.salvar(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Categoria categoria, @PathVariable Integer id) {
        categoria.setId(id);
        categoriaService.atualizar(categoria);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        categoriaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> obterTodos(){
        List<Categoria> categorias = categoriaService.obterTodos();
        List<CategoriaDTO> categoriaDTOS = categorias.stream().map(CategoriaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriaDTOS);
    }
}
