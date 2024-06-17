package view.pages.settings;

import javax.swing.JScrollPane;

import controller.app.AuthController;
import model.entities.User;
import utils.ConfirmDialog;
import utils.validations.Validate;
import utils.validations.ValidationRule;
import utils.view.LanguageUtil;
import view.QuestNutri;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJPanel;
import view.pages.generics.GenericPage;

public class SettingsPage extends GenericPage {
	private static final long serialVersionUID = 1L;
	
	JScrollPane scrollPane = new JScrollPane();
	GenericJPanel holder = new GenericJPanel().ltGridBag().setBGColor(STD_WHITE_COLOR);
	
	JScrollPane userList = new JScrollPane();
	
	protected ValidationRule strSizeBetween(int min, int max) {
		return new ValidationRule(value -> {
			if(value.length() != 0) {
				return value.length() >= min && value.length() <= max;
			} else {
				return true;
			}
			
		} , new LanguageUtil("Campo precisa ter entre "+min+ " e "+max+".", "Field size must be between "+min+ " and "+max+".").get());
	}
	
	public SettingsPage(GenericJPanel ownerPanel) {
		super(ownerPanel);
		ltGridBag();
		
		FormBoxInput userName = new FormBoxInput(this).setLbl("Username")
													  .addValidation(
															  new ValidationRule(
																	  value -> {
																		  return Validate.sizeBetween(value, 5, 10);
																	  }, new LanguageUtil("Username precisa estar entre 5 e 10", "Username size need to be between 5 and 10").get()),
													   
															  new ValidationRule(
																	  value -> {
																		  return User.findOne(" login LIKE '"+value + "' ") == null;
													  					}, new LanguageUtil("Username já existe", "Username already exists").get())
															  ).setRequired();
		
		FormBoxInput password = new FormBoxInput(this).setLbl(new LanguageUtil("Senha", "Password").get()).setRequired().addValidation(strSizeBetween(5, 30));
		
		FormBoxInput firstName = new FormBoxInput(this).setLbl(new LanguageUtil("Primeiro nome", "First name").get());
		
		FormBoxInput accessLevel = new FormBoxInput(this).setLbl(new LanguageUtil("Nível de acesso", "Access Level").get())
														 .setComboBoxInput("1", "1", "2");
		
		FormSection addNewUser = new FormSection(this).setUpName(new LanguageUtil("Criar Novo Usuário", "Create New User").get())
													  .addRow(userName, password)
													  .addRow(firstName, accessLevel)
													  .setInteractBtn(StdButton.stdBtnConfig(new LanguageUtil("Criar", "Create").get())
															  .setAction(() -> {
																  if(Validate.formFields(userName, password)) {
																	  String name = firstName.getValue().isBlank() ? "{user}" : firstName.getValue();
																	  QuestNutriJOP.showMessageDialog(null, AuthController.createUser(userName.getValue(), password.getValue(), name, Integer.parseInt(accessLevel.getValue()), this::updateUsersList ));
																  }
															  }))
													  .init();
		
		

		if(QuestNutri.isEditAuth()) {
			holder.add(addNewUser, holder.gbc.grid(0).anchor("NORTHWEST").wgt(1.0).fill("BOTH"));
		}
		
		FormBoxInput changeUserName = new FormBoxInput(this).setLbl("Username")
															.setValue(User.findByPK(QuestNutri.getLoggedUserId()).getLogin())
														  	.addValidation(
														  			new ValidationRule(
														  					value -> {
														  						return Validate.sizeBetween(value, 5, 10);
																	  }, new LanguageUtil("Username precisa estar entre 5 e 10", "Username size need to be between 5 and 10").get()),
														  			new ValidationRule(
														  					value -> {
														  						User s = User.findOne(" login LIKE '"+value+ "' ");
														  						if(s == null || (s.getId() == QuestNutri.getLoggedUserId())) {
														  							return true;
														  						}
														  						return false;
														  					}, new LanguageUtil("Username já existe", "Username already exists").get())
														  	).setRequired();

		FormBoxInput changePassword = new FormBoxInput(this).setLbl(new LanguageUtil("Senha", "Password").get()).addValidation(strSizeBetween(5, 30)).setRequired();
		
		FormSection changeUser = new FormSection(this).setUpName(new LanguageUtil("Atualizar usuário", "Update user").get())
													  .addRow(changeUserName, changePassword)
													  .setInteractBtn(StdButton.stdBtnConfig(new LanguageUtil("Salvar", "Save").get())
															  .setAction(() -> {
																  if(Validate.formFields(changeUserName, changePassword)) {
																	  try {
																		  if(QuestNutri.updateLoggedUser(changeUserName.getValue(), changePassword.getValue())) {
																			  QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Usuário alterado!", "User updated!").get());
																		  } else {
																			  QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível alterar o usuário.", "User updated have failed.").get());
																		  }
																	} catch (Exception e) {
																		e.printStackTrace();
																		QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Erro interno do sistema", "Server internal error").get());
																	}
																  }
															  })
													  );
		
		if(!QuestNutri.isEditAuth()) holder.gbc.grid(0);
		holder.add(changeUser.init(), holder.gbc.yP().fill("BOTH").wgt(1.0));
				
		
		FormBoxInput language = new FormBoxInput(this).setLbl(new LanguageUtil("Linguagem", "Language").get())
													  .setComboBoxInput(new LanguageUtil("PT-BR", "EN-US").get(), "PT-BR", "EN-US");
		language.setButtonBox(StdButton.stdBtnConfig(new LanguageUtil("Salvar", "Save").get())
																					   .setAction(() -> {
																						   QuestNutri.changeLanguage(language.getValue());
																					   }), true);
		
		FormSection sysConfig = new FormSection(this).setUpName(new LanguageUtil("Configurações do Sistema", "System Settings").get())
													 .addRow(language)
													 .hideRequiredLbl()
													 .init();
		
		holder.add(sysConfig, holder.gbc.yP());
		
		if(QuestNutri.isAdminControl()) {
			holder.add(userList, holder.gbc.yP().insets(25).fill("BOTH").wgt(1.0));
			updateUsersList();
			this.refresh();
		}
		
		FormBoxInput logOutBtn = new FormBoxInput(this).setButtonBox(StdButton.stdBtnConfig(new LanguageUtil("Sair", "Logout").get())
																			   .setAction(() -> {
																				   if(ConfirmDialog.ask(new LanguageUtil("Você tem certeza que deseja sair?", "Are you sure you want to logout?").get(),
																						   				new LanguageUtil("Você está prestes a sair", "You are about to leave").get())) {
																					   QuestNutri.logOut();
																				   }
																			   }),false);
		
		FormSection logOutSection = new FormSection(this).addRow(logOutBtn)
														 .hideRequiredLbl()
														 .hideBorder()
														 .setAllFieldsLateralDistance(0, 0)
														 .setLateralDistance(0)
														 .setUpperDistance(0)
														 .setLowerDistance(0)
														 .setInternalColor(STD_WHITE_COLOR);
											logOutSection.setBackground(STD_WHITE_COLOR);

		holder.add(logOutSection.init(), holder.gbc.yP().insets(10, 25).anchor("NORTHWEST"));
		
		scrollPane.setViewportView(holder);
		this.add(scrollPane, gbc.fill("BOTH").wgt(1.0));
	}
	
	private void updateUsersList() {	    
	    userList.setViewportView(new UserList(this::updateUsersList).getViewport().getView());
	    userList.revalidate();
	    userList.repaint();
	}
}
