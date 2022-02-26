package Aplicacao.resources;

import Aplicacao.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar() {

        Categoria categoria = new Categoria();
        Categoria categoria1 = new Categoria();

        List<Categoria> categorias = new ArrayList<>(Arrays.asList(categoria, categoria1));
        return categorias;
    }
}
