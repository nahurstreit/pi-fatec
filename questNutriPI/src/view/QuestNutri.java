package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.batik.swing.JSVGCanvas;

import view.components.Make;
import view.panels.LoggedPanel;
import view.panels.LoginPanel;

public class QuestNutri {
	/**
	 * Instância de JFrame que representa o objeto principal da aplicação.
	 */
	public static JFrame app = new JFrame();
	public static LoginPanel loginPanel = new LoginPanel();
	public static LoggedPanel loggedPanel;
	
	public static JSVGCanvas questNutriSVG;
	public static ImageIcon loginBG = null;
	
	public static Color paletteBlue1 = new Color(85, 183, 254);
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Carregando arquivos
					loadAssets();
					paletteBlue1 = new Color(85, 183, 254);
					
					//Definindo estado da janela.
					app.setExtendedState(JFrame.MAXIMIZED_BOTH);
					app.setMinimumSize(new Dimension(1000, 500));
					app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					app.setVisible(true);
					
					//Incializando
					showLogoReveal();
					swapToLogin(true);
					
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
	
//	public static Font loadFont(String fontName) {
//		//Prepara o retorno
//		Font loadedFont;
//        try {
//        	//Encontra o arquivo da fonte
//            InputStream fontStream = QuestNutri.class.getResourceAsStream("/view/assets/fonts/"+fontName+".ttf");
//            
//            //Carrega a fonte e atribui a loadedFont
//            loadedFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
//        } catch (FontFormatException | IOException e) {
//            e.printStackTrace();
//            
//            //Define a fonte Arial como padrão em caso de erro no load.
//            loadedFont = new Font("Arial", Font.PLAIN, 18);
//        }
//        return loadedFont;
//    }
	
	public static void loadQuestNutriSVG() {
		questNutriSVG = Make.svgIcon("QuestNutri2", false);
	}
	
	private static void loadAssets() {
		questNutriSVG = Make.svgIcon("QuestNutri2", false);
		loginBG = Make.sizedImg("LoginPageBG", app.getWidth(), app.getHeight());
	}
	
	public static void swapToLogin(boolean... doDelay) {
//		LoginPanel loginPanel = new LoginPanel();
		boolean delay = false;
		if(doDelay.length > 0) delay = doDelay[0];
		
		Runnable swap = new Runnable() {
			public void run() {
				QuestNutri.swapAppPanel(loginPanel);
			}
		};
		
		if(delay) {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					swap.run();
				}
			}, 4000);;
		} else {
			swap.run();
		}
	}
	
	public static void showLogoReveal() {		
		JLabel logoRevealGif = new JLabel(Make.gifIcon("LogoRevealQuestNutri"), JLabel.CENTER);
		
		
		//Criação de um JPanel intermediário para definir a cor do fundo como branco.
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		JPanel centeredWhitePanel = new JPanel();
		centeredWhitePanel.setLayout(new GridBagLayout());
		centeredWhitePanel.setBackground(Color.white);
		centeredWhitePanel.add(logoRevealGif, gbc);
		
		app.add(centeredWhitePanel, BorderLayout.CENTER);
	}
}