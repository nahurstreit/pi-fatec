package view.frames;

import view.QuestNutri;
import view.panels.components.GenericJPanel;
import view.panels.pages.subpages.NewCustomerForm;

public class NewCustomerFrame extends SubFrame {
	private static final long serialVersionUID = 1L;

	private GenericJPanel mainPanel;
	
	public NewCustomerFrame() {
		super();
		int x = QuestNutri.app.getX() + QuestNutri.app.getWidth()/5;
		int y = QuestNutri.app.getY() + QuestNutri.app.getHeight()/5;
		
		int w = QuestNutri.app.getWidth() - (2*x);
		int h = QuestNutri.app.getHeight() - (2*y);
		
		this.setBounds(x, y, w, h);
		
		mainPanel = new NewCustomerForm(mainPanel, this);
		this.setContentPane(mainPanel);
		setTitle("Cadastro de Novo Cliente");
		
		this.setVisible(true);
	}
	
	
}