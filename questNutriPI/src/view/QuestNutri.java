package view;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.panels.LoggedPanel;
import view.panels.LoginPanel;

public class QuestNutri {
	/**
	 * Instância de JFrame que representa o objeto principal da aplicação.
	 */
	public static JFrame app = new JFrame();
	
	public static LoggedPanel loggedPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app.setExtendedState(JFrame.MAXIMIZED_BOTH);
					app.setMinimumSize(new Dimension(1000, 500));
					app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					QuestNutri.swapAppPanel(new LoginPanel());
					app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Método estático que troca o panel exibido atualmente.
	 * @param panel -> Objeto do tipo JPanel a ser exibido na aplicação.
	 */
	private static void swapAppPanel(JPanel panel) {
		app.setContentPane(panel);
		app.revalidate();
	}
	
	public static void doLogin() {
		loggedPanel = new LoggedPanel("{nutri}");
		swapAppPanel(loggedPanel);
	}
	
	public static Font loadFont(String fontName) {
		//Prepara o retorno
		Font loadedFont;
        try {
        	//Encontra o arquivo da fonte
            InputStream fontStream = QuestNutri.class.getResourceAsStream("/view/assets/fonts/"+fontName+".ttf");
            
            //Carrega a fonte e atribui a loadedFont
            loadedFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            
            //Define a fonte Arial como padrão em caso de erro no load.
            loadedFont = new Font("Arial", Font.PLAIN, 18);
        }
        return loadedFont;
    }
}