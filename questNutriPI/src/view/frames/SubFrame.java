package view.frames;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import view.QuestNutri;

public class SubFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public SubFrame() {
		QuestNutri.followYouIntoTheDark();
		
		this.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        QuestNutri.heraldOfDarkness();
		    }
		});
	}
}