/**
 * Package que contém as classes do componente VS-FlexFormUI(Vertical Scale - Flex Form User Interface)
 */
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
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import utils.interfaces.Condition;
import utils.validations.ValidationRule;
import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.generics.GenericComponent;
import view.components.generics.GenericJPanel;

/**
 * Componente personalizado para definir um campo de formulário do componente VS-FlexFormUI.
 * É possível definir o campo de diversas maneiras, como TextField, ComboBox, Spinner, Botão e Título.
 * É possível aplicar rótulos (labels), regras de validação e máscaras de entrada condicional.
 */
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
	
	private boolean lockInput;
	private boolean initiated = false;

	/**
	 * Contructor que recebe o panel que o chamou.
	 * @param ownerPanel GenericJPanel que chamou esse FormBox
	 */
	public FormBoxInput(GenericJPanel ownerPanel) {
		super(ownerPanel);
		ltGridBag();
		required = false;
		
		initComponents();
	}
	
	/**
	 * Método que inicializa os componentes básicos.
	 */
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
		lockInput = false;
	}
	
	/**
	 * Método que inicializa o componente depois de configurá-lo.
	 * @return O próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput init() {
		placeComponents();
		if(lockInput) applyLock();
		initiated = true;
		return this;
	}
	
    /**
     * Posiciona os componentes dentro do layout.
     */
	private void placeComponents() {
		this.add(label, gbc.fill("BOTH").grid(0).insets(upperDistance, leftDistance, 0, rightDistance).anchor("WEST").width("REMAINDER"));
		
		if(button != null) {
			gbc.yP().insets(0, leftDistance, 0, btnDistance).wgt((1.0 - btnWgt), 0).width(1); //Se o botão existir, aplica uma configuração diferente.
		} else {
			gbc.yP().insets(0, leftDistance, 0, rightDistance).wgt(1.0, 0);
		}
		
		try {
			this.add(tfInput, gbc);
			if(lockInput) {
				tfInput.setEnabled(false);
			}

		} catch (Exception e) {
			try {
				this.add(cbInput, gbc);
				if(lockInput) {
					cbInput.setEnabled(false);
				}

			} catch (Exception e2) {
				try {
					this.add(spinnerInput, gbc);
					if(lockInput) {
						spinnerInput.setEnabled(false);
					}
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
	
    /**
     * Obtém o texto do rótulo do formulário.
     * @return O texto do rótulo.
     */
	public String getLabel() {
		return label.getText();
	}
	
    /**
     * Define a distância superior do campo.
     * @param distance A distância superior desejada.
     * @return O próprio objeto para implementar fluent interface.
     */
	public FormBoxInput setUpperDistance(int distance) {
		upperDistance = distance;
		return this;
	}
	
    /**
     * Define a distância inferior do campo.
     * @param distance A distância inferior desejada.
     * @return O próprio objeto para implementar fluent interface.
     */
	public FormBoxInput setLowerDistance(int distance) {
		lowerDistance = distance;
		return this;
	}
	
    /**
     * Define a distância direita do campo.
     * @param distance A distância direita desejada.
     * @return O próprio objeto para implementar fluent interface.
     */
	public FormBoxInput setRightDistance(int distance) {
		rightDistance = distance;
		return this;
	}
	
    /**
     * Define a distância esquerda do campo.
     * @param distance A distância esquerda desejada.
     * @return O próprio objeto para implementar fluent interface.
     */
	public FormBoxInput setLeftDistance(int distance) {
		leftDistance = distance;
		return this;
	}
	
    /**
     * Define a distância lateral do campo.
     * @param left A distância esquerda desejada.
     * @param right A distância direita desejada.
     * @return O próprio objeto para implementar fluent interface.
     */
	public FormBoxInput setLateralDistance(Integer left, Integer right) {
		if(left != null) leftDistance = left;
		if(right != null) rightDistance = right;
		return this;
	}
	
    /**
     * Define a distância lateral do campo (mesma distância para esquerda e direita).
     * @param distance A distância lateral desejada.
     * @return O próprio objeto para implementar fluent interface.
     */
	public FormBoxInput setLateralDistance(int distance) {
		return setLateralDistance(distance, distance);
	}
	
    /**
     * Define a distância do botão em relação ao input, caso seja mantido.
     * @param distance A distância desejada do botão.
     * @return O próprio objeto para implementar fluent interface.
     */
	public FormBoxInput setBtnDistance(int distance) {
		btnDistance = distance;
		return this;
	}
	
    /**
     * Define o peso do botão dentro do layout.
     * @param value O peso do botão desejado.
     * @return O próprio objeto para implementar fluent interface.
     */
	public FormBoxInput setBtnWgt(double value) {
		btnWgt = value;
		return this;
	};
	
	/**
	 * Método que bloqueia o input do usuário.
	 * @return FormBoxInput para fluent interface.
	 */
	public FormBoxInput lockInput() {
		lockInput = true;
		if(hasInitiated()) applyLock();
		return this;
	}
	
	/**
	 * Método que desbloqueia o input do usuário.
	 * @return FormBoxInput para fluent interface.
	 */
	public FormBoxInput unlockInput() {
		lockInput = false;
		if(hasInitiated()) applyUnlock();
		return this;
	}
	
	/**
	 * Método que aplica a trava do campo.
	 */
	private void applyLock() {
        Color disabledColor = STD_STRONG_GRAY_COLOR;

        try {
            tfInput.setEnabled(false);
            tfInput.setDisabledTextColor(disabledColor);
            tfInput.setBackground(Color.WHITE);
            tfInput.setForeground(STD_BLACK_COLOR);
        } catch (Exception e) {
            try {
                cbInput.setEnabled(false);
                cbInput.setForeground(disabledColor);
                cbInput.setBackground(Color.WHITE);
            } catch (Exception e2) {
                try {
                    spinnerInput.setEnabled(false);
                    JComponent editor = spinnerInput.getEditor();
                    if(editor instanceof JSpinner.DefaultEditor) {
                        JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
                        textField.setDisabledTextColor(disabledColor);
                        textField.setBackground(Color.WHITE);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
    }
	
	/**
	 * Método que tira a trava do campo.
	 */
	private void applyUnlock() {
		try {
			if(lockInput) {
				tfInput.setEnabled(true);
			}
		} catch (Exception e) {
			try {
				if(lockInput) {
					cbInput.setEnabled(true);
				}

			} catch (Exception e2) {
				try {
					if(lockInput) {
						spinnerInput.setEnabled(true);
					}
				} catch (Exception e3) {}
			}
		}
	}
	
	/**
	 * Método que indica se o objeto já foi inicializado. Usado para caso tenha sido inicializado e depois as configurações mudem, o objeto precise ser refeito.
	 * @return
	 */
	private boolean hasInitiated() {
		return initiated;
	}
	
	
    /**
     * Define o campo como obrigatório.
     * @return O próprio objeto para implementar fluent interface.
     */
	public FormBoxInput setRequired() {
		required = true;
		String holderLabel = this.label.getText();
		this.label.setText(holderLabel + "*");
		return this;
	}
	
	/**
	 * Indica se o campo é obrigatório
	 * @return True se for obrigatório, false caso contrário.
	 */
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
	
	/**
	 * Define as fontes do FormBox caso seja um comboBox. 
	 * @param selectedItem -> Fonte do item selecionado.
	 * @param dropBoxItems -> Fonte dos itens da lista.
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
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
		} catch (Exception e) {}
		
		return this;
	}
	
	/**
	 * Define uma mesma fonte para o item selecionado e a lista de itens. 
	 * @param font -> Fonte do item selecionado e da lista.
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput setComboFont(Font font) {
		return setComboFont(font, font);
	}
	
	/**
	 * Define o formbox como um spinner.
	 * @param initalValue - Valor inicial do spinner.
	 * @param min - valor mínimo
	 * @param max - valor máximo
	 * @param step - número que será somado ou subtraído ao clicar para aumentar ou diminuir o valor atual.
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
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
	 * @param keepInput - valor booleano que mantém o input do usuário. True se o formbox precise ter um input + um botão ou false para ter apenas um botão.
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
	
	/**
	 * Método que define o formBox como um título ao invés de um input de usuário.
	 * @param text -> Texto a ser exibido.
	 * @param center -> Se o texto deve ser centralizado.
	 * @param font -> Fonte do texto.
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
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
	
	/**
	 * Método para definir a label e a fonte.
	 * @param text - Texto da label
	 * @param font - Fonte da label
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput setLbl(String text, Font font) {
		label.setText(text);
		label.setFont(font);
		return this;
	}
	
	/**
	 * Método para definir a label e o tamanho da fonte, usando a fonte padrão.
	 * @param text - Texto da label
	 * @param size - Tamanho da fonte
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
	public FormBoxInput setLbl(String text, float size) {
		label.setText(text);
		label.setFont(label.getFont().deriveFont(size));
		return this;
	}
	
	/**
	 * Método para definir a cor da label
	 * @param color - cor da label.
	 * @return Retorna o próprio objeto para implementar fluent interface.
	 */
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
	
	/**
	 * Método que define uma máscara e sua condição de aplicação.
	 * @param mask - String da máscara. Use # para definir caracteres curingas.
	 * @param condition - Condição para que a máscara seja aplicada. 
	 * @return
	 */
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
	
	/**
	 * Método para definir a label de erro no preenchimento do campo.
	 * @param text - Texto da label de erro
	 */
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
	 * @param fields - varargs com FormBoxInput.
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
		
}