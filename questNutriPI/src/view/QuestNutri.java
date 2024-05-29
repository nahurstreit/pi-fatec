package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.apache.batik.swing.JSVGCanvas;

import controller.AuthController;
import model.utils.HibernateUtil;
import view.panels.LoggedPanel;
import view.panels.LoginPanel;
import view.utils.VMakePicture;

/**
 * Classe principal da Aplicação.
 */
public class QuestNutri {
    public static JFrame app = new JFrame();
    public static LoginPanel loginPanel = new LoginPanel();
    public static LoggedPanel loggedPanel;
    public static final String QUESTNUTRI_SVG_NAME = "QuestNutri";
    
    //CONFIG
    private static final boolean SHOWLOGO = false;
    private static final boolean  SKIP_LOGIN = true;

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
			if(AuthController.doLogin(userName, password) || SKIP_LOGIN) {
				loggedPanel = new LoggedPanel("{nutri}");
		        swapAppPanel(loggedPanel); 
			} else {
				JOptionPane.showMessageDialog(null, "Usuário incorreto!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	       
    }

    /**
     * Carrega o SVG da logotipo.
     * @return JSVGCanvas com a logotipo vetorizada
     */
    public static JSVGCanvas loadSVG() {
        return VMakePicture.svgIcon(QUESTNUTRI_SVG_NAME);
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
        JLabel logoRevealGif = new JLabel(VMakePicture.gifIcon("LogoRevealQuestNutri"), JLabel.CENTER);

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
            loadingFrame.setAction("Inicializando...");
            Thread.sleep(500); // Simula o tempo de carregamento

            loadingFrame.setProgress(30);
            loadSVG(); // Carrega os assets importantes
            loadingFrame.setAction("Carregando Vetores...");
            Thread.sleep(500); // Simula o tempo de carregamento

            loadingFrame.setProgress(60);
            loadingFrame.setAction("Conectando ao banco de dados...");
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
                app.setTitle("QuestNutri (Desktop App)");
                app.setIconImage(VMakePicture.sizedImg("QuestNutriAlphaChannel", 8680, 4540).getImage());
                app.setExtendedState(JFrame.MAXIMIZED_BOTH);
                app.setMinimumSize(new Dimension(1000, 500));
                app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}
