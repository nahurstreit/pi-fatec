package view.panels.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.QuestNutri;
import view.components.HintInputField;
import view.panels.LoggedPanel;
import view.panels.components.GenericComp;
import view.panels.components.SideBar;
import view.panels.components.SideBarItem;
import view.panels.components.SideBarMenu;

public class CustomersPage extends GenericPage {
	private static final long serialVersionUID = 1L;

	public static JFrame customerFrame = null;
	
	public CustomersPage(LoggedPanel mainPanel) {
		this.ltGridBag();
		
		GenericComp blueBox = new GenericComp();
			blueBox.setBackground(STD_BLUE_COLOR);
			blueBox.ltGridBag();
		
		
		//Caixa de cima do painel branco
		GenericComp whiteBox = new GenericComp();
			whiteBox.setBackground(Color.green);
			whiteBox.ltGridBag();
		
			//Caixa do tipo de pesquisa
			GenericComp typeBox = new GenericComp();
			typeBox.setBackground(Color.white);
			typeBox.ltGridBag();
		
				//Label superior tipo de pesquisa
				JLabel lblTypeBox = new JLabel("Pesquisar por");
					lblTypeBox.setFont(STD_BOLD_FONT.deriveFont(20f));
					typeBox.add(lblTypeBox, typeBox.gbc.grid(0, 0).wgt(1.0).fill("BOTH").insets("3", 0, 10));
					
				//InputBox tipo de pesquisa
				HintInputField inputTypeBox = new HintInputField("{tipo}", new Dimension(100, 25), 15f);
					typeBox.add(inputTypeBox, typeBox.gbc.yP().insets("1", 0, 10));
				
			//Caixa do termo de pesquisa
			GenericComp searchBox = new GenericComp();
			searchBox.setBackground(Color.white);
			searchBox.ltGridBag();
		
				//Label superior tipo de pesquisa
				JLabel lblSearchBox = new JLabel("Termo de Pesquisa");
					lblSearchBox.setFont(STD_BOLD_FONT.deriveFont(20f));
					searchBox.add(lblSearchBox, searchBox.gbc.grid(0, 0).wgt(1.0).fill("BOTH").insets("3", 0, 10));
					
				//InputBox tipo de pesquisa
				HintInputField inputSearchBox= new HintInputField("Digite aqui...", new Dimension(100, 25), 15f);
					searchBox.add(inputSearchBox, searchBox.gbc.yP().insets("1", 0, 10));			
			
		
		GenericComp upperBox = new GenericComp();
		upperBox.setBackground(Color.red);
		upperBox.ltGridBag();
		upperBox.add(typeBox, upperBox.gbc.wgt(0).fill("NONE").insets(10));
		upperBox.add(searchBox, upperBox.gbc.wgt(1.0, 0).fill("BOTH").insets("2", 0, 10));
		
		whiteBox.add(upperBox, whiteBox.gbc.anchor("NORTH").wgt(1.0, 0).fill("HORIZONTAL"));
				
		
		// Criação da lista de itens
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < 100; i++) {
            listModel.addElement("Item " + (i + 1));
        }

        // Criação do componente de lista
        JList<String> itemList = new JList<>(listModel);

        // Criação do JScrollPane e passagem do componente de lista
        JScrollPane bottomBox = new JScrollPane(itemList);
        
        itemList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Obtém o índice do item selecionado
                    int selectedIndex = itemList.getSelectedIndex();
                    // Obtém o valor do item selecionado
                    String selectedItem = itemList.getSelectedValue();
                    openCustomerFrame(selectedIndex);
                }
            }
        });
        
        
        whiteBox.add(bottomBox, whiteBox.gbc.gridY(1).anchor("NORTH").wgt(1.0).fill("BOTH").insets(10));
		
		
		blueBox.add(whiteBox, blueBox.gbc.anchor("NORTHWEST").wgt(1.0).fill("BOTH").insets(30));
		
		JLabel pageLbl = new JLabel("Controle de Clientes");
		pageLbl.setFont(STD_BOLD_FONT.deriveFont(40f));
		this.add(pageLbl, gbc.grid(0));        
        
		this.add(blueBox, gbc.yP().wgt(1.0).insets("1", 20, 40).fill("BOTH"));
		
	}
	
	private void openCustomerFrame(int id) {
		CustomersPage.customerFrame = new JFrame();
		
		int x = QuestNutri.app.getX() + QuestNutri.app.getWidth()/10;
		int y = QuestNutri.app.getY() + QuestNutri.app.getHeight()/10;
		
		int w = QuestNutri.app.getWidth() - (2*x);
		int h = QuestNutri.app.getHeight() - (2*y);
		
		customerFrame.setBounds(x, y, w, h);
//		customerFrame.setUndecorated(true);
		customerFrame.setTitle("Cliente - "+"{holderCustomerName}");
		
		QuestNutri.followYouIntoTheDark();
		
		customerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                QuestNutri.heraldOfDarkness();;
            }
        });
		
		JPanel lateralPanel = new JPanel();
		
		SideBarMenu menu = new SideBarMenu(
				new SideBarItem("Perfil", null, true),
				new SideBarItem("Dieta", () -> swapCustomerPanel(lateralPanel)),
				new SideBarItem("X", () -> closeCustomerPage(customerFrame))
				);
		
		
		SideBar customerSideBar = new SideBar(menu);
		customerSideBar.setMinimumSize(new Dimension(250, this.getHeight()));
		customerSideBar.setPreferredSize(new Dimension(250, this.getHeight()));
		
		customerFrame.add(customerSideBar);
		
		customerFrame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
//		customerFrame.add(new SideBar("Cliente", items), gbc);
		
		

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; //preenche todo o resto disponível

		customerFrame.add(lateralPanel, gbc);
		customerFrame.setVisible(true);
	}
	
	public static void closeCustomerPage(JFrame frame) {
		QuestNutri.heraldOfDarkness();
		frame.dispose();
	}
	
	public static void swapCustomerPanel(JPanel panel) {
		customerFrame.remove(panel);
		panel = new DietPage();
		customerFrame.add(panel);
		customerFrame.revalidate();
		customerFrame.repaint();
	}
	
}