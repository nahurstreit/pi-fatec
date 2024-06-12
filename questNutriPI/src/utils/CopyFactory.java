package utils;

import java.lang.reflect.Field;

public class CopyFactory {
	/**
	 * Método que permite a cópia automática do estado das propriedades de um objeto de uma dada classe a outro objeto da <b>mesma classe</b>.
	 * @param <T> - Classe dos objetos.
	 * @param origin - objeto que contém as propriedades a serem copiadas em destiny.
	 * @param destiny - objeto que receberá as propriedades de origin.
	 * @return <b>boolean</b> - O resultado da operação.
	 * <li>Se <b>true</b> - Todas as propriedades foram copiadas.
	 * <li>Se <b>false</b> - Houve algum erro e a cópia não foi bem sucedida.
	 */
	public static <T> boolean clone(T origin, T destiny) {
        if (destiny == null || origin == null) {
            System.err.println("Chamada de cópia inválida. Os objetos origin e destiny não podem ser nulos.");
            System.err.println("Origin: "+origin != null);
            System.err.println("Destiny: "+destiny != null);
            return false;
        }

        Class<?> clazz = origin.getClass();
        if (!clazz.equals(destiny.getClass())) {
        	System.err.println("Chamada de cópia inválida. Os objetos origin e destiny devem ser da mesma classe.");
        	System.err.println("Origin: "+clazz.getSimpleName());
            System.err.println("Destiny: "+destiny.getClass().getSimpleName());
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); //Permite o acesso a campos privados
            try {
                Object value = field.get(origin);
                field.set(destiny, value);
            } catch (Exception e) {
            	System.err.println("Houve um erro ao copiar uma das propriedade: "+field.getName());
                return false;
            }
        }
        
        return true;
    }
}