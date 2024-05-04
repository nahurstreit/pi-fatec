package view.panels.components;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.*;

import view.QuestNutri;

public class SideBar extends GeneralJPanel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Define a última label clicada para ter underline.
	 */
	private static JLabel lastSelectedLabel = null;
	
	/**
	 * Cria o painel de menu lateral (SideBar)
	 */
	public SideBar(String nutriName, SideBarItem ...windows) {
		this.setBackground(this.STD_LIGHT_GRAY);
		this.ltGridBag();
		
		//Definindo o layout da saudação
			//Criando o Layout da saudação inicial
			this.gbcFill("BOTH");
			this.gbcInsets(20, 0);
		
			JLabel lblGreetings = new JLabel("Olá, "+nutriName+"!",JLabel.CENTER);
			lblGreetings.setFont(this.STD_BOLD_FONT.deriveFont(28f));
			this.add(lblGreetings, gbc);
			
		//Definindo o Layout das opções	
			JPanel options = new JPanel();
			options.setBackground(this.STD_LIGHT_GRAY);
			options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
			
			//Definindo o espaçamento do conteúdo dos itens
			Border padding = BorderFactory.createEmptyBorder(10, 25, 10, 10);
			options.setBorder(padding);
			
			//Cria os items como label usando o método createMenuItems e depois adiciona ao panel indicado, no caso options.
			createMenuItems(windows, options);
			
			//Criando o Layout da parte dos itens
		 	this.resetGbc();
		 	gbc.gridy = 1;
		 	gbc.gridheight = 2;
	        gbc.weightx = 1.0;
	        gbc.weighty = 1.0;
	        this.gbcFill("BOTH");
		   
	        //Adicionando o panel com os itens e o layout
			this.add(options, gbc);
			
			
			//Adicionando a logotipo em SVG.
			gbc.gridy = 3;
			gbc.weightx = 0.0;
	        gbc.weighty = 0.0;
	        this.gbcFill("NONE");
	        this.gbcInsets(40, 40, 15, 40);
			this.add(QuestNutri.loadSVG(), gbc);
		
	}
	
	/**
	 * Método que adiciona ao SideBar os itens que trocam o panel de exibição.
	 * @param windows -> Array de SideBarItem
	 */
	private void createMenuItems(SideBarItem[] windows, JPanel panelDestiny) {
		for(SideBarItem window: windows) {
			JLabel optionLbl = createItemLabel(window);

			 // Criar um Map de atributos de texto para configurar o espaçamento entre letras
	        Map<TextAttribute, Object> attributes = new HashMap<>();
	        attributes.put(TextAttribute.TRACKING, 0.2); // Valor positivo para aumentar o espaçamento
	        
	        // Aplicar os atributos de texto à fonte atual
	        Font modifiedFont = this.STD_LIGHT_FONT.deriveFont(18f).deriveFont(attributes);
	        optionLbl.setFont(modifiedFont);
	        panelDestiny.add(optionLbl);
			panelDestiny.add(Box.createVerticalStrut(15));
		}
	}
	
	/**
	 * Método que cria um JLabel e configura suas interações particulares da SideBar
	 * @param item -> objeto do tipo SideBarItem, que contém as especificações do item do menu
	 * @return -> JLabel com as especificações enviadas no item.
	 */
	private JLabel createItemLabel(SideBarItem item) {
		JLabel optionLbl = new JLabel(item.text);
		optionLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				item.performEvent();
				
				// Remove sublinhado da última JLabel selecionada
                if (lastSelectedLabel != null) {
                    removeUnderline(lastSelectedLabel);
                }

                // Definir a JLabel atual como selecionada
                addUnderline(optionLbl);
                lastSelectedLabel = optionLbl;
			}
			
			//Quando passar o mouse em cima, muda o cursor
			public void mouseEntered(MouseEvent e) {
				optionLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			//Quando tirar o mouse de cima, volta o cursor ao normal
			public void mouseExited(MouseEvent e) {
				optionLbl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
		});
		
		//Define o primeiro item que já começa selecionado
		if(item.isSelected()) {
			addUnderline(optionLbl);
			lastSelectedLabel = optionLbl;
		}
		
		return optionLbl;
	}
	
	/**
	 * Método que adiciona underline a um objeto do tipo JLabel
	 * @param label -> objeto do tipo JLabel
	 */
	private void addUnderline(JLabel label) {
        String text = label.getText();
        label.setText("<html><u>" + text + "</u></html>");
    }

	/**
	 * Método que retira o underline de um objeto do tipo JLabel
	 * @param label -> objeto do tipo JLabel
	 */
    private void removeUnderline(JLabel label) {
        String text = label.getText();
        text = text.replaceAll("<html><u>", "").replaceAll("</u></html>", "");
        label.setText(text);
    }
  
}