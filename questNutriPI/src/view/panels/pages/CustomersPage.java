package view.panels.pages;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import controller.CustomerController;
import controller.InterfaceController;
import view.components.HintInputField;
import view.panels.components.GenericComponent;
import view.panels.components.GenericJPanel;

public class CustomersPage extends GenericPage {
	private static final long serialVersionUID = 1L;

	public static JFrame customerFrame = null;
	
	public GenericComponent whiteBox;
	public JScrollPane customerList = null;
	
	public JComboBox<String> comboBox;
	public HintInputField inputSearchBox;
	
	public CustomersPage(GenericJPanel ownerPanel) {
		super(ownerPanel);
		this.ltGridBag();
		
		GenericComponent blueBox = new GenericComponent();
			blueBox.setBackground(STD_BLUE_COLOR);
			blueBox.ltGridBag();
		
		
		//Caixa de cima do painel branco
		whiteBox = new GenericComponent();
			whiteBox.setBackground(Color.green);
			whiteBox.ltGridBag();
		
			//Caixa do tipo de pesquisa
			GenericComponent typeBox = new GenericComponent();
			typeBox.setBackground(Color.white);
			typeBox.ltGridBag();
		
				//Label superior tipo de pesquisa
				JLabel lblTypeBox = new JLabel("Pesquisar por");
					lblTypeBox.setFont(STD_BOLD_FONT.deriveFont(20f));
					typeBox.add(lblTypeBox, typeBox.gbc.grid(0, 0).wgt(1.0).fill("BOTH").insets("3", 0, 10));
					
					// Criação da drop box com os possíveis valores de pesquisa
					String[] searchOptions = {"CPF", "Nome", "Telefone", "Data de Nascimento"};
					comboBox = new JComboBox<>(searchOptions);

					// Personalizando a fonte no item selecionado
					comboBox.setFont(GenericJPanel.STD_BOLD_FONT.deriveFont(16f));
					// Personalizando a fonte dos itens na JComboBox
					comboBox.setRenderer(new DefaultListCellRenderer() {
						private static final long serialVersionUID = 1L;

						@Override
					    public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
					        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
					        setFont(GenericJPanel.STD_REGULAR_FONT.deriveFont(15f)); // Altere a fonte conforme desejado
					        return this;
					    }
					});
					
					// Adicionando o JComboBox à caixa do tipo de pesquisa (typeBox)
					typeBox.add(comboBox, typeBox.gbc.yP().insets("1", 0, 10));
				
			//Caixa do termo de pesquisa
			GenericComponent searchBox = new GenericComponent();
			searchBox.setBackground(Color.white);
			searchBox.ltGridBag();
		
				//Label superior tipo de pesquisa
				JLabel lblSearchBox = new JLabel("Termo de Pesquisa");
					lblSearchBox.setFont(STD_BOLD_FONT.deriveFont(20f));
					searchBox.add(lblSearchBox, searchBox.gbc.grid(0, 0).wgt(1.0).fill("BOTH").insets("3", 0, 10));
					
				//InputBox tipo de pesquisa
				inputSearchBox = new HintInputField("Digite aqui...", new Dimension(100, 25), 15f);
					searchBox.add(inputSearchBox, searchBox.gbc.yP().insets("1", 0, 10));
				
				JButton searchBtn = new JButton("Buscar");
					searchBtn.setFont(GenericJPanel.STD_BOLD_FONT.deriveFont(13f));
					searchBtn.setBackground(STD_BLUE_COLOR);
					searchBtn.setForeground(Color.white);
					
					CustomersPage currentPage = this;
					searchBtn.addActionListener(new ActionListener() {
					    @Override
					    public void actionPerformed(ActionEvent e) {
					        CustomerController.searchCustomerList(currentPage);
					    }
					});
					
					searchBox.add(searchBtn, searchBox.gbc.xP().wgt(0).insets(0, 0, 10, 10));
				
			
		
		GenericComponent upperBox = new GenericComponent();
		upperBox.setBackground(Color.red);
		upperBox.ltGridBag();
		upperBox.add(typeBox, upperBox.gbc.wgt(0).fill("NONE").insets(10));
		upperBox.add(searchBox, upperBox.gbc.wgt(1.0, 0).fill("BOTH").insets("2", 0, 10));
		
		whiteBox.add(upperBox, whiteBox.gbc.anchor("NORTH").wgt(1.0, 0).fill("HORIZONTAL"));
        
		customerList = InterfaceController.getCustomerList();
		
        whiteBox.add(customerList, whiteBox.gbc.gridY(1).anchor("NORTH").wgt(1.0).fill("BOTH").insets(10));
		
		
		blueBox.add(whiteBox, blueBox.gbc.anchor("NORTHWEST").wgt(1.0).fill("BOTH").insets(30));
		
		JLabel pageLbl = new JLabel("Controle de Clientes");
		pageLbl.setFont(STD_BOLD_FONT.deriveFont(40f));
		this.add(pageLbl, gbc.grid(0));        
        
		this.add(blueBox, gbc.yP().wgt(1.0).insets("1", 20, 40).fill("BOTH"));
		
	}
	
	public boolean setCustomersList(JScrollPane list) {
	    try {
	        // Remove o componente existente da página
	        this.whiteBox.remove(customerList);
	        
	        // Atualiza a referência para o novo componente
	        this.customerList = list;
	        
	        // Adiciona o novo componente à página
	        this.whiteBox.add(customerList, whiteBox.gbc.gridY(1).anchor("NORTH").wgt(1.0).fill("BOTH").insets(10));
	        
	        // Revalida e repinta a página para aplicar as alterações
	        this.revalidate();
	        this.repaint();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	}
	
}