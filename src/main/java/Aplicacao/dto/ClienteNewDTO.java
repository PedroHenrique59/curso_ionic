package Aplicacao.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ClienteNewDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpfOuCnpj;

    private Integer tipoCliente;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cep;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String telefone1;

    private String telefone2;

    private String telefone3;

    private Integer cidadeId;

    public ClienteNewDTO() {

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
     * @return Retorna o atributo email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email Atribui o valor do parâmetro no atributo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Retorna o atributo cpfOuCnpj
     */
    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    /**
     * @param cpfOuCnpj Atribui o valor do parâmetro no atributo cpfOuCnpj
     */
    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    /**
     * @return Retorna o atributo tipoCliente
     */
    public Integer getTipoCliente() {
        return tipoCliente;
    }

    /**
     * @param tipoCliente Atribui o valor do parâmetro no atributo tipoCliente
     */
    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    /**
     * @return Retorna o atributo logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro Atribui o valor do parâmetro no atributo logradouro
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return Retorna o atributo numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero Atribui o valor do parâmetro no atributo numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return Retorna o atributo complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento Atribui o valor do parâmetro no atributo complemento
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return Retorna o atributo bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro Atribui o valor do parâmetro no atributo bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return Retorna o atributo cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep Atribui o valor do parâmetro no atributo cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return Retorna o atributo telefone1
     */
    public String getTelefone1() {
        return telefone1;
    }

    /**
     * @param telefone1 Atribui o valor do parâmetro no atributo telefone1
     */
    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    /**
     * @return Retorna o atributo telefone2
     */
    public String getTelefone2() {
        return telefone2;
    }

    /**
     * @param telefone2 Atribui o valor do parâmetro no atributo telefone2
     */
    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    /**
     * @return Retorna o atributo telefone3
     */
    public String getTelefone3() {
        return telefone3;
    }

    /**
     * @param telefone3 Atribui o valor do parâmetro no atributo telefone3
     */
    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    /**
     * @return Retorna o atributo cidadeId
     */
    public Integer getCidadeId() {
        return cidadeId;
    }

    /**
     * @param cidadeId Atribui o valor do parâmetro no atributo cidadeId
     */
    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }
}
