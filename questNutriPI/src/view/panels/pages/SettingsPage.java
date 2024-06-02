package view.panels.pages;

import controller.InterfaceController;
import model.entities.User;
import validate.Validate;
import validate.ValidationRule;
import view.QuestNutri;
import view.components.FormBoxInput;
import view.components.FormSection;
import view.components.QuestNutriJOP;
import view.components.StdButton;
import view.panels.components.GenericJPanel;
import view.utils.LanguageUtil;

public class SettingsPage extends GenericPage {
	private static final long serialVersionUID = 1L;

	public SettingsPage(GenericJPanel ownerPanel) {
		super(ownerPanel);
		ltGridBag();
		
		FormBoxInput userName = new FormBoxInput(this).setLbl("Username")
													  .setValue("Admin")
													  .addValidation(
															  new ValidationRule(
																	  value -> {
																		  return Validate.sizeBetween(value, 5, 10);
																	  }, new LanguageUtil("Username precisa estar entre 5 e 10", "Username size need to be between 5 and 10").get()) 
													   );
		
		FormBoxInput password = new FormBoxInput(this).setLbl(new LanguageUtil("Senha", "Password").get());
		
		FormBoxInput accessLevel = new FormBoxInput(this).setLbl(new LanguageUtil("Nível de acesso", "Access Level").get())
														 .setComboBoxInput("1", "1", "2");
		
		FormSection addNewUser = new FormSection(this).setUpName(new LanguageUtil("Criar Novo Usuário", "Create New User").get())
													  .addRow(userName, password, accessLevel)
													  .setInteractBtn(StdButton.stdBtnConfig(new LanguageUtil("Criar", "Create").get()))
													  .init();
		
		this.add(addNewUser, gbc.anchor("NORTHWEST").wgt(1.0).fill("HORIZONTAL").grid(0));
		
		FormBoxInput language = new FormBoxInput(this).setLbl(new LanguageUtil("Linguagem", "Language").get())
													  .setComboBoxInput(new LanguageUtil("PT-BR", "EN-US").get(), "PT-BR", "EN-US");
		language.setButtonBox(StdButton.stdBtnConfig(new LanguageUtil("Salvar", "Save").get())
																					   .setAction(() -> {
																						   QuestNutri.changeLanguage(language.getValue());
																					   }), true);
		
		FormSection sysConfig = new FormSection(this).setUpName(new LanguageUtil("Configurações do Sistema", "System Settings").get())
													 .addRow(language)
													 .init();
		
		this.add(sysConfig, gbc.yP().insets(10, 15));
		
		this.add(InterfaceController.createTable(User.findAll(), 
												new Object[] {"Username", new LanguageUtil("Nível de acesso", "System Access").get()}, 
												user -> new Object[] {user.getLogin(), user.getSysLevel()},
												user -> () -> {
													QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Você clicou no usuário: ", "You clicked on the user: ").get() + user.getLogin());
												}), gbc.yP().insets(10, 15).fill("BOTH").wgt(1.0));
	}
}
