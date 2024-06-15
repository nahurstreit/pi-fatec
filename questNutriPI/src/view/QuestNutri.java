package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.apache.batik.swing.JSVGCanvas;

import controller.app.AuthController;
import model.entities.User;
import model.utils.HibernateUtil;
import utils.interfaces.GeneralAppSettings;
import utils.interfaces.GeneralVisualSettings;
import utils.view.ImagesUtil;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.generics.GenericJFrame;
import view.states.LoggedPanel;
import view.states.LoginPanel;

/**
 * Classe principal da Aplicação.
 */
public class QuestNutri implements GeneralVisualSettings, GeneralAppSettings{	
	private static int language = STD_LANGUAGE;
	
    public static GenericJFrame app;
    public static LoginPanel loginPanel;
    public static LoggedPanel loggedPanel;
    public static final String QUESTNUTRI_SVG_NAME = "QuestNutri";
    
    private static User loggedUser;
    


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Exibe o JFrame de carregamento
                    LoadingFrame loadingFrame = new LoadingFrame();
                    loadingFrame.setVisible(true);

                    // Inicia o SwingWorker para carregar a aplicação
                    new LoadAppWorker(loadingFrame).execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Método que faz a troca do mainPanel do app.
     * @param panel -> Novo painel a ser exibido.
     */
    private static void swapAppPanel(JPanel panel) {
        app.setContentPane(panel);
        app.revalidate();
        app.repaint();
    }

    /**
     * TROCAR DEPOIS: AuthController
     */
    @SuppressWarnings("unused")
	public static void doLogin(String userName, String password) {
    	try {
    		System.err.println("SKIP LOGIN: "+SKIP_LOGIN);
    		if(!SKIP_LOGIN) System.err.println("SKIPPED DENIED, TRYING TO DO MANUAL LOGIN");
			if(SKIP_LOGIN || AuthController.doLogin(userName, password)) {
				if(SKIP_LOGIN) {
					System.err.println("LOGING SKIPPED SUCESSFULLY");
					setConnectedUser(new User("master", "", "{nutri}", 3));
				}
				else {
					System.err.println("USER LOGIN HAVE BEEN DONE SUCESSFULLY"
							+ "\nUser: "+userName+" - Logged at: "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
				}
						
				loggedPanel = new LoggedPanel(loggedUser.getFirstName());
		        swapAppPanel(loggedPanel);
			} else {
				System.err.println("LOGIN HAVE BEEN DENIED, CHECK YOUR CREDENTIALS AND TRY AGAIN");
			}
		} catch (Exception e) {
			QuestNutriJOP.showMessageDialog(null, 
					new LanguageUtil(
							"Erro interno do sistema. Consulte o log para mais informações", 
							"System Internal error. Check log for more details"
							).get(),
					new LanguageUtil(
							"Não foi possível fazer login", 
							"Login Failed"
							).get(),
					1,
					null);
		}
    	       
    }

    /**
     * Carrega o SVG da logotipo.
     * @return JSVGCanvas com a logotipo vetorizada
     */
    public static JSVGCanvas loadSVG() {
        return ImagesUtil.svgIcon(QUESTNUTRI_SVG);
    }

    /**
     * Método que inicializa a tela de login
     * @param doDelay -> valor booleano que define se será feito um delay para a exibição da logotipo
     * <li> Se <b>true</b> exibirá a tela de login
     * <li> Se <b>false</b> pulará a animação. -> Valor padrão.
     */
    public static void swapToLogin(boolean... doDelay) {
        boolean delay = false;
        if (doDelay.length > 0) delay = doDelay[0];

        Runnable swap = new Runnable() {
            public void run() {
            	loginPanel = new LoginPanel();
                QuestNutri.swapAppPanel(loginPanel);
                loginPanel.placeLogo();
            }
        };

        if (delay) {
            showLogoReveal();
            new java.util.Timer().schedule(new java.util.TimerTask() {
                public void run() {
                    swap.run();
                }
            }, 4000);
        } else {
            swap.run();
        }
    }

    /**
     * Método que exibe a animação da logotipo no start da aplicação.
     */
    public static void showLogoReveal() {
        JLabel logoRevealGif = new JLabel(ImagesUtil.gifIcon(LOGO_REVEAL_GIF), JLabel.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        JPanel centeredWhitePanel = new JPanel();
        centeredWhitePanel.setLayout(new GridBagLayout());
        centeredWhitePanel.setBackground(Color.white);
        centeredWhitePanel.add(logoRevealGif, gbc);

        app.add(centeredWhitePanel, BorderLayout.CENTER);
    }

    /**
     * Método que escurece a tela da aplicação.
     */
    public static void followYouIntoTheDark() {
        JPanel darkScene = new JPanel();
        darkScene.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        darkScene.add(loggedPanel, gbc);

        JLabel darkness = new JLabel();
        darkness.setBackground(new Color(0, 0, 0, 200));
        darkness.setOpaque(true);
        darkness.setLayout(new GridBagLayout());
        darkScene.add(darkness, gbc);

        darkScene.setComponentZOrder(darkness, 0);

        app.setEnabled(false);
        swapAppPanel(darkScene);
    }

    public static void heraldOfDarkness() {
        app.setEnabled(true);
        swapAppPanel(loggedPanel);
        app.toFront();
    }

    private static class LoadAppWorker extends SwingWorker<Void, Void> {
        private LoadingFrame loadingFrame;

        public LoadAppWorker(LoadingFrame loadingFrame) {
            this.loadingFrame = loadingFrame;
        }

        @Override
        protected Void doInBackground() throws Exception {
            loadingFrame.setProgress(10);
            loadingFrame.setAction(new LanguageUtil("Inicializando...", "Starting...").get());
            Thread.sleep(500); // Simula o tempo de carregamento

            loadingFrame.setProgress(30);
            loadSVG(); // Carrega os assets importantes
            loadingFrame.setAction(new LanguageUtil("Carregando Vetores...", "Loading Vectors...").get());
            Thread.sleep(500); // Simula o tempo de carregamento

            loadingFrame.setProgress(60);
            loadingFrame.setAction(new LanguageUtil("Conectando ao banco de dados...", "Connecting to database...").get());
            connectDB(); // Estabelece a conexão com o banco de dados
            Thread.sleep(500); // Simula o tempo de carregamento

            loadingFrame.setProgress(100);
            Thread.sleep(100);
            return null;
        }

        @Override
        protected void done() { //Define o comportamento depois de carregado
            try {
                get();
                
                loadingFrame.dispose(); // Fecha a janela de carregamento
                
                // Inicializa o JFrame principal
                app = new GenericJFrame(new Dimension(1000, 500));
                app.setDefaultCloseApp();
                app.setTitle("QuestNutri (Desktop App)");
                app.setExtendedState(JFrame.MAXIMIZED_BOTH);
                app.setVisible(true);

                // Inicia o painel de login
                swapToLogin(SHOWLOGO);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para inicializar a conexão com o banco de dados.
     */
    public static void connectDB() {
        HibernateUtil.getSessionFactory();
    }
    
    public static void setConnectedUser(User user) {
    	loggedUser = user;
    }
    
    public static boolean isEditAuth() {
    	if(SKIP_LOGIN) return true;
    	
    	boolean res = false;
    	if(loggedUser != null) {
    		res = Integer.parseInt(loggedUser.getSysLevel()) > 1;
    	}
    	
    	return res;
    }
    
    public static int language() {
    	return QuestNutri.language;
    }
    
    public static boolean changeLanguage(String language) {
    	try {
        	switch(language) {
        	case "EN-US":
        		QuestNutri.language = 1;
        		break;
        	default:
        		QuestNutri.language = 0;
        	}
		} catch (Exception e) {
			return false;
		}
    	
    	try {
    		loggedPanel = new LoggedPanel(loggedUser.getFirstName());
    		loggedPanel.settingsPage.performEvent();
		} catch (Exception e) {
			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Houve um erro ao definir a nova linguagem!", "An error occurred while setting the new language!").get());
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
    	
    	swapAppPanel(loggedPanel);
    	QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Nova linguagem do sistema foi definida!", "New system language set!").get());

    	return true;
    }
    
}
