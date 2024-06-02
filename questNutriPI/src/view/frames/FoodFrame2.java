package view.frames;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import model.entities.Aliment;
import model.entities.Food;
import view.components.HintInputField;
import view.components.utils.StdGBC;
import view.panels.components.AlimentNutritionalTable;
import view.panels.components.GeneralJPanelSettings;
import view.panels.components.GenericJScrollPaneList;
import view.utils.LanguageUtil;

public class FoodFrame2 {

	private JFrame frame;
	
	private Food food;
	
	public Aliment foodAliment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodFrame2 window = new FoodFrame2(Food.findByPK(1));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FoodFrame2(Food food) {
		this.food = food;
		this.foodAliment = food.aliment;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 930, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setBackground(new Color(255, 255, 255));
		selectionPanel.setBounds(0, 0, 357, 474);
		selectionPanel.setLayout(null);
		selectionPanel.setBackground(GeneralJPanelSettings.STD_BLUE_COLOR);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(0, 0, 984, 474);
		frame.getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(12f));
		tabbedPane.setBounds(10, 11, 909, 452);
		infoPanel.add(tabbedPane);
		
		tabbedPane.addTab("Alimento", selectionPanel);
		
		JPanel rTable_NutriInfo = new JPanel();
		rTable_NutriInfo.setBounds(10, 11, 317, 402);
		selectionPanel.add(rTable_NutriInfo);
		rTable_NutriInfo.setLayout(null);
		
		JScrollPane l_lowerScrollPanel = new JScrollPane();
		l_lowerScrollPanel.setBounds(10, 96, 297, 295);
		rTable_NutriInfo.add(l_lowerScrollPanel);
				
		JPanel l_upperPanel = new JPanel();
		l_upperPanel.setBounds(10, 11, 297, 74);
		rTable_NutriInfo.add(l_upperPanel);
		l_upperPanel.setLayout(null);
		
		JLabel lblCurrentSelected = new JLabel(new LanguageUtil("Selecionado atualmente", "Current selected").get());
		lblCurrentSelected.setBounds(0, 11, 277, 14);
		lblCurrentSelected.setFont(GeneralJPanelSettings.STD_MEDIUM_FONT.deriveFont(10f));
		l_upperPanel.add(lblCurrentSelected);
		
		JLabel lblCurrentAlimentSelected = new JLabel(foodAliment.name);
		lblCurrentAlimentSelected.setFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(12f));
		lblCurrentAlimentSelected.setBounds(0, 24, 277, 14);
		l_upperPanel.add(lblCurrentAlimentSelected);
		
		JPanel r_panel = new JPanel();
		r_panel.setBounds(337, 11, 556, 402);
		selectionPanel.add(r_panel);
		r_panel.setLayout(null);
		
		JPanel searchBoxPanel = new JPanel();
		searchBoxPanel.setBounds(277, 11, 269, 71);
		r_panel.add(searchBoxPanel);
		searchBoxPanel.setLayout(null);
		
		
		HintInputField formattedTextField = new HintInputField("Digite aqui...");
		formattedTextField.setHint("Digite aqui...");
		formattedTextField.setValue(food.aliment.name);
		formattedTextField.setBounds(0, 36, 269, 20);
		searchBoxPanel.add(formattedTextField);
		
		JLabel lblNameAliment = new JLabel("New label");
		lblNameAliment.setBounds(0, 11, 269, 14);
		lblNameAliment.setText(new LanguageUtil("Nome do Alimento", "Aliment's Name").get());
		lblNameAliment.setFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(10f));
		searchBoxPanel.add(lblNameAliment);
		formattedTextField.setHint("");
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(277, 94, 269, 296);
		searchPanel.setLayout(new GridBagLayout());
		StdGBC searchPanel_gbc = new StdGBC();
		r_panel.add(searchPanel);
		
		JScrollPane l_lowerNutriInfo_search = new JScrollPane();
		l_lowerNutriInfo_search.setBounds(10, 93, 257, 297);
		r_panel.add(l_lowerNutriInfo_search);
		
		JPanel l_upperPanel_1 = new JPanel();
		l_upperPanel_1.setLayout(null);
		l_upperPanel_1.setBounds(10, 11, 257, 71);
		r_panel.add(l_upperPanel_1);
		
		JLabel lblCurrentSelected_1 = new JLabel(new LanguageUtil("Visualizando atualmente", "Currently viewing").get());
		lblCurrentSelected_1.setBounds(0, 11, 210, 14);
		lblCurrentSelected_1.setFont(GeneralJPanelSettings.STD_MEDIUM_FONT.deriveFont(10f));
		l_upperPanel_1.add(lblCurrentSelected_1);
		
		//JLabel lblCurrentAlimentView = new JLabel(new LanguageUtil("Biscoito, doce, recheado com chocolate", "None.").get());
		JLabel lblCurrentAlimentView = new JLabel(new LanguageUtil("Nenhum.", "None.").get());
		lblCurrentAlimentView.setFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(12f));
		lblCurrentAlimentView.setBounds(0, 24, 257, 14);
		l_upperPanel_1.add(lblCurrentAlimentView);
		
		//StdButton btnNewButton = StdButton.stdBtnConfig(new LanguageUtil("Trocar alimento atual por este", "Set this as current aliment").get()).setUpFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(11f));
		JButton btnNewButton = new JButton("Trocar alimento atual por este");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(0, 40, 257, 24);
		l_upperPanel_1.add(btnNewButton);
		
		GenericJScrollPaneList<Aliment> alimentList = new GenericJScrollPaneList<Aliment>()
				.setOriginList(Aliment.findAll())
				.setColumnNames(new Object[] {"Nome", "Grupo"})
				.setRowMapper(aliment -> new Object[] {aliment.name, aliment.alimentGroup})
				.setDoubleClickAction(aliment -> () -> {
					JTable table = new JTable(new AlimentNutritionalTable(aliment));
					l_lowerNutriInfo_search.setViewportView(table);
					lblCurrentAlimentView.setText(aliment.name);
				})
				.init();
		searchPanel.add(alimentList, searchPanel_gbc.fill("BOTH").anchor("NORTHWEST").wgt(1.0));
				
		JTable table = new JTable(new AlimentNutritionalTable(foodAliment, food.quantity));
		l_lowerScrollPanel.setViewportView(table);
		
	}
}
