package view.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;

import view.utils.VUtils;

/**
 * Classe para controlar Inputs do Usuário e fornece um texto de orientação de preenchimento chamado "hint".
 */
public class HintInputField extends JFormattedTextField {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String hint; //Texto de dica de preenchimento que sempre aparecerá
	@SuppressWarnings("unused")
	private float fontSize; //Tamanho da fonte
	protected boolean showHint; //Variável de controle da dica.
	
	protected Font STD_HINT_FONT = VUtils.loadFont("Montserrat-ExtraLight");
	protected Font STD_TEXT_FONT = VUtils.loadFont("Montserrat-Regular");

	
	private String mask;
	private boolean clearMaskOnSelect = true;
	
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
			private int caretPosition; //Definindo explicitamente a posição do caret
			
			/**
			 * Método que esconde a dica do TextField
			 * @Override
			 */
			public void focusGained(FocusEvent e) {
				if(showHint) { //Se o foco for ganho e precisar mostrar a hint, reseta o texto e coloca uma fonte maior.
					setText("");
					setFont(STD_TEXT_FONT.deriveFont(fontSize + 1));
					showHint = false;
				} else {
					String text = getText();
					setCaretPosition(text.length());
					if(mask != null && clearMaskOnSelect) {
						text = text.replaceAll("\\W", "");
						setText(text);
						setCaretPosition(text.length());
					}
				}
				
				if(caretPosition == 0) caretPosition = getText().length(); //Definindo a posição do caret
                SwingUtilities.invokeLater(() -> setCaretPosition(caretPosition)); //Salvando a posição final do caret
			}
			
			/**
			 * Método que revela a dica novamente caso nada seja preenchido.
			 * @Override
			 */
			public void focusLost(FocusEvent e) {// Se o foco do input for perdido e não tiver texto, volta ao original.
				caretPosition = getCaretPosition();
				if(getText().isBlank()) {
					setText(hint);
					setFont(STD_HINT_FONT.deriveFont(fontSize));
					showHint = true;
				} else {
					if(mask != null) {
				        setText(applyMask(getText()));
					}
				}
			}
		});

	}
	
	public HintInputField(String hint) {
		this(hint, new Dimension(100, 100), 12f);
	}
	
	public HintInputField setHint(String text) {
		this.hint = text;
		setText(text);
		return this;
	}
	
	public HintInputField setInitialValue(String text) {
		setFont(STD_TEXT_FONT.deriveFont(fontSize + 1));
		showHint = false;
		if(mask != null) text = applyMask(text);
		setValue(text);
		setText(text);
		return this;
	}
	
	public HintInputField setMask(String mask) {
		this.mask = mask;
		return this;
	}
	
	public HintInputField clearMaskOnSelect(boolean clear) {
		this.clearMaskOnSelect = clear;
		return this;
	}
	
	private String applyMask(String text) {
		String currentText = text.replaceAll("\\W", "");
		StringBuilder rT = new StringBuilder();
        int i = 0;// Variável de controle da posição da String verdadeira
        for (int j = 0; j < mask.length(); j++) {
        	if(j > currentText.length()) {
        		break;
        	}
            if(mask.charAt(j) == '#') {
                rT.append(currentText.charAt(i));
                i++;
            } else {
            	rT.append(mask.charAt(j));
            }
        }
        
        if(i < currentText.length()) {
        	for(int j = i; j < currentText.length(); j++) {
        		rT.append(currentText.charAt(j));
        	}
        }
        
        return rT.toString();
	}
}