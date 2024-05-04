package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.batik.swing.JSVGCanvas;

import view.panels.LoggedPanel;
import view.panels.LoginPanel;
import view.utils.VMakePicture;

/**
 * Classe principal da Aplicação.
 */
public class QuestNutri {
	/**
	 * Instância de JFrame que representa o objeto principal da aplicação.
	 */
	
	/**
	 * Instância estática centralizadora do aplicativo aberto. É feito desse modo
	 * para que seja possível acessar em qualquer lugar do código sem muitas
	 * referências explícitas.
	 */
	public static JFrame app = new JFrame();
	
	/**
	 * Cria uma instância estática para o painel de login do usuário. É feito desse modo
	 * para que seja possível acessar em qualquer lugar do código sem muitas
	 * referências explícitas.
	 */
	public static LoginPanel loginPanel = new LoginPanel();
	
	/**
	 * Instância estática para o painel de estado LOGADO do usuário.
	 * <br>É feito desse modo para que seja possível acessar em qualquer lugar do 
	 * código sem muitas referências explícitas.
	 */
	public static LoggedPanel loggedPanel;
	
	/**
	 * Logo SVG do applicativo a ser usado na side bar do painel logado.
	 */
	public static final String QUESTNUTRI_SVG_NAME = "QuestNutri";
	
	/**
	 * Inicializa a aplicação.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Carregando arquivos de interesse do aplicativo
					loadSVG();
					
					//Definindo estado da janela do app
					app.setTitle("QuestNutri (Desktop App)");
					app.setIconImage(VMakePicture.sizedImg("QuestNutriAlphaChannel", 8680, 4540).getImage());
//					app.setIconImage(VMakePicture.sizedImg("QuestNutri", 4500, 4500).getImage());
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
		app.repaint();
	}
	
	
	/**
	 * Método a ser melhor implementado posteriormente.
	 * <br>Tem como objetivo, atualmente, de trocar o painel principal do app
	 * para uma instância de painel logado.
	 */
	public static void doLogin() {
		loggedPanel = new LoggedPanel("{nutri}");
		swapAppPanel(loggedPanel);
	}
	
	
	/**
	 * Método que carrega os assets importantes ao sistema.
	 */
	public static JSVGCanvas loadSVG() {
		return VMakePicture.svgIcon(QUESTNUTRI_SVG_NAME);
	}
	
	/**
	 * Método que troca o painel principal do app para a instãncia do painel de Login do usuário.
	 * @param doDelay -> Valor booleano, opcional, que define se deverá ser exibido a animação da Logotipo antes
	 * de transferir para o painel de Login.
	 * <li><b>true</b> - Exibirá a animação</li>
	 * <li><b>false</b> - Não exibirá a animação</li>
	 */
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
	
	/**
	 * Método que exibe no painel principal do app a  Logo Reveal do aplicativo em formato .gif
	 */
	public static void showLogoReveal() {
		JLabel logoRevealGif = new JLabel(VMakePicture.gifIcon("LogoRevealQuestNutri"), JLabel.CENTER);
		
		
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