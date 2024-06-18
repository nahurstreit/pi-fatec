/**
 * Package que contém as interfaces do sistema.
 */
package utils.interfaces;

/**
 * Interface para implementar callbacks mais complexos, definindo o tipo de retorno e os tipos de parâmetros.
 * @param <T> retorno da função.
 * @param <P> tipo de entrada na função.
 */
public interface ActionFunction<T, P> {
    /**
     * Método que será implementado para realizar uma ação baseada nos parâmetros fornecidos.
     * @param params Parâmetros necessários para a execução da ação.
     * @return O resultado da ação, do tipo especificado pela interface.
     */
	T execute(@SuppressWarnings("unchecked") P ...params);
}