package view.frames;

import model.entities.Aliment;
import view.pages.aliment.AlimentFormPage;

public class AlimentFrame extends SubFrame {
	private static final long serialVersionUID = 1L;
	
	public AlimentFrame(Aliment aliment) {
		this.setName("Aliment Frame");
		AlimentFormPage page = new AlimentFormPage(null, aliment);
		setContentPane(page);
		setVisible(true);
	}
}