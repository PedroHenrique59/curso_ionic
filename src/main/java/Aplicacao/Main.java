package Aplicacao;

import Aplicacao.domain.*;
import Aplicacao.domain.enums.TipoCliente;
import Aplicacao.repositories.CategoriaRepository;
import Aplicacao.repositories.ClienteRepository;
import Aplicacao.repositories.EstadoRepository;
import Aplicacao.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        Categoria categoria1 = new Categoria(null, "Informática");
        Categoria categoria2 = new Categoria(null, "Escritório");

        Produto produto1 = new Produto(null, "Computador", 2000.00);
        Produto produto2 = new Produto(null, "Impressora", 800.00);
        Produto produto3 = new Produto(null, "Mouse", 80.00);

        categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
        categoria2.getProdutos().add(produto2);

        produto1.getCategorias().add(categoria1);
        produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
        produto3.getCategorias().add(categoria1);

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

        Estado estado1 = new Estado(null, "Minas Gerais");
        Estado estado2 = new Estado(null, "São Paulo");

        Cidade cidade1 = new Cidade(null, "Uberlandia", estado1);
        Cidade cidade2 = new Cidade(null, "Sao Paulo", estado2);
        Cidade cidade3 = new Cidade(null, "Campinas", estado2);

        estado1.getCidades().add(cidade1);
        estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

        estadoRepository.saveAllAndFlush(Arrays.asList(estado1, estado2));

        Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345670256", TipoCliente.PESSOA_FISICA);

        cliente1.getTelefones().addAll(Arrays.asList("31140250", "35478995"));

        Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim","31120259" ,cliente1, cidade1);
        Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro","31120587" ,cliente1, cidade2);

        cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

        clienteRepository.saveAllAndFlush(List.of(cliente1));

    }
}
