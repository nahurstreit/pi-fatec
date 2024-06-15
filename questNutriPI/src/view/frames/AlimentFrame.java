package view.frames;

import model.entities.Aliment;
import view.pages.aliment.AlimentFormPage;

public class AlimentFrame extends SubFrameFromMain {
	private static final long serialVersionUID = 1L;
	
	public AlimentFrame(Aliment aliment) {
		super();
		this.setFrameName("Aliment Frame");
		AlimentFormPage page = new AlimentFormPage(null, aliment);
		page.setCallerFrame(this);
		
		setContentPane(page);
		setVisible(true);
	}
}