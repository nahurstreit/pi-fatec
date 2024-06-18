/**
 * Package que contém as classes da camada view.
 */
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
import view.frames.LoadingFrame;
import view.states.LoggedPanel;
import view.states.LoginPanel;

/**
 * Classe principal da Aplicação e ponto de início.
 */
public class QuestNutri implements GeneralVisualSettings, GeneralAppSettings{	
	/**
	 * Linguagem atual do sistema.
	 */
	private static int language = STD_LANGUAGE;
	
	/**
	 * Frame principal do sistema.
	 */
    public static GenericJFrame app;
    
    /**
     * Panel de estado login do sistema.
     */
    public static LoginPanel loginPanel;
    
    /**
     * Panel de estado logado do sistema.
     */
    public static LoggedPanel loggedPanel;
    
    /**
     * Nome do SVG para ser carregado pelo sistema.
     */
    public static final String QUESTNUTRI_SVG_NAME = "QuestNutri";
    
    /**
     * Usuário atualmente logado no sistema.
     */
    private static User loggedUser;

    
    /**
     * Método de início da aplicação.
     * @param args
     */
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
     * Método para realizar o login na aplicação.
     * @param username -> username do usuário.
     * @param password -> senha do usuário.m
     */
	public static void doLogin(String username, String password) {
    	try {
    		System.err.println("SKIP LOGIN: "+SKIP_LOGIN);
    		if(!SKIP_LOGIN) System.err.println("SKIPPED DENIED, TRYING TO DO MANUAL LOGIN");
			if(SKIP_LOGIN || AuthController.doLogin(username, password)) {
				if(SKIP_LOGIN) {
					System.err.println("LOGING SKIPPED SUCESSFULLY");
					setConnectedUser(new User("master", "", "{nutri}", 3));
				}
				else {
					System.err.println("USER LOGIN HAVE BEEN DONE SUCESSFULLY"
							+ "\nUser: "+username+" - Logged at: "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
				}
				
				loggedPanel = new LoggedPanel(loggedUser.getFirstName());
		        swapAppPanel(loggedPanel);
			} else {
				System.err.println("LOGIN HAVE BEEN DENIED, CHECK YOUR CREDENTIALS AND TRY AGAIN");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
     * <ul>
     * <li> Se <b>true</b> exibirá a tela de login
     * <li> Se <b>false</b> pulará a animação. -> Valor padrão.
     * </ul>
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

    /**
     * Método que esclarece a tela da aplicação.
     */
    public static void heraldOfDarkness() {
        app.setEnabled(true);
        swapAppPanel(loggedPanel);
        app.toFront();
    }

    /**
     * Classe que inicializa o carregamento da aplicação enquanto exibe um frame de carregamento.
     */
    private static class LoadAppWorker extends SwingWorker<Void, Void> {
    	/**
    	 * Frame de carregamento.
    	 */
        private LoadingFrame loadingFrame;

        /**
         * Constructor do frame de carregamento.
         * @param loadingFrame
         */
        public LoadAppWorker(LoadingFrame loadingFrame) {
            this.loadingFrame = loadingFrame;
        }

        /**
         * Método que carrega os assets do sistema, ao mesmo tempo que aumenta a barra do loadingFrame
         */
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

        /**
         * Método que inicializa a aplicação quando tudo tiver sido carregado.
         */
        @Override
        protected void done() { //Define o comportamento depois de carregado
            try {
                get();
                
                loadingFrame.dispose(); // Fecha a janela de carregamento
                
                // Inicializa o JFrame principal
                app = new GenericJFrame(new Dimension(1150, 600));
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
    public static void connectDB() throws Exception {
    	try {
    		HibernateUtil.getSessionFactory();
		} catch (Exception e) {
	        throw new Exception("Não foi possível conectar ao banco de dados, veja se o banco existe.", e);
		}
        
    }
    
    /**
     * Define o usuário que está conectado atualmente ao sistema.
     * @param user
     */
    public static void setConnectedUser(User user) {
    	loggedUser = user;
    	language = user.userPrefLanguage();
    }
    
    /**
     * Indica se o usuário logado no sistema tem nível de autorização > 1, que permite a edição de partes do sistema.
     * @return - retorna se o usuário tem permissão de edição
     * <br><b>true</b> - o usuário tem permissão
     * <br><b>false</b> - o usuário não tem permissão
     */
    public static boolean isEditAuth() {
    	if(SKIP_LOGIN) return true;
    	
    	boolean res = false;
    	if(loggedUser != null) {
    		res = Integer.parseInt(loggedUser.getSysLevel()) > 1;
    	}
    	
    	return res;
    }
    
    /**
     * Retorna qual é a linguagem atual do sistema.
     * @return integer indicando qual é a linguagem atual do sistema
     * <br>0 -> português;
     * <br>1 -> inglês;
     */
    public static int language() {
    	return QuestNutri.language;
    }
    
    /**
     * Método que faz a troca das linguagens do sistema e salva essa linguagem como a padrão para o usuário logado.
     * @param language -> Nova linguaguem.
     * @return - o status da troca de linguagem.
     * <br><b>true</b> - a troca foi realizada
     * <br><b>false</b> - a troca não pôde ser realizada.
     */
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
    		try {
    			loggedUser.setPreferredLanguage(QuestNutri.language);
    			if(!SKIP_LOGIN) {
    				loggedUser.save();
    			}
			} catch (Exception e1) {
				throw new Exception(e1);
			}
    		
    		loggedPanel = new LoggedPanel(loggedUser.getFirstName());
    		loggedPanel.settingsPage.performEvent();
		} catch (Exception e) {
			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Houve um erro ao definir a nova linguagem!", "An error occurred while setting the new language!").get());
			e.printStackTrace();
			return false;
		}
    	
    	swapAppPanel(loggedPanel);
    	QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Nova linguagem do sistema foi definida!", "New system language set!").get());

    	return true;
    }
    
    /**
     * Retorna o número de id no banco de dados do usuário logado atualmente.
     * @return integer -> que representa o id do usuário no banco de dados.
     */
    public static int getLoggedUserId() {

    	if(loggedUser != null) {
    		try {
    			return loggedUser.getId();
			} catch (Exception e) {
				return 0;
			}
    		
    	}
    	else return 0;
    }
    
    /**
     * Método que atualiza as informações do usuário logado atualmente.
     * @param username -> novo nome de usuário
     * @param password -> nova senha.
     * @return - o status da atualização.
     * <br><b>true</b> - a atualização foi realizada
     * <br><b>false</b> - a atualização não pôde ser realizada.
     */
    public static boolean updateLoggedUser(String username, String password) {
    	try {
    		loggedUser.setLogin(username).setPassword(password);
    		return loggedUser.save();
		} catch (Exception e) {
			return false;
		}
    }
    
    /**
     * Método que desloga o usuário atual do sistema.
     */
    public static void logOut() {
    	loginPanel = new LoginPanel();
    	swapAppPanel(loginPanel);
    	loginPanel.placeLogo();
    	loggedPanel = null;
    	loggedUser = null;
    	QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Você foi desconectado.", "You have been disconnected.").get());
    }
    
    /**
     * Indica se o usuário logado no sistema tem o maior nível de autorização (systemLevel == 3), que permite a edição de todas as partes do sistema e o controle completo de usuários.
     * @return - retorna se o usuário tem permissão de admin.
     * <br><b>true</b> - o usuário tem permissão
     * <br><b>false</b> - o usuário não tem permissão
     */
    public static boolean isAdminControl() {
    	return loggedUser.getSystemLevel() == 3;
    }
    
}
