package view.frames;

import utils.view.LanguageUtil;
import view.QuestNutri;
import view.components.generics.GenericJPanel;
import view.pages.customer.profile.NewCustomerForm;

public class NewCustomerFrame extends SubFrame {
	private static final long serialVersionUID = 1L;

	private GenericJPanel mainPanel;
	
	public NewCustomerFrame(Runnable onCreate) {
		super();
		int x = QuestNutri.app.getX() + QuestNutri.app.getWidth()/5;
		int y = QuestNutri.app.getY() + QuestNutri.app.getHeight()/5;
		
		int w = QuestNutri.app.getWidth() - (2*x);
		int h = QuestNutri.app.getHeight() - (2*y);
		
		this.setBounds(x, y, w, h);
		
		mainPanel = new NewCustomerForm(mainPanel, this, onCreate);
		this.setContentPane(mainPanel);
		setTitle(new LanguageUtil("Cadastro de Novo Cliente", "New Customer Register").get());
		
		this.setVisible(true);
	}
	
	
}