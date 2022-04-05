package Aplicacao.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private String fieldName;
    private String message;

    public FieldMessage() {

    }

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    /**
     * @return Retorna o atributo fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName Atribui o valor do parâmetro no atributo fieldName
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return Retorna o atributo message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message Atribui o valor do parâmetro no atributo message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
