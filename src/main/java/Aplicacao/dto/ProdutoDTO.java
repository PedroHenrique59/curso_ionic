package Aplicacao.dto;

import Aplicacao.domain.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO() {

    }

    public ProdutoDTO(Produto obj) {
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();
    }

    /**
     * @return Retorna o atributo id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id Atribui o valor do parâmetro no atributo id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Retorna o atributo nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Atribui o valor do parâmetro no atributo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Retorna o atributo preco
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * @param preco Atribui o valor do parâmetro no atributo preco
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
