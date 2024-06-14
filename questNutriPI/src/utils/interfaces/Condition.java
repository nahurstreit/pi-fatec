package utils.interfaces;

/**
 * Interface funcional para definir a regra de validação.
 * Deve ser implementada com uma função que recebe uma string e retorna um booleano.
 */
public interface Condition {
    /**
     * Método que verifica se o texto fornecido é válido de acordo com a regra implementada.
     *
     * @param text O texto a ser validado.
     * @return true se o texto for válido, false caso contrário.
     */
	boolean isValid(String text);
}