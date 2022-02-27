package Aplicacao.resources;

import Aplicacao.domain.Cliente;
import Aplicacao.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> obterPorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.obterPorId(id);
        return ResponseEntity.ok().body(cliente);
    }
}