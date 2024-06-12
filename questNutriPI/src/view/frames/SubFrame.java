package view.frames;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import utils.view.ImagesUtil;
import view.QuestNutri;
import view.components.generics.GenericJFrame;

public abstract class SubFrame extends GenericJFrame {
	private static final long serialVersionUID = 1L;

	public SubFrame() {
		try {
			QuestNutri.followYouIntoTheDark();
			this.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosing(WindowEvent e) {
			        QuestNutri.heraldOfDarkness();
			    }
			});
		} catch (Exception e) {
            setIconImage(ImagesUtil.sizedImg("QuestNutriAlphaChannel", 8680, 4540).getImage());
            //setExtendedState(JFrame.MAXIMIZED_BOTH);
            setMinimumSize(new Dimension(1000, 600));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		

	}
}