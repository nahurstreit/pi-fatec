/**
 * Package que contém rótulos ou labels personalizadas do sistema.
 */
package view.components.labels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import utils.interfaces.IDoAction;

/**
 * Classe JLabel personalizada que suporta eventos de clique e alterações visuais ao passar o mouse por cima.
 */
public class ActionLbl extends JLabel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Evento a ser executado.
	 */
	private IDoAction event;

	@SuppressWarnings("unused")
	private String text;

	
	/**
	 * Criação do mouse adapter de hover.
	 */
	private MouseAdapter mouseHover = new MouseAdapter() {			
        /**
         * Quando o mouse entra na área do componente, altera o cursor para o formato de mão.
         *
         * @param e Evento de mouse
         */
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		
        /**
         * Quando o mouse sai da área do componente, restaura o cursor para o padrão.
         *
         * @param e Evento de mouse
         */
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
	};
	
    /**
     * Construtor que cria um ActionLbl com texto e uma ação a ser executada no clique.
     *
     * @param text  Texto exibido no JLabel
     * @param event Ação a ser executada no clique
     */
	public ActionLbl(String text, IDoAction event) {
		super(text);
		this.text = text;
		this.event = event;
		turnHoverOn();
		initAction();
	}
	
    /**
     * Construtor que cria um ActionLbl com texto, uma ação e um modo de exibição específico.
     *
     * @param text  Texto exibido no JLabel
     * @param event Ação a ser executada no clique
     * @param mode  Modo de exibição do JLabel
     */
	public ActionLbl(String text, IDoAction event, int mode) {
		super(text, mode);
		this.text = text;
		this.event = event;
		turnHoverOn();
		initAction();
	}
	
    /**
     * Construtor que cria um ActionLbl apenas com texto.
     *
     * @param text Texto exibido no JLabel
     */
	public ActionLbl(String text) {
		this(text, null);
	}
	
    /**
     * Adiciona um listener de mouse para executar a ação quando o componente for clicado.
     */
	private void initAction() {
		this.addMouseListener(new MouseAdapter() {
			//Quando clicar aciona a ação enviada
			public void mouseClicked(MouseEvent e) {
		        if (event != null) {
		            event.execute();
		        }
			}
		});
	}
	
    /**
     * Define uma nova ação a ser executada quando o JLabel é clicado.
     *
     * @param event Ação a ser executada no clique
     * @return O próprio objeto para implementar fluent interface.
     */
	public ActionLbl setAction(IDoAction event) {
		this.event = event;
		return this;
	}
	
    /**
     * Configura a fonte do ActionLbl.
     *
     * @param font Fonte a ser configurada
     * @return O próprio objeto para implementar fluent interface.
     */
	public ActionLbl setUpFont(Font font) {
		this.setFont(font);
		return this;
	}
	
    /**
     * Configura a cor do texto do ActionLbl.
     *
     * @param color Cor do texto a ser configurada
     * @return O próprio objeto para implementar fluent interface.
     */
	public ActionLbl setUpColor(Color color) {
		this.setForeground(color);
		return this;
	}
	
    /**
     * Desativa o efeito de alteração de cursor ao passar o mouse por cima do JLabel.
     *
     * @return O próprio objeto para implementar fluent interface.
     */
	public ActionLbl turnHoverOff() {
		try {
			this.removeMouseListener(mouseHover);
		} catch (Exception e) {
			System.out.println(e);
		}
		return this;
	}
	
    /**
     * Ativa o efeito de alteração de cursor ao passar o mouse por cima do JLabel.
     *
     * @return O próprio objeto para implementar fluent interface.
     */
	public ActionLbl turnHoverOn() {
		this.addMouseListener(mouseHover);
		return this;
	}
	
}