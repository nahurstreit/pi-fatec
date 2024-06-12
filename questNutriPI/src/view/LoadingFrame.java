package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import utils.interfaces.GeneralVisualSettings;
import utils.view.ImagesUtil;
import view.components.generics.GenericJPanel;

public class LoadingFrame extends JFrame implements GeneralVisualSettings {
	private static final long serialVersionUID = 1L;
	
	private JProgressBar progressBar;
    private JLabel lblAction = new JLabel("", JLabel.CENTER);

    public LoadingFrame() {
        setIconImage(ImagesUtil.sizedImg("QuestNutriAlphaChannel", 8680, 4540).getImage());
        setTitle("QuestNutri (Desktop App) - Loading...");
        setSize(300, 100);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
       
        
        // Layout do painel principal
        GenericJPanel panel = new GenericJPanel().ltGridBag();
        JLabel lblQuestNutri = new JLabel("QuestNutri (Desktop App)", JLabel.CENTER);
        lblQuestNutri.setFont(STD_BOLD_FONT.deriveFont(15f));
        
        panel.add(lblQuestNutri, panel.gbc.grid(0).fill("BOTH"));
        panel.add(progressBar, panel.gbc.yP());
        
        //Mudando o Layout do lblAction
        lblAction.setFont(STD_REGULAR_FONT.deriveFont(10f));
        panel.add(lblAction, panel.gbc.yP());

        add(panel);
    }

    public void setProgress(int progress) {
        progressBar.setValue(progress);
    }
    
    public void setAction(String action) {
    	lblAction.setText(action);
    }
}