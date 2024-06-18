/**
 * Package que contém os componentes de input dos usuários.
 */
package view.components.inputs;

import utils.interfaces.Condition;

/**
 * Classe para definir uma máscara condicional
 */
public class ConditionalMask {
	private final String mask;
	/**
	 * Condição para que a máscara seja aplicada.
	 */
	public final Condition condition;
	
	/**
     * Construtor da classe ConditionalMask.
     *
     * @param mask a máscara a ser aplicada.
     * @param condition a condição para que a máscara seja aplicada.
     */
	public ConditionalMask(final String mask, final Condition condition) {
		this.mask = mask;
		this.condition = condition;
	}
	
    /**
     * Construtor da classe ConditionalMask com uma condição padrão que sempre retorna true.
     *
     * @param mask a máscara a ser aplicada.
     */
	public ConditionalMask(final String mask) {
		this.mask = mask;
		this.condition = (text) -> {return true;};
	}

	/**
     * Obtém a máscara definida.
     *
     * @return a máscara.
     */
	public String getMask() {
		return mask;
	}
	
    /**
     * Verifica se o texto fornecido é válido de acordo com a condição.
     *
     * @param text o texto a ser validado.
     * @return true se o texto atender à condição, false caso contrário.
     */
	public boolean isValid(String text) {
		return condition.isValid(text);
	}
}