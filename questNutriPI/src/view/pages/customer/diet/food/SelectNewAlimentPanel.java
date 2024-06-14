package view.pages.customer.diet.food;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.entities.Aliment;
import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.generics.GenericJPanel;
import view.components.generics.GenericJScrollPaneList;
import view.components.inputs.HintInputField;
import view.components.labels.BreakActionLbl;
import view.components.tables.AlimentNutritionalTable;
import view.components.utils.StdGBC;

public class SelectNewAlimentPanel extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lista para armazenar todos os alimentos do banco de dados
	 */
	private GenericJScrollPaneList<Aliment> alimentSearchList;
	
	/**
	 * Label para mostrar o nome do alimento selecionado na lista de alimentList
	 */
	private BreakActionLbl selectedAliment; 
	
	/**
	 * JScrolPane para exibir as informações nutricionais do alimento selecionado em alimentList
	 */
	private JScrollPane scrollPaneSelectedAlimentInfo;

	public SelectNewAlimentPanel(StdButton upperButton) {
		this.setLayout(null);
		
		JLabel searchAlimentSectionLbl = new JLabel(new LanguageUtil("Pesquisa de alimentos", "Search aliments").get());
		searchAlimentSectionLbl.setBounds(296, 11, 269, 23);
		searchAlimentSectionLbl.setFont(STD_BOLD_FONT.deriveFont(18f));
		this.add(searchAlimentSectionLbl);
		
		JPanel searchAlimentPanel = new JPanel();
		searchAlimentPanel.setBounds(296, 44, 269, 38);
		this.add(searchAlimentPanel);
		searchAlimentPanel.setLayout(null);
		
		HintInputField searchAlimentInputName = new HintInputField("Digite aqui...");
		searchAlimentInputName.setHint("Digite aqui...");
		searchAlimentInputName.setBounds(0, 15, 269, 20);
		searchAlimentPanel.add(searchAlimentInputName);
		
		JLabel searchAlimentUpperLbl = new JLabel("New label");
		searchAlimentUpperLbl.setBounds(0, 0, 269, 14);
		searchAlimentUpperLbl.setText(new LanguageUtil("Nome do Alimento", "Aliment's Name").get());
		searchAlimentUpperLbl.setFont(STD_BOLD_FONT.deriveFont(10f));
		searchAlimentPanel.add(searchAlimentUpperLbl);
		searchAlimentInputName.setHint("");
		
		JPanel searchAlimentListPanel = new JPanel();
		searchAlimentListPanel.setBounds(296, 93, 269, 297);
		searchAlimentListPanel.setLayout(new GridBagLayout());
		StdGBC searchPanel_gbc = new StdGBC();
		this.add(searchAlimentListPanel);
		
		scrollPaneSelectedAlimentInfo = new JScrollPane();
		scrollPaneSelectedAlimentInfo.setBounds(10, 93, 257, 297);
		this.add(scrollPaneSelectedAlimentInfo);
		
		JPanel selectedAlimentPanel = new JPanel();
		selectedAlimentPanel.setLayout(null);
		selectedAlimentPanel.setBounds(10, 44, 257, 49);
		this.add(selectedAlimentPanel);
		
		JLabel selectedAlimentUpperLbl = new JLabel(new LanguageUtil("Visualizando atualmente", "Currently viewing").get());
		selectedAlimentUpperLbl.setBounds(0, 0, 257, 14);
		selectedAlimentUpperLbl.setFont(STD_MEDIUM_FONT.deriveFont(10f));
		selectedAlimentPanel.add(selectedAlimentUpperLbl);
		
		//Label superior do lado direito que diz qual é o aliemnto selecionado atualmente.

		selectedAliment = new BreakActionLbl()
				.setUpText(new LanguageUtil("Nenhum.", "None.").get())
				.setUpFont(STD_BOLD_FONT.deriveFont(12f))
				.turnHoverOff()
				.setBGColor(this.getBackground());
		selectedAliment.setBounds(0, 15, 257, 34);
		selectedAlimentPanel.add(selectedAliment);
		
		alimentSearchList = new GenericJScrollPaneList<Aliment>()
				.setOriginList(Aliment.findAll())
				.setColumnNames(new Object[] {"Nome", "Grupo"})
				.setRowMapper(aliment -> new Object[] {aliment.name, aliment.alimentGroup});
		
		searchAlimentListPanel.add(alimentSearchList, searchPanel_gbc.fill("BOTH").anchor("NORTHWEST").wgt(1.0));
		
		//StdButton interactionButton = StdButton.stdBtnConfig(new LanguageUtil("Trocar alimento atual por este", "Set this as current aliment").get()).setUpFont(STD_BOLD_FONT.deriveFont(11f));
		JButton interactionButton = new JButton("Trocar alimento atual por este");
		interactionButton.setBounds(10, 11, 257, 24);
		this.add(interactionButton);
		interactionButton.setHorizontalAlignment(SwingConstants.LEFT);
		

		
	    JSeparator separator = new JSeparator(SwingConstants.VERTICAL);  // Separador vertical
	    separator.setBounds(280, 11, 1, 379);

        // Criação de uma borda personalizada
        Border border = BorderFactory.createLineBorder(STD_BLUE_COLOR, 1);
        separator.setBorder(border);
        
        this.add(separator);
        //init();
	}
	
	private void init() {
		selectedAliment.init();
		alimentSearchList
		.setDoubleClickAction(aliment -> () -> {
			JTable table = new JTable(new AlimentNutritionalTable(aliment));
			scrollPaneSelectedAlimentInfo.setViewportView(table);
			selectedAliment.setText(aliment.name);
		})
		.init();
	}
}