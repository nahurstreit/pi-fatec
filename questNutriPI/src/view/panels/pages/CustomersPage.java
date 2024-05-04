package view.panels.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import view.components.HintInputField;
import view.panels.LoggedPanel;

public class CustomersPage extends GenericPage {
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public CustomersPage(LoggedPanel mainPanel) {
		this.ltGridBag();
		
		ArrayList<String> itens = new ArrayList<String>();
		for(int i = 0; i < 100; i++) {
			itens.add("Item "+ (i+1));
		}
		
		gbcGridXY(0);
		JLabel lblControl = new JLabel("Controle de Clientes");
		lblControl.setFont(STD_BOLD_FONT.deriveFont(45f));
		this.add(lblControl, gbc);
		
		JPanel bluePanel = new JPanel();
		bluePanel.setLayout(new GridBagLayout());
		bluePanel.setBackground(STD_BLUE_COLOR);
		gbcWgXY(1.0);
		gbcFill("BOTH");
		gbcInsets(30);
		this.add(bluePanel, gbcYp());
		
		JPanel whitePanel = new JPanel();
		whitePanel.setLayout(new GridBagLayout());
		whitePanel.setBackground(Color.white);
		bluePanel.add(whitePanel, gbc);
		
		
		gbcInsets();
		JPanel searchTypePanel = new JPanel();
		searchTypePanel.setLayout(new GridBagLayout());
		
		gbcGridXY(0);
		gbcFill("BOTH");
		JLabel lblSearchType = new JLabel("Pesquisar por");
		lblSearchType.setFont(STD_BOLD_FONT.deriveFont(20f));
		searchTypePanel.add(lblSearchType, gbc);
		

		HintInputField selectInput = new HintInputField(" {tipo}", new Dimension(80, 10), 14f);
		searchTypePanel.add(selectInput, gbcYp());
		
		gbcGridXY(1,0);
		JLabel lblSearchTerm = new JLabel("Termo de Pesquisa");
		lblSearchTerm.setFont(STD_BOLD_FONT.deriveFont(20f));
		searchTypePanel.add(lblSearchTerm, gbc);
		
		HintInputField searchInput = new HintInputField("Digite aqui...", new Dimension(80, 10), 14f);
		searchTypePanel.add(searchInput, gbcYp());
		
		whitePanel.add(searchTypePanel, gbc);
		
		
		
		JList<String> list = new JList<>(itens.toArray(new String[0]));
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250, 250));
        scrollPane.setMaximumSize(new Dimension(250, 250));
        
        gbcInsets(20);
        gbcGridXY(1, 2);
        gbcFill("BOTH");
        whitePanel.add(scrollPane, gbc);
		
	}
	
}


//super();
//this.add(new JLabel("Customers Page"));
//JButton c1 = new JButton("Cliente 1");
//
//Meal[] mealsCliente1 = {
//		new Meal("Café da Manhã A - C1", 10000, 84, true),
//		new Meal("Café da Manhã B - C1", 10000, 42, true),
//		new Meal("Café da Manhã C - C1", 10000, 1, true),
//		new Meal("Almoço A - C1", 20000, 84, true),
//		new Meal("Almoço B - C1", 20000, 42, true),
//		new Meal("Almoço C - C1", 20000, 1, true),
//		new Meal("Jantar A - C1", 20000, 84, true),
//		new Meal("Jantar B - C1", 20000, 42, true),
//		new Meal("Jantar C - C1", 20000, 1, true),
//		
//		new Meal("Café da tarde X - C1", 20000, 64, true),
//		new Meal("Café da tarde Y - C1", 20000, 16, true),
//		new Meal("Café da tarde Z - C1", 20000, 2, true),
//	};
//
//c1.addActionListener(new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		mainPanel.swapLoggedMainPanel(new DietPage(mealsCliente1));
//	}
//});
//this.add(c1);
//
//JButton c2 = new JButton("Cliente 2");
//Meal[] mealsCliente2 = {
//		new Meal("Café da Manhã - C2", 10000, 127, true),
//		new Meal("Lanche Manhã - C2", 10000, 127, true),
//		new Meal("Almoço - C2", 10000, 127, true),
//		new Meal("Lanche da Tarde - C2", 10000, 127, true),
//		new Meal("Jantar - C2", 10000, 127, true),
//		new Meal("Lanche da Noite - C2", 10000, 127, true),
//	};
//c2.addActionListener(new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		mainPanel.swapLoggedMainPanel(new DietPage(mealsCliente2));
//	}
//});
//
//this.add(c2);