/**
 * Package que contém os frames (janelas) do sistema.
 */
package view.frames;

import model.entities.Aliment;
import utils.interfaces.IDoAction;
import view.QuestNutri;
import view.pages.aliment.AlimentFormPage;

/**
 * Frame específico para exibição e edição de detalhes de um Alimento.
 * Extende SubFrameFromMain para manter a consistência visual e funcional do aplicativo.
 */
public class AlimentFrame extends SubFrameFromMain {
	private static final long serialVersionUID = 1L;
	
    /**
     * Construtor para AlimentFrame.
     * 
     * @param aliment  objeto Aliment a ser exibido/editado
     * @param onUpdate ação a ser executada quando ocorrer uma atualização no Aliment
     */
	public AlimentFrame(Aliment aliment, IDoAction onUpdate) {
		super();
		this.setFrameName("Aliment Frame");
		AlimentFormPage page = new AlimentFormPage(null, aliment, onUpdate);
		page.setCallerFrame(this);
		
		int x = QuestNutri.app.getX() + QuestNutri.app.getWidth()/10;
		int y = QuestNutri.app.getY() + QuestNutri.app.getHeight()/10;
		
		int w = QuestNutri.app.getWidth() - (2*x);
		int h = QuestNutri.app.getHeight() - (2*y);
		
		this.setBounds(x, y, w, h);
		
		
		
		setContentPane(page);
		setVisible(true);
	}
}