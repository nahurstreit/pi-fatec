package view.frames;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.QuestNutri;

/**
 * Subclasse de SubFrame que representa um subframe principal da aplicação, associado ao frame principal da aplicação QuestNutri.
 */
public class SubFrameFromMain extends SubFrame {
	private static final long serialVersionUID = 1L;

    /**
     * Construtor padrão que inicializa o SubFrame associado ao frame principal QuestNutri.app.
     */
	public SubFrameFromMain() {
		super(QuestNutri.app, null);
		
		try {
			QuestNutri.followYouIntoTheDark();
			this.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosing(WindowEvent e) {
			        QuestNutri.heraldOfDarkness();
			    }
			});
		} catch (Exception e) {}
	}

}