package utils.interfaces;

/**
 * Interface que define regras de validação para campos relacionados a clientes.
 */
public interface CustomerFieldRules {
    /**
     * Tamanho mínimo para o campo de nome.
     */
	final int MIN_SIZE_NAME = 5;
	
    /**
     * Tamanho máximo para o campo de nome.
     */
	final int MAX_SIZE_NAME = 50;
	
	
    /**
     * Altura mínima permitida para o campo de altura.
     */
	final double MIN_HEIGHT = 0.0;
	
    /**
     * Altura máxima permitida para o campo de altura.
     */
	final double MAX_HEIGHT = 300.0;
	
	
    /**
     * Tamanho mínimo para o campo de e-mail.
     */
	final int MIN_SIZE_EMAIL = 5;
	
    /**
     * Tamanho máximo para o campo de e-mail.
     */
	final int MAX_SIZE_EMAIL = 30;
	
	
    /**
     * Tamanho mínimo para o campo de telefone.
     */
	final int MIN_SIZE_PHONE = 10;
	
	/**
     * Tamanho máximo para o campo de telefone.
     */
	final int MAX_SIZE_PHONE = 17;
}