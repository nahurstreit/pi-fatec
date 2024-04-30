package view.components;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import view.utils.VUtils;

public class HintInputField extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@SuppressWarnings("unused")
	private String hint; //Texto de dica de preenchimento que sempre aparecerá
	@SuppressWarnings("unused")
	private float fontSize; //Tamanho da fonte
	private boolean showHint; //Variável de controle da hint.
	
	public HintInputField(String hint, Dimension size, float fontSize) {
		super(hint);
		this.hint = hint;
		this.fontSize = fontSize;
		this.showHint = true;
		this.setPreferredSize(size);
		this.setFont(VUtils.loadFont("Montserrat-ExtraLight").deriveFont(fontSize));
		
		this.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(showHint) { //Se o foco for ganho e precisar mostrar a hint, reseta o texto e coloca uma fonte maior.
					setText("");
					setFont(VUtils.loadFont("Montserrat-Regular").deriveFont(fontSize + 1));
					showHint = false;
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {// Se o foco do input for perdido e não tiver texto, volta ao original.
				if(getText().isBlank()) {
					setText(hint);
					setFont(VUtils.loadFont("Montserrat-ExtraLight").deriveFont(fontSize));
					showHint = true;
				}
			}
		});
	}
	
}