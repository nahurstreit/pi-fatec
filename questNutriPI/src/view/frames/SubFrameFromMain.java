package view.frames;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.QuestNutri;

public class SubFrameFromMain extends SubFrame {
	private static final long serialVersionUID = 1L;

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