package view.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import view.utils.VUtils;

/**
 * Classe para controlar Inputs do Usuário e fornece um texto de orientação de preenchimento chamado "hint".
 */
public class HintInputField extends JTextField {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String hint; //Texto de dica de preenchimento que sempre aparecerá
	@SuppressWarnings("unused")
	private float fontSize; //Tamanho da fonte
	private boolean showHint; //Variável de controle da dica.
	
	protected Font STD_HINT_FONT = VUtils.loadFont("Montserrat-ExtraLight");
	protected Font STD_TEXT_FONT = VUtils.loadFont("Montserrat-Regular");
	
	/**
	 * Método Construtor da classe para controlar inputs do usuário e fornecer uma dica de preenchimento
	 * @param hint -> (String) Texto a ser exibido na dica
	 * @param size -> (Dimension) Instância de Dimension para controlar o tamanho do TextField
	 * @param fontSize -> (float) Tamanho de exibição da fonte durante o preenchimento
	 */
	public HintInputField(String hint, Dimension size, float fontSize) {
		super(hint);
		this.hint = hint;
		this.fontSize = fontSize;
		this.showHint = true;
		this.setPreferredSize(size);
		this.setFont(this.STD_HINT_FONT.deriveFont(fontSize));
		
		this.addFocusListener(new FocusListener() { //Adicionando um Listener de Foco
			
			/**
			 * Método que esconde a dica do TextField
			 * @Override
			 */
			public void focusGained(FocusEvent e) {
				if(showHint) { //Se o foco for ganho e precisar mostrar a hint, reseta o texto e coloca uma fonte maior.
					setText("");
					setFont(STD_TEXT_FONT.deriveFont(fontSize + 1));
					showHint = false;
				}
			}
			
			/**
			 * Método que revela a dica novamente caso nada seja preenchido.
			 * @Override
			 */
			public void focusLost(FocusEvent e) {// Se o foco do input for perdido e não tiver texto, volta ao original.
				if(getText().isBlank()) {
					setText(hint);
					setFont(STD_HINT_FONT.deriveFont(fontSize));
					showHint = true;
				}
			}
		});
	}
	
}