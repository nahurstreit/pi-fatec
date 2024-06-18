/**
 * Package que contém as classes de componentes da view.
 */
package view.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import utils.interfaces.GeneralVisualSettings;
import utils.view.LanguageUtil;

/**
 * Extensão da classe JOptionPane para aplicar configurações visuais específicas do sistema QuestNutri.
 * Implementa a interface GeneralVisualSettings para definir configurações visuais gerais.
 */
public class QuestNutriJOP extends JOptionPane implements GeneralVisualSettings {
	private static final long serialVersionUID = 1L;
    
	/**
	 * Exibe um diálogo de confirmação com as configurações visuais personalizadas da QuestNutri.
	 * 
	 * @param parentComponent componente pai do diálogo
	 * @param message mensagem exibida no diálogo
	 * @return um inteiro representando a opção selecionada pelo usuário
	 */
    public static int showConfirmDialog(Component parentComponent, Object message) {
        applyQuestNutriSettings();
        return JOptionPane.showConfirmDialog(parentComponent, message);
    }


    /**
     * Exibe um diálogo de confirmação com título e tipo de opção especificados,
     * utilizando as configurações visuais personalizadas da QuestNutri.
     * 
     * @param parentComponent componente pai do diálogo
     * @param message mensagem exibida no diálogo
     * @param title título do diálogo
     * @param optionType tipo de opção (por exemplo, YES_NO_OPTION)
     * @return um inteiro representando a opção selecionada pelo usuário
     */
    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType) {
        applyQuestNutriSettings();
        return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType);
    }

    /**
     * Exibe um diálogo de confirmação com título, tipo de opção e tipo de mensagem especificados,
     * utilizando as configurações visuais personalizadas da QuestNutri.
     * 
     * @param parentComponent componente pai do diálogo
     * @param message mensagem exibida no diálogo
     * @param title título do diálogo
     * @param optionType tipo de opção (por exemplo, YES_NO_CANCEL_OPTION)
     * @param messageType tipo de mensagem (por exemplo, INFORMATION_MESSAGE)
     * @return um inteiro representando a opção selecionada pelo usuário
     */
    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType) {
        applyQuestNutriSettings();
        return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType);
    }

    /**
     * Exibe um diálogo de confirmação com título, tipo de opção, tipo de mensagem, e ícone especificados,
     * utilizando as configurações visuais personalizadas da QuestNutri.
     * 
     * @param parentComponent componente pai do diálogo
     * @param message mensagem exibida no diálogo
     * @param title título do diálogo
     * @param optionType tipo de opção (por exemplo, YES_NO_OPTION)
     * @param messageType tipo de mensagem (por exemplo, WARNING_MESSAGE)
     * @param icon ícone exibido no diálogo
     * @return um inteiro representando a opção selecionada pelo usuário
     */
    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon) {
        applyQuestNutriSettings();
        return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType, icon);
    }

    /**
     * Exibe um diálogo de mensagem com as configurações visuais personalizadas da QuestNutri.
     * 
     * @param parentComponent componente pai do diálogo
     * @param message mensagem exibida no diálogo
     */
    public static void showMessageDialog(Component parentComponent, Object message) {
        showMessageDialog(parentComponent, message, new LanguageUtil("Mensagem", "Message").get(), 1);
    }

    /**
     * Exibe um diálogo de mensagem com título, tipo de mensagem e as configurações visuais personalizadas da QuestNutri.
     * 
     * @param parentComponent componente pai do diálogo
     * @param message mensagem exibida no diálogo
     * @param title título do diálogo
     * @param messageType tipo de mensagem (por exemplo, ERROR_MESSAGE)
     */
    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        applyQuestNutriSettings();
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
    }

    /**
     * Exibe um diálogo de mensagem com título, tipo de mensagem, ícone e as configurações visuais personalizadas da QuestNutri.
     * 
     * @param parentComponent componente pai do diálogo
     * @param message mensagem exibida no diálogo
     * @param title título do diálogo
     * @param messageType tipo de mensagem (por exemplo, QUESTION_MESSAGE)
     * @param icon ícone exibido no diálogo
     */
    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType, Icon icon) {
        applyQuestNutriSettings();
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType, icon);
    }
    
    /**
     * Exibe um diálogo com opções para o usuário selecionar, utilizando as configurações visuais personalizadas da QuestNutri.
     * 
     * @param parentComponent componente pai do diálogo
     * @param message mensagem exibida no diálogo
     * @param title título do diálogo
     * @param optionType tipo de opção (por exemplo, YES_NO_OPTION)
     * @param messageType tipo de mensagem (por exemplo, INFORMATION_MESSAGE)
     * @param icon ícone exibido no diálogo
     * @param options opções disponíveis para o usuário selecionar
     * @param initialValue valor inicialmente selecionado
     * @return um inteiro representando a opção selecionada pelo usuário
     */
    public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) {
        applyQuestNutriSettings();
        return JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue);
    }
    
    /**
     * Exibe um diálogo de entrada de texto, utilizando as configurações visuais personalizadas da QuestNutri.
     * 
     * @param parentComponent componente pai do diálogo
     * @param message mensagem exibida no diálogo
     * @return uma string representando o texto inserido pelo usuário, ou null se o usuário cancelar
     */
    public static String showInputDialog(Component parentComponent, Object message) {
        applyQuestNutriSettings();
        return JOptionPane.showInputDialog(parentComponent, message);
    }
    
    /**
     * Exibe um diálogo de entrada de texto com valor inicial, utilizando as configurações visuais personalizadas da QuestNutri.
     * 
     * @param parentComponent componente pai do diálogo
     * @param message mensagem exibida no diálogo
     * @param initialSelectionValue valor inicial do campo de entrada
     * @return uma string representando o texto inserido pelo usuário, ou null se o usuário cancelar
     */
    public static String showInputDialog(Component parentComponent, Object message, Object initialSelectionValue) {
        applyQuestNutriSettings();
        return JOptionPane.showInputDialog(parentComponent, message, initialSelectionValue);
    }

    /**
     * Exibe um diálogo de entrada de texto com título e tipo de mensagem especificados,
     * utilizando as configurações visuais personalizadas da QuestNutri.
     * 
     * @param parentComponent componente pai do diálogo
     * @param message mensagem exibida no diálogo
     * @param title título do diálogo
     * @param messageType tipo de mensagem (por exemplo, WARNING_MESSAGE)
     * @return uma string representando o texto inserido pelo usuário, ou null se o usuário cancelar
     */
    public static String showInputDialog(Component parentComponent, Object message, String title, int messageType) {
        applyQuestNutriSettings();
        return JOptionPane.showInputDialog(parentComponent, message, title, messageType);
    }
    
    /**
     * Exibe um diálogo de entrada de texto com título, tipo de mensagem, ícone, opções de seleção e valor inicial especificados,
     * utilizando as configurações visuais personalizadas da QuestNutri.
     * 
     * @param parentComponent componente pai do diálogo
     * @param message mensagem exibida no diálogo
     * @param title título do diálogo
     * @param messageType tipo de mensagem (por exemplo, QUESTION_MESSAGE)
     * @param icon ícone exibido no diálogo
     * @param selectionValues valores de seleção disponíveis para o usuário
     * @param initialSelectionValue valor inicialmente selecionado
     * @return um objeto representando a opção selecionada pelo usuário, ou null se o usuário cancelar
     */
    public static Object showInputDialog(Component parentComponent, Object message, String title, int messageType, Icon icon, Object[] selectionValues, Object initialSelectionValue) {
        applyQuestNutriSettings();
        return JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue);
    }
    
    /**
     * Aplica as configurações visuais personalizadas da QuestNutri para os diálogos JOptionPane.
     */
    private static void applyQuestNutriSettings() {
        // Configurando a fonte do título
        UIManager.put("OptionPane.messageFont", STD_BOLD_FONT);
        // Configurando a fonte normal
        UIManager.put("OptionPane.messageFont", STD_REGULAR_FONT);
        // Configurando a fonte dos botões
        UIManager.put("OptionPane.buttonFont", STD_BOLD_FONT);
        // Configurando a cor da barra de cima
        UIManager.put("OptionPane.titleForeground", Color.white);
        // Configurando a cor das letras na barra de cima
        UIManager.put("OptionPane.title", STD_BLUE_COLOR);
        // Configurando a cor do fundo dos botões
        UIManager.put("Button.background", STD_BLUE_COLOR);
        // Configurando a cor do texto dos botões
        UIManager.put("Button.foreground", Color.white);

        // Criando um JPanel com fundo branco para o fundo da mensagem
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);

        // Configurando a cor de fundo da mensagem
        UIManager.put("Panel.background", Color.white);
     // Configurando a cor de fundo da mensagem
        UIManager.put("OptionPane.background", Color.white);

        // Definindo a cor do texto da mensagem
        UIManager.put("OptionPane.messageForeground", Color.black);
    }
}
