package view.frames;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.entities.Aliment;
import model.entities.Food;
import utils.interfaces.GeneralVisualSettings;
import view.components.buttons.StdButton;
import view.pages.customer.diet.food.SelectNewAlimentPanel;

public class FoodFrame2 implements GeneralVisualSettings {

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
		frame.setBounds(100, 100, 938, 462);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setBackground(new Color(255, 255, 255));
		selectionPanel.setBounds(0, 0, 935, 427);
		selectionPanel.setLayout(null);
		selectionPanel.setBackground(STD_BLUE_COLOR);
		frame.setContentPane(new SelectNewAlimentPanel(new StdButton()));
		
//		JPanel leftPanel_current = new JPanel();
//		leftPanel_current.setBounds(10, 10, 317, 402);
//		selectionPanel.add(leftPanel_current);
//		leftPanel_current.setLayout(null);
//		
//		JScrollPane lfP_lowerPanel = new JScrollPane();
//		lfP_lowerPanel.setBounds(10, 93, 297, 298);
//		leftPanel_current.add(lfP_lowerPanel);
//				
//		JPanel lfP_upperPanel = new JPanel();
//		lfP_upperPanel.setBounds(10, 45, 297, 37);
//		leftPanel_current.add(lfP_upperPanel);
//		lfP_upperPanel.setLayout(null);
//		
//		JLabel lfP_upperPanelCurrentLabel = new JLabel(new LanguageUtil("Selecionado atualmente", "Current selected").get());
//		lfP_upperPanelCurrentLabel.setBounds(0, 0, 297, 14);
//		lfP_upperPanelCurrentLabel.setFont(STD_MEDIUM_FONT.deriveFont(10f));
//		lfP_upperPanel.add(lfP_upperPanelCurrentLabel);
//		
//		JLabel lfP_upperPanelAlimentLabel = new JLabel(foodAliment.name);
//		lfP_upperPanelAlimentLabel.setFont(STD_BOLD_FONT.deriveFont(12f));
//		lfP_upperPanelAlimentLabel.setBounds(0, 15, 297, 14);
//		lfP_upperPanel.add(lfP_upperPanelAlimentLabel);
//		
//		JLabel lfP_comparisonTextLabel = new JLabel("Janela de Comparação");
//		lfP_comparisonTextLabel.setFont(STD_BOLD_FONT.deriveFont(18f));
//		lfP_comparisonTextLabel.setBounds(10, 11, 297, 23);
//		leftPanel_current.add(lfP_comparisonTextLabel);
				
//		JTable table = new JTable(new AlimentNutritionalTable(foodAliment, food.quantity));
//		l_lowerScrollPanel.setViewportView(table);
		
	}
	
	private void initDisableDesign() {
		
	}
}
