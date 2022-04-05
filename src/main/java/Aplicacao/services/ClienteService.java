package Aplicacao.services;

import Aplicacao.domain.Cidade;
import Aplicacao.domain.Cliente;
import Aplicacao.domain.Endereco;
import Aplicacao.domain.enums.TipoCliente;
import Aplicacao.dto.ClienteDTO;
import Aplicacao.dto.ClienteNewDTO;
import Aplicacao.repositories.ClienteRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente obterPorId(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        Cliente newObj = obterPorId(cliente.getId());
        updateData(newObj, cliente);
        return clienteRepository.save(newObj);
    }

    public void excluir(Integer id) {
        try {
            clienteRepository.delete(obterPorId(id));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }

    public List<Cliente> obterTodos() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> obterPorPaginacao(Integer numeroPagina, Integer linhasPorPagina, String ordenacao, String ordemOrdenacao) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, linhasPorPagina, Sort.Direction.fromString(ordemOrdenacao), ordenacao);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO clienteDTO) {
        Cliente cliente = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteDTO.getTipoCliente()));
        Cidade cidade = new Cidade(clienteDTO.getCidadeId());
        Endereco endereco = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(), clienteDTO.getComplemento(), clienteDTO.getBairro(), clienteDTO.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteDTO.getTelefone1());
        if (clienteDTO.getTelefone2() != null) {
            cliente.getTelefones().add(clienteDTO.getTelefone2());
        }
        if (clienteDTO.getTelefone3() != null) {
            cliente.getTelefones().add(clienteDTO.getTelefone3());
        }
        return cliente;
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
