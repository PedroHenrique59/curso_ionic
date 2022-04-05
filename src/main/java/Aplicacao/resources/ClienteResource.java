package Aplicacao.resources;

import Aplicacao.domain.Cliente;
import Aplicacao.dto.ClienteDTO;
import Aplicacao.dto.ClienteNewDTO;
import Aplicacao.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> obterPorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.obterPorId(id);
        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@Valid @RequestBody ClienteNewDTO clienteDto) {
        Cliente cliente = clienteService.fromDTO(clienteDto);
        cliente = clienteService.salvar(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@Valid @RequestBody ClienteDTO clienteDto, @PathVariable Integer id) {
        Cliente cliente = clienteService.fromDTO(clienteDto);
        cliente.setId(id);
        clienteService.atualizar(cliente);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> obterTodos(){
        List<Cliente> clientes = clienteService.obterTodos();
        List<ClienteDTO> clienteDTOS = clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTOS);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> obterPorPagina(
            @RequestParam(value = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(value = "linhasPorPagina", defaultValue = "nome") Integer linhasPorPagina,
            @RequestParam(value = "ordenacao", defaultValue = "24") String ordenacao,
            @RequestParam(value = "ordemOrdenacao", defaultValue = "ASC") String ordemOrdenacao){
        Page<Cliente> clientes = clienteService.obterPorPaginacao(numeroPagina, linhasPorPagina, ordenacao, ordemOrdenacao);
        Page<ClienteDTO> clienteDTOS = clientes.map(ClienteDTO::new);
        return ResponseEntity.ok().body(clienteDTOS);
    }

}
