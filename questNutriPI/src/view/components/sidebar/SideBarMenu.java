package view.components.sidebar;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JLabel;

import view.components.generics.GenericComponent;

/**
 * Classe que representa um menu lateral (SideBar) composto por itens de menu (SideBarItem).
 */
public class SideBarMenu extends SideBarComponent<GenericComponent> {
	/**
	 * Define a última label clicada para ter underline.
	 */
	private JLabel lastSelectedLabel = null;
	
    /**
     * Array de itens de menu (SideBarItem) no menu lateral.
     */
	public SideBarItem[] items;
	
    /**
     * Construtor que inicializa o SideBarMenu com uma lista de itens de menu.
     *
     * @param items Array de SideBarItem representando os itens do menu.
     */
	public SideBarMenu(SideBarItem ...items) {
		this.component = new GenericComponent();
		this.items = items;
		component.ltGridBag();
		component.setBackground(STD_LIGHT_GRAY_COLOR);
		createMenuItems(items);
	}
	
	/**
	 * Método que adiciona ao SideBar os itens que trocam o panel de exibição.
	 * @param windows -> Array de SideBarItem
	 */
	private void createMenuItems(SideBarItem[] windows) {
		for(SideBarItem window: windows) {
			JLabel optionLbl = createItemLabel(window);

			 // Criar um Map de atributos de texto para configurar o espaçamento entre letras
	        Map<TextAttribute, Object> attributes = new HashMap<>();
	        attributes.put(TextAttribute.TRACKING, 0.2); // Valor positivo para aumentar o espaçamento
	        
	        // Aplicar os atributos de texto à fonte atual
	        Font modifiedFont = STD_LIGHT_FONT.deriveFont(16f).deriveFont(attributes);
	        optionLbl.setFont(modifiedFont);
	        component.add(optionLbl, component.gbc.wgt(0).anchor("WEST").yP().fill("NONE").insets(10, 0));
	        component.add(Box.createVerticalStrut(15));
		}
	}
	
	/**
	 * Método que cria um JLabel e configura suas interações particulares da SideBar
	 * @param item -> objeto do tipo SideBarItem, que contém as especificações do item do menu
	 * @return -> JLabel com as especificações enviadas no item.
	 */
	private JLabel createItemLabel(SideBarItem item) {
		JLabel optionLbl = new JLabel(item.text);
		item.setLbl(optionLbl);
		item.setBarMenu(this);
		
		optionLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				item.performEvent();
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
			item.performEvent();
		}
		
		return optionLbl;
	}
	
    /**
     * Método que troca o sublinhado para a JLabel especificada, removendo o sublinhado do último selecionado.
     *
     * @param lbl JLabel que terá o sublinhado aplicado.
     */
	public void swapUnderline(JLabel lbl) {
        if (lastSelectedLabel != null) {
            removeUnderline(lastSelectedLabel);
        }

        addUnderline(lbl);
        lastSelectedLabel = lbl;
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
    
    
    /**
     * Método que define o primeiro painel a ser exibido, baseado nos itens inicialmente selecionados.
     */
    public void setFirstPanel() {
    	for(SideBarItem item: items) {
    		if(item.isSelected()) item.performEvent();
    	}
    }
    
}