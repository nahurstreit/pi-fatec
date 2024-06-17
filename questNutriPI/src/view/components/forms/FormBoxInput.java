package view.components.forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import utils.interfaces.Condition;
import utils.validations.ValidationRule;
import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.generics.GenericComponent;
import view.components.generics.GenericJPanel;

public class FormBoxInput extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	private JLabel errorLabel;
	private FormInputField tfInput;
	private JComboBox<String> cbInput;
	private JSpinner spinnerInput;
	private StdButton button;
	private JLabel centralTitle;
	
	private boolean required;
	
	private final int STD_LATERAL_DISTANCE = 15;
	private final int STD_UPPER_DISTANCE = 5;
	private final int STD_LOWER_DISTANCE = 5;
	private final int STD_BTN_DISTANCE = 5;
	private final double STD_BTN_WGT = 0.1;
	
	private int upperDistance;
	private int leftDistance;
	private int lowerDistance;
	private int rightDistance;
	private int btnDistance;
	private double btnWgt;

	private int spinnerValue;
	
	public interface ValueChangedListener {
	    void valueChanged(String newValue);
	}
	
	private ValueChangedListener valueChangedListener;

	
	public FormBoxInput(GenericJPanel ownerPanel) {
		super(ownerPanel);
		ltGridBag();
		required = false;
		
		initComponents();
	}
	
	private void initComponents() {
		label = new JLabel("");
		label.setFont(STD_BOLD_FONT.deriveFont(15f));
		
		tfInput = new FormInputField(new LanguageUtil("Digite aqui...", "Type here...").get(), new Dimension(10, 30), 12f).setFormBoxInput(this);
		tfInput.setMinimumSize(new Dimension(100, 20));
		
		tfInput.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				setErrorLbl("");
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		
		errorLabel = new JLabel("");
		errorLabel.setFont(STD_BOLD_FONT.deriveFont(12f));
		
		upperDistance = STD_UPPER_DISTANCE;
		leftDistance = STD_LATERAL_DISTANCE;
		lowerDistance = STD_LOWER_DISTANCE;
		rightDistance = STD_LATERAL_DISTANCE;
		btnDistance = STD_BTN_DISTANCE;
		btnWgt = STD_BTN_WGT;
	}
	
	public FormBoxInput init() {
		placeComponents();
		return this;
	}
	
	private void placeComponents() {
		this.add(label, gbc.fill("BOTH").grid(0).insets(upperDistance, leftDistance, 0, rightDistance).anchor("WEST").width("REMAINDER"));
		
		if(button != null) {
			gbc.yP().insets(0, leftDistance, 0, btnDistance).wgt((1.0 - btnWgt), 0).width(1); //Se o botão existir, aplica uma configuração diferente.
		} else {
			gbc.yP().insets(0, leftDistance, 0, rightDistance).wgt(1.0, 0);
		}
		
		try {
			this.add(tfInput, gbc);
		} catch (Exception e) {
			try {
				this.add(cbInput, gbc);
			} catch (Exception e2) {
				try {
					this.add(spinnerInput, gbc);
				} catch (Exception e3) {
					try {
						this.add(centralTitle, gbc);
					} catch (Exception e4) {}
					
				}
			}
		}
		
		if(button != null) {
			if(tfInput != null || cbInput != null || spinnerInput != null) { //Se algum dos outros inputs ainda existirem, o botão será posicionado ao lado.
				gbc.wgt(btnWgt, 0).grid(1).insets(0, 0, 0, rightDistance).width(1);
			} else {
				gbc.wgt(1.0, 0).grid(0, 1).insets(0, leftDistance, 0, rightDistance);
			}
			
			this.add(button, gbc);
			gbc.gridX(0);
		}

		this.add(errorLabel, gbc.yP().insets(0, leftDistance, lowerDistance, rightDistance).width("REMAINDER"));
	}
	
	public String getLabel() {
		return label.getText();
	}
	
	public FormBoxInput setUpperDistance(int distance) {
		upperDistance = distance;
		return this;
	}
	
	public FormBoxInput setLowerDistance(int distance) {
		lowerDistance = distance;
		return this;
	}
	
	public FormBoxInput setRightDistance(int distance) {
		rightDistance = distance;
		return this;
	}
	
	public FormBoxInput setLeftDistance(int distance) {
		leftDistance = distance;
		return this;
	}
	
	public FormBoxInput setLateralDistance(Integer left, Integer right) {
		if(left != null) leftDistance = left;
		if(right != null) rightDistance = right;
		return this;
	}
	
	public FormBoxInput setLateralDistance(int distance) {
		return setLateralDistance(distance, distance);
	}
	
	public FormBoxInput setBtnDistance(int distance) {
		btnDistance = distance;
		return this;
	}
	
	public FormBoxInput setBtnWgt(double value) {
		btnWgt = value;
		return this;
	};
	
	/**
	 * Método que bloqueia o input do usuário.
	 * @return FormBoxInput para fluent interface.
	 */
	public FormBoxInput lockInput() {
		if(tfInput != null) {
			tfInput.setEditable(false);
		} else if(cbInput != null) {
			cbInput.setEditable(false);
		}
		
		return this;
	}
	
	/**
	 * Método que desbloqueia o input do usuário.
	 * @return FormBoxInput para fluent interface.
	 */
	public FormBoxInput unlockInput() {
		if(tfInput != null) {
			tfInput.setEditable(true);
		} else if(cbInput != null) {
			cbInput.setEditable(true);
		}
		return this;
	}
	
	public FormBoxInput setRequired() {
		required = true;
		String holderLabel = this.label.getText();
		this.label.setText(holderLabel + "*");
		return this;
	}
	
	public boolean isRequired() {
		return this.required;
	}
	
	/**
	 * Muda a maneira de input do FormBoxInput para ser um comboBox ao invés de um JTextField
	 * @param initialValue - Valor inicial do ComboBox. Se enviado null, o valor inicial será o mesmo que o da lista.
	 * @param values - Valores disponíveis no ComboBox
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput setComboBoxInput(String initialValue, String ...values) {
        //Criação da drop box com os possíveis valores de pesquisa
		cbInput = new JComboBox<>(values);
		
		//Removendo o tfInput
		this.remove(tfInput);
		tfInput = null;
		this.remove(errorLabel);
		
        //Personalizando a fonte no item selecionado
		cbInput.setFont(GenericJPanel.STD_BOLD_FONT.deriveFont(16f));
		
        //Personalizando a fonte dos itens na JComboBox
		cbInput.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;
            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setFont(GenericJPanel.STD_MEDIUM_FONT.deriveFont(15f));
                return this;
            }
        });
		
		if(initialValue != null) {
			cbInput.setSelectedItem(initialValue);
		}
		
		return this;
	}
	
	public FormBoxInput setComboFont(Font selectedItem, Font dropBoxItems) {
		try {
			 //Personalizando a fonte no item selecionado
			cbInput.setFont(selectedItem);
			
			cbInput.setRenderer(new DefaultListCellRenderer() {
	            private static final long serialVersionUID = 1L;
	            @Override
	            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	                setFont(dropBoxItems);
	                return this;
	            }
	        });
		} catch (Exception e) {
			//TODO: handle exception
		}
		
		return this;
	}
	
	public FormBoxInput setComboFont(Font font) {
		return setComboFont(font, font);
	}
	
	
	public FormBoxInput setSpinnerInput(int initalValue, int min, int max, int step) {
		spinnerInput = new JSpinner();
		spinnerInput.setEditor(new JSpinner.NumberEditor(spinnerInput, "00")); //Formato para dois dígitos
		spinnerInput.setModel(new SpinnerNumberModel(initalValue, min, max, step));
		spinnerInput.setFont(STD_REGULAR_FONT);
		if(tfInput != null) {
			this.remove(tfInput);
			tfInput = null;
		}
		if(cbInput != null) {
			this.remove(cbInput);
			cbInput = null;
		}
		
	    spinnerInput.addChangeListener(e -> {
	    	spinnerValue = (int) spinnerInput.getValue();
	    });
		
		return this;
	}
	
	/**
	 * Adiciona um botão ao box input.
	 * @param button - Objeto de StdButton para ser adicionado.
	 * @param keepInput - valor booleano que mantém o input do usuário.
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput setButtonBox(StdButton button, boolean keepInput) {
		if(!keepInput) {
			try {
				tfInput = null;
				cbInput = null;
				spinnerInput = null;
			} catch (Exception e) {
				
			}
			
		}
		this.button = button;
		
		return this;
	}
	
	public FormBoxInput setCentralTitle(String text, boolean center, Font font) {
		try {
			tfInput = null;
			cbInput = null;
			spinnerInput = null;
			button = null;
		} catch (Exception e) {
		}
		
		centralTitle = new JLabel();
		
		if(center) {
			centralTitle = new JLabel(text, JLabel.CENTER);
		} else {
			centralTitle = new JLabel(text);
		}
		centralTitle.setFont(font);
		
		return this;
		
	}
	
	/**
	 * Método para definir a label de exibição do Box.
	 * @param text - Texto da label
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput setLbl(String text) {
		label.setText(text);
		return this;
	}
	
	public FormBoxInput setLbl(String text, Font font) {
		label.setText(text);
		label.setFont(font);
		return this;
	}
	
	public FormBoxInput setLbl(String text, float size) {
		label.setText(text);
		label.setFont(label.getFont().deriveFont(size));
		return this;
	}
	
	public FormBoxInput setLblColor(Color color) {
		label.setForeground(color);
		return this;
	}
	
	/**
	 * Método que define a dica de preenchimento do input de textField
	 * @param text - Texto de dica de preenchimento
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput setHint(String text) {
		try {
			tfInput.setHint(text);
		} catch (Exception e) {
			System.out.println("Esse FormBoxInput não utiliza TextField para input");
		}

		return this;
	}
	
	/**
	 * Método que define a máscara de exibição do input de texto.
	 * @param mask - String da máscara. Use # para definir caracteres curingas.
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput setMask(String mask) {
		try {
			tfInput.setMask(mask);
		} catch (Exception e) {
			System.out.println("Esse FormBoxInput não utiliza TextField para input");
		}

		return this;
	}
	
	public FormBoxInput setMask(String mask, Condition condition) {
		try {
			tfInput.setMask(mask, condition);
		} catch (Exception e) {
			System.out.println("Esse FormBoxInput não utiliza TextField para input");
		}

		return this;
	}
	
	/**
	 * Método que define o valor inicial do input.
	 * @param text - Texto inicial.
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput setValue(String text) {
        if(text != null) {
            try {
            	if(text.equalsIgnoreCase("null")) return this;
                if(tfInput != null) tfInput.setInitialValue(text);
                if(cbInput != null) cbInput.setSelectedItem(text);
                if(spinnerInput != null) {
                    spinnerInput.setValue(Integer.parseInt(text));
                }
                if(centralTitle != null) centralTitle.setText(text);
                if(valueChangedListener != null) {
                	notifyValueChanged(text);
                }
                // Adicionar notificação para cbInput também, se necessário
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return this;
    }
	

	/**
	 * Método para definir se a máscara do input será retirada quando o textField ganhar foco.
	 * @param clear - valor booleano que define isso. Por padrão esse campo sempre é interpretado como false.
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput clearMaskOnSelect(boolean clear) {
		try {
			tfInput.clearMaskOnSelect(clear);
		} catch (Exception e) {
			System.out.println("Esse FormBoxInput não utiliza TextField para input");
		}
		
		return this;
	}
	
	/**
	 * Método que implementa mecanismos de validação no input de textField.
	 * @param rules - Varargs de objetos do tipo ValidationRule veja em: {@link utils.validations.ValidationRule}.
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput addValidation(ValidationRule ...rules) {
		try {
			tfInput.addValidationRule(rules);
		} catch (Exception e) {
			System.out.println("Esse FormBoxInput não utiliza TextField para input");
		}
        return this;
    }
	
	
	public void setErrorLbl(String text) {
		try {
			errorLabel.setText(text);
			this.setForeground(Color.red);
		} catch (Exception e) {
			System.out.println("Esse FormBoxInput não utiliza TextField para input");
		}
	}
	
	/**
	 * Método que retorna se o campo estiver preenchido errado.
	 * @return valor booleano que diz se o campo está preenchido errado ou não.
	 */
	public boolean isWrong() {
		if(!errorLabel.getText().isBlank()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Método que retorna se a dica de preenchimento está ativa.
	 * @return valor booleano que indica se a dica de preenchimento está ativa.
	 */
	public boolean isHintOn() {
		if(tfInput != null) {
			return tfInput.isShowingHint();
		} else {
			return true;
		}
	}
	
	/**
	 * Método que retorna qual o texto de erro sendo exibido no box.
	 * @return String com o erro do box.
	 */
	public String getErrorText() {
		return label.getText() +": "+errorLabel.getText();
	}
	
	/**
	 * Método que retorna o valor de input do box, seja como textField ou como comboBox.
	 * @return String contendo o valor.
	 */
	public String getValue() {
		String response;
		if(tfInput != null) {
			response = tfInput.getValue().toString();
		} else if(cbInput != null ) {
			response = (String) cbInput.getSelectedItem().toString();
		} else if(spinnerInput != null){
			try {
				response = spinnerValue + "";
			} catch (Exception e) {
				e.printStackTrace();
				response = "00";
			}
		} else {
			response = null;
		}
		
		return response;
	}
	
	/**
	 * Método estático que valida se os box passados como parâmetros, em fields, estão sem erros de preenchimento.
	 * @param fiels - varargs com FormBoxInput.
	 * @return ArrayList de FormBoxInput, contendo os campos que estão incorretos.
	 */
	public static ArrayList<FormBoxInput> validateFields(FormBoxInput ...fields) {
		ArrayList<FormBoxInput> wrongFields = new ArrayList<FormBoxInput>();
		for(FormBoxInput field: fields) {
			if(field.isRequired() && field.isHintOn() && field.cbInput == null) {
				field.setErrorLbl(new LanguageUtil("Campo obrigatório!", "Required Field!").get());
			}
			if(field.isWrong()) wrongFields.add(field);
		}
		
		return wrongFields;
	}
	
	public FormBoxInput setValueChangedListener(ValueChangedListener listener) {
        this.valueChangedListener = listener;
        return this;
    }
	
	private void notifyValueChanged(String newValue) {
        if(valueChangedListener != null) {
            valueChangedListener.valueChanged(newValue);
        }
    }
		
}