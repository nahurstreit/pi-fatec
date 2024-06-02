package view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import view.panels.components.GeneralJPanelSettings;

public class QuestNutriJOP extends JOptionPane {
	private static final long serialVersionUID = 1L;
	
	private static final Font STD_BOLD_FONT = GeneralJPanelSettings.STD_BOLD_FONT;
    private static final Font STD_REGULAR_FONT = GeneralJPanelSettings.STD_REGULAR_FONT;
    private static final Color STD_BLUE_COLOR = GeneralJPanelSettings.STD_BLUE_COLOR;
    
    public static int showConfirmDialog(Component parentComponent, Object message) {
        applyQuestNutriSettings();
        return JOptionPane.showConfirmDialog(parentComponent, message);
    }

    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType) {
        applyQuestNutriSettings();
        return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType);
    }

    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType) {
        applyQuestNutriSettings();
        return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType);
    }

    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon) {
        applyQuestNutriSettings();
        return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType, icon);
    }

    
    public static void showMessageDialog(Component parentComponent, Object message) {
        applyQuestNutriSettings();
        JOptionPane.showMessageDialog(parentComponent, message);
    }

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        applyQuestNutriSettings();
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
    }

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType, Icon icon) {
        applyQuestNutriSettings();
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType, icon);
    }

    
    public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) {
        applyQuestNutriSettings();
        return JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue);
    }
    
    public static String showInputDialog(Component parentComponent, Object message) {
        applyQuestNutriSettings();
        return JOptionPane.showInputDialog(parentComponent, message);
    }

    public static String showInputDialog(Component parentComponent, Object message, Object initialSelectionValue) {
        applyQuestNutriSettings();
        return JOptionPane.showInputDialog(parentComponent, message, initialSelectionValue);
    }

    public static String showInputDialog(Component parentComponent, Object message, String title, int messageType) {
        applyQuestNutriSettings();
        return JOptionPane.showInputDialog(parentComponent, message, title, messageType);
    }
    
    public static Object showInputDialog(Component parentComponent, Object message, String title, int messageType, Icon icon, Object[] selectionValues, Object initialSelectionValue) {
        applyQuestNutriSettings();
        return JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue);
    }
    
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
    
    
    public static void main(String[] args) {
    	QuestNutriJOP.showMessageDialog(null, "Ok");
	}
}
