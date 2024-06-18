package view.frames;

import java.awt.Dimension;

import controller.app.AuthController;
import model.entities.User;
import utils.validations.Validate;
import utils.validations.ValidationRule;
import utils.view.LanguageUtil;
import view.QuestNutri;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJPanel;

/**
 * Frame para alteração de dados de usuário.
 * Extende SubFrameFromMain para manter a consistência visual e funcional do aplicativo.
 */
public class ChangeUserFrame extends SubFrameFromMain {
	private static final long serialVersionUID = 1L;

	GenericJPanel panel = new GenericJPanel().ltGridBag().setBGColor(STD_WHITE_COLOR);
	
    /**
     * Regra de validação para tamanho de strings entre min e max caracteres.
     * 
     * @param min tamanho mínimo da string
     * @param max tamanho máximo da string
     * @return objeto ValidationRule para validação de tamanho de string
     */
	protected ValidationRule strSizeBetween(int min, int max) {
		return new ValidationRule(value -> {
			if(value.length() != 0) {
				return value.length() >= min && value.length() <= max;
			} else {
				return true;
			}
			
		} , new LanguageUtil("Campo precisa ter entre "+min+ " e "+max+".", "Field size must be between "+min+ " and "+max+".").get());
	}
	
    /**
     * Construtor para ChangeUserFrame.
     * 
     * @param user objeto User contendo os dados do usuário a ser alterado
     */
	public ChangeUserFrame(User user) {
		super();
		setContentPane(panel);
		
		this.setPreferredSize(new Dimension(500, 300));
		this.setMinimumSize(getPreferredSize());
		this.setMaximumSize(getPreferredSize());
		this.setResizable(false);
		
		
		FormBoxInput userName = new FormBoxInput(panel).setLbl("Username")
													   .addValidation(
														 	   new ValidationRule(
														 			   value -> {
														 				   return Validate.sizeBetween(value, 5, 10);
																	   }, new LanguageUtil("Username precisa estar entre 5 e 10", "Username size need to be between 5 and 10").get()),
													   
															   new ValidationRule(
																	   value -> {
													  						User s = User.findOne(" login LIKE '"+value+ "' ");
													  						if(s == null || (s.getId() == user.getId())) {
													  							return true;
													  						}
													  						return false;
													  					 }, new LanguageUtil("Username já existe", "Username already exists").get())
															   )
													   .setValue(user.getLogin())
													   .setRequired();

		FormBoxInput password = new FormBoxInput(panel).setLbl(new LanguageUtil("Senha", "Password").get()).setRequired().addValidation(strSizeBetween(5, 30));
		
		FormBoxInput firstName = new FormBoxInput(panel).setLbl(new LanguageUtil("Primeiro nome", "First name").get()).setValue(user.getFirstName());
		
		FormBoxInput accessLevel = new FormBoxInput(panel).setLbl(new LanguageUtil("Nível de acesso", "Access Level").get())
							 							  .setComboBoxInput(user.getSystemLevel().toString(), "1", "2");
		
		FormSection updateUser = new FormSection(panel).setUpName(new LanguageUtil("Atualizar Usuário", "Update User").get())
													   .addRow(userName, password)
													   .addRow(firstName, accessLevel)
													   .setInteractBtn(StdButton.stdBtnConfig(new LanguageUtil("Salvar", "Save").get())
															   .setAction(() -> {
																   if(Validate.formFields(userName, password)) {
																	   String name = firstName.getValue().isBlank() ? "{user}" : firstName.getValue();
																	  QuestNutriJOP.showMessageDialog(null, AuthController.updateFullUser(user, userName.getValue(), password.getValue(), name, Integer.parseInt(accessLevel.getValue()), () -> {
																		this.dispose();
																		QuestNutri.heraldOfDarkness();
																	  }));
																   }
															   }));
		
		panel.add(updateUser.init(), panel.gbc.fill("BOTH").wgt(1.0));
		panel.refresh();
		
		this.revalidate();
		this.repaint();
		
	}

}