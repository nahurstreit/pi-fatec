package utils.interfaces;

/**
 * Interface para definição de classes que apresentam a propriedade de cópia.
 * @param <T> Classe de cópia, para poder ter o retorno de objetos criados. <b>SEMPRE DEVE SER DA MESMA CLASSE QUE VAI REALIZAR ESSA INTERFACE</b>!
 */
public interface ICopy<T> {
	/**
	 * Método para copiar as propriedades de um objeto em outro objeto da mesma classe.
	 * <br>Este é um método abstrato definido na interface ICopy e deve ser obrigatoriamente sobrescrito caso
	 * implementado. A sobrescrita deve acontecer pois as propriedades copiadas devem ser especificadas individualmente.
	 * <br>Para copiar automaticamente algum objeto, por qualquer motivo, use {@linkplain utils.CopyFactory#clone(Object, Object)}
	 * @param originObject - Objeto que contém as informações a copiar no objeto que chamou este método.
	 * @return O próprio objeto, para implementar fluent interface.
	 */
	T copyFrom(T originObject);
	
	/**
	 * Método para copiar as propriedades de um objeto em outro objeto da mesma classe.
	 * <br>Este é um método abstrato definido na interface ICopy e deve ser obrigatoriamente sobrescrito caso
	 * implementado. A sobrescrita deve acontecer pois as propriedades copiadas devem ser especificadas individualmente.
	 * <br>Para copiar automaticamente algum objeto, por qualquer motivo, use {@linkplain utils.CopyFactory#clone(Object, Object)}
	 * @param destinyObject - Objeto que receberá as informações deste objeto.
	 * @return <b>boolean</b> - O resultado da operação.
	 * <br>Se <b>true</b> - Todas as propriedades foram copiadas.
	 * <br>Se <b>false</b> - Houve algum erro e a cópia não foi bem sucedida.
	 */
	boolean copyMeTo(T destinyObject);
}