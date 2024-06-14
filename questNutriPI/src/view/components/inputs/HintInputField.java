package view.components.inputs;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;

import utils.interfaces.Condition;
import utils.interfaces.GeneralVisualSettings;
import utils.view.LanguageUtil;

/**
 * Classe para controlar Inputs do Usuário e fornece um texto de orientação de preenchimento chamado "hint".
 */
public class HintInputField extends JFormattedTextField implements GeneralVisualSettings {
	private static final long serialVersionUID = 1L;

	protected final String STD_HINT_TEXT = new LanguageUtil("Digite aqui...", "Type here...").get();
	protected final Float STD_HINT_FONT_SIZE = 12f;
	protected final Dimension STD_HINT_INPUT_SIZE = new Dimension(100, 100);
	
	 //Texto de dica de preenchimento que sempre aparecerá
	private String hint = STD_HINT_TEXT;
	private float fontSize; //Tamanho da fonte
	private boolean showHint;
	
	protected Font STD_HINT_FONT = STD_EXTRA_LIGHT_FONT;
	protected Font STD_TEXT_FONT = STD_REGULAR_FONT;

	private List<ConditionalMask> masks = new ArrayList<ConditionalMask>();
	
	private boolean clearMaskOnSelect = true;
	
	/**
	 * Método Construtor da classe para controlar inputs do usuário e fornecer uma dica de preenchimento
	 * @param hint -> (String) Texto a ser exibido na dica
	 * @param size -> (Dimension) Instância de Dimension para controlar o tamanho do TextField
	 * @param fontSize -> (float) Tamanho de exibição da fonte durante o preenchimento
	 */
	public HintInputField(String hint, Dimension size, Float fontSize) {
		super();
		if(hint != null) {
			setHint(hint);
		}
		
		if(size != null) {
			this.setPreferredSize(size);
		}
		
		if(fontSize != null) {
			this.fontSize = fontSize;
		}
		
		this.showHint = true;
		this.setFont(this.STD_HINT_FONT.deriveFont(fontSize));

		initHintListener();
	}
	
	public HintInputField(String hint) {
		this(hint, null, null);
	}
	
	public HintInputField() {
		this(null, null, null);
	}
	
	private void initHintListener() {
		this.addFocusListener(new FocusListener() { //Adicionando um Listener de Foco
			private int caretPosition; //Definindo explicitamente a posição do caret
			
			/**
			 * Método que esconde a dica do TextField
			 * @Override
			 */
			public void focusGained(FocusEvent e) {
		        if(isShowingHint() && isEditable()) { //Se o foco for ganho e precisar mostrar a hint, reseta o texto e coloca uma fonte maior.
		            setText("");
		            setFont(STD_TEXT_FONT.deriveFont(fontSize + 1));
		            showHint = false;
		        } else {
		            String text = getText();
		            setCaretPosition(text.length());
		            if(masks.size() > 0 && clearMaskOnSelect) {
		                text = text.replaceAll("\\W", "");
		                setText(text);
		                setCaretPosition(text.length());
		            }
		        }
                
				if (caretPosition == 0) caretPosition = getText().length(); //Definindo a posição do caret
		        SwingUtilities.invokeLater(() -> {
		            int position = Math.min(caretPosition, getText().length());
		            setCaretPosition(position);
		        }); //Salvando a posição final do caret
			}
			
			/**
			 * Método que revela a dica novamente caso nada seja preenchido.
			 * @Override
			 */
			public void focusLost(FocusEvent e) {//Se o foco do input for perdido e não tiver texto, volta ao original.
				caretPosition = getCaretPosition();
				if(getText().isBlank()) {
					setText(hint);
					setFont(STD_HINT_FONT.deriveFont(fontSize));
					showHint = true;
				} else {
		            showHint = false;
		    		if(masks.size() > 0) {
		    			String text = getText().replaceAll("\\W", "");
		    			setText(chooseAndApplyMask(text));
		    		}
				}
			}
		});
	}
	
	public boolean isShowingHint() {
		return showHint;
	}

	public HintInputField setHint(String text) {
		this.hint = text;
		setText(hint);
		return this;
	}
	
	public HintInputField setInitialValue(String text) {
		showHint = false;
		setFont(STD_TEXT_FONT.deriveFont(fontSize + 1));
		if(masks.size() > 0) {
			text = chooseAndApplyMask(text);
		}
		setText(text);
		return this;
	}
	
	public HintInputField setMask(String mask) {
		this.masks.add(new ConditionalMask(mask));
		return this;
	}
	
	public HintInputField setMask(String mask, Condition condition) {
		this.masks.add(new ConditionalMask(mask, condition));
		return this;
	}
	
	public HintInputField setMask(ConditionalMask mask) {
		this.masks.add(mask);
		return this;
	}
	
	public HintInputField clearMaskOnSelect(boolean clear) {
		this.clearMaskOnSelect = clear;
		return this;
	}
	
	public String getValue() {
		if(showHint || this.getText().equals(STD_HINT_TEXT)) {
			return "";
		} else {
			return this.getText();
		}
	}
	
	private String chooseAndApplyMask(String text) {
		for(ConditionalMask mask: masks) {
			if(mask.isValid(text)) {
				text = applyMask(text, mask.getMask());
				break;
			}
		}
		return text;
	}
	
	private String applyMask(String text, String mask) {
		String currentText = text.replaceAll("\\W", "");
		StringBuilder rT = new StringBuilder();
        int i = 0;// Variável de controle da posição da String verdadeira
        for (int j = 0; j < mask.length(); j++) {
        	if(j > currentText.length()) {
        	    break;
        	}
            if(mask.charAt(j) == '#') {
            	try {
            		 rT.append(currentText.charAt(i));
				} catch (Exception e) {
					// TODO: handle exception
				}
               
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