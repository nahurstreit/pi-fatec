package validate;

/**
 * Classe para criar regras de validação.
 * <br>Para criar uma regra, basta chamar o construtor dessa classe, enviar a ela uma função
 * de validação e uma mensagem de erro caso a regra não seja cumprida.
 */
public class ValidationRule {

    /**
     * Interface funcional para definir a regra de validação.
     * Deve ser implementada com uma função que recebe uma string e retorna um booleano.
     */
    public interface Validate {
        /**
         * Método que verifica se o texto fornecido é válido de acordo com a regra implementada.
         *
         * @param text O texto a ser validado.
         * @return true se o texto for válido, false caso contrário.
         */
        boolean isValid(String text);
    }

    private Validate rule;
    private String errorMessage;

    /**
     * Construtor da classe ValidationRule.
     * Cria uma regra de validação com uma função de validação e uma mensagem de erro.
     *
     * @param rule A função de validação que implementa a interface Validate.
     * @param errorMessage A mensagem de erro a ser exibida caso a regra não seja cumprida.
     */
    public ValidationRule(Validate rule, String errorMessage) {
        this.rule = rule;
        this.errorMessage = errorMessage;
    }

    /**
     * Construtor da classe ValidationRule.
     * Cria uma regra de validação com uma função de validação e usa uma mensagem de erro padrão.
     *
     * @param rule A função de validação que implementa a interface Validate.
     */
    public ValidationRule(Validate rule) {
        this(rule, "Erro de validação.");
    }

    /**
     * Obtém a mensagem de erro associada a esta regra de validação.
     *
     * @return A mensagem de erro.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Verifica se o texto fornecido é válido de acordo com a regra de validação.
     *
     * @param text O texto a ser validado.
     * @return true se o texto for válido, false caso contrário.
     */
    public boolean isValid(String text) {
        return rule.isValid(text);
    }
}
