package view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import validate.ValidationRule;
import view.panels.components.GenericComponent;
import view.panels.components.GenericJPanel;

public class FormBoxInput extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	private JLabel errorLabel;
	private FormInputField tfInput;
	private JComboBox<String> cbInput;
	
	private Function<String, Object> resMiddleware;
	
	public FormBoxInput(GenericJPanel ownerPanel) {
		super(ownerPanel);
		ltGridBag();
		
		label = new JLabel("");
		label.setFont(STD_BOLD_FONT.deriveFont(15f));
		this.add(label, gbc.fill("BOTH").grid(0).insets(5, 15, 0, 15).anchor("WEST"));
		
		tfInput = new FormInputField("Digite aqui...", new Dimension(10, 30), 12f).setFormBoxInput(this);
		tfInput.setMinimumSize(new Dimension(100, 20));
		this.add(tfInput, gbc.yP().insets(0, 15).wgt(1.0, 0));
		
		errorLabel = new JLabel("");
		errorLabel.setFont(STD_BOLD_FONT.deriveFont(12f));
		this.add(errorLabel, gbc.yP().insets(0, 15, 5, 15));
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
		
		this.add(cbInput, gbc.yP().insets(0, 15).wgt(1.0, 0));
		this.add(errorLabel, gbc.yP().insets(0, 15, 5, 15)); //Readicionando errorLabel para adicionar o "espaço" faltante embaixo da comboBox
		return this;
	}
	
	public FormBoxInput setLbl(String text) {
		label.setText(text);
		return this;
	}
	
	public FormBoxInput setHint(String text) {
		try {
			tfInput.setHint(text);
		} catch (Exception e) {
			System.out.println("Esse FormBoxInput não utiliza TextField para input");
		}

		return this;
	}
	
	public FormBoxInput setSize() {
		return this;
	}
	
	public FormBoxInput setMask(String mask) {
		try {
			tfInput.setMask(mask);
		} catch (Exception e) {
			System.out.println("Esse FormBoxInput não utiliza TextField para input");
		}

		return this;
	}
	
	public FormBoxInput setValue(String text) {
		if(text != null) {
			if(text.equalsIgnoreCase("null")) return this;
			try {
				tfInput.setInitialValue(text);
			} catch (Exception e) {
				try {
					cbInput.setSelectedItem(text);
				} catch(Exception e2) {
				}
				
			}
		}
		return this;
	}
	
	public FormBoxInput clearMaskOnSelect(boolean clear) {
		try {
			tfInput.clearMaskOnSelect(clear);
		} catch (Exception e) {
			System.out.println("Esse FormBoxInput não utiliza TextField para input");
		}
		
		return this;
	}
	
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
	
	public boolean isWrong() {
		if(!errorLabel.getText().isBlank()) {
			return true;
		}
		return false;
	}
	
	public String getErrorText() {
		return label.getText() +": "+errorLabel.getText();
	}
	
	public String getValue() {
		String response;
		if(tfInput != null) {
			response = tfInput.getValue().toString();
		} else if(cbInput != null ) {
			response = (String) cbInput.getSelectedItem().toString();
		} else {
			response = null;
		}
		
		return response;
	}
	
	public static ArrayList<FormBoxInput> validateFields(FormBoxInput ...fields) {
		ArrayList<FormBoxInput> wrongFields = new ArrayList<FormBoxInput>();
		for(FormBoxInput field: fields) {
			if(field.isWrong()) wrongFields.add(field);
		}
		
		return wrongFields;
	}
		
}