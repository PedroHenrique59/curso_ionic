package Aplicacao.dto;

import Aplicacao.domain.Cliente;
import org.hibernate.EmptyInterceptor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClienteDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty()
    @Email(message = "E-mail inválido")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente obj) {
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
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
}
