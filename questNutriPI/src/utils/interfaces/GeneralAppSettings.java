package utils.interfaces;

/**
 * Interface utilitária para definir configurações da aplicação.
 */
public interface GeneralAppSettings {
    //CONFIG
	/**
	 * Exibir a logo inicial quando iniciar a aplicação.
	 */
    public static final boolean SHOWLOGO = true;
    
    /**
     * Pular a tela de login sem precisar ter um login válido.
     */
    public static final boolean  SKIP_LOGIN = false;
    
    /**
     * Linguagem padrão do sistema.
     * <br>0 -> português;
     * <br>1 -> inglês;
     */
	public static final int STD_LANGUAGE = 0;
}