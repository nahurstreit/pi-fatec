package view.pages.customer.diet.food;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.entities.AlimentController;
import model.entities.Aliment;
import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.generics.GenericJPanel;
import view.components.generics.GenericJScrollPaneList;
import view.components.inputs.HintInputField;
import view.components.labels.BreakActionLbl;
import view.components.tables.AlimentNutritionalTable;

public class SelectNewAlimentPanel extends GenericJPanel {
    private static final long serialVersionUID = 1L;

    private GenericJScrollPaneList<Aliment> alimentSearchList;
    private BreakActionLbl selectedAliment;
    private JScrollPane scrollPaneSelectedAlimentInfo;
    private Aliment currentSelected = null;

    public SelectNewAlimentPanel(StdButton upperButton) {
        this.setLayout(new GridBagLayout());
        
        //Panel da esquerda
        GenericJPanel leftPanel = new GenericJPanel().ltGridBag();
        this.add(leftPanel, gbc.grid(0).fill("BOTH").wgt(0.75, 1.0).insets(10, 10, 10, 0).anchor("NORTHWEST"));
        
        upperButton.setHorizontalAlignment(SwingConstants.LEFT);
        upperButton.setPreferredSize(new Dimension(200, 20));
        leftPanel.add(upperButton, leftPanel.gbc.grid(0).fill("HORIZONTAL").wgt(1.0, 0).anchor("NORTHWEST").insets(10));
        
        JLabel selectedAlimentUpperLbl = new JLabel(new LanguageUtil("Visualizando atualmente", "Currently viewing").get());
        selectedAlimentUpperLbl.setFont(STD_MEDIUM_FONT.deriveFont(10f));
        leftPanel.add(selectedAlimentUpperLbl, leftPanel.gbc.grid(0, 1).fill("HORIZONTAL").anchor("NORTHWEST").wgt(1.0, 0).insets(0, 10));
        
        selectedAliment = new BreakActionLbl()
            .setUpText(new LanguageUtil("Nenhum.", "None.").get())
            .setUpFont(STD_BOLD_FONT.deriveFont(12f))
            .turnHoverOff()
            .setBGColor(this.getBackground());
        leftPanel.add(selectedAliment, leftPanel.gbc.grid(0, 2).wgt(1.0, 0).insets(0, 10, 10, 10));
        
        scrollPaneSelectedAlimentInfo = new JScrollPane();
        leftPanel.add(scrollPaneSelectedAlimentInfo, leftPanel.gbc.grid(0, 3).fill("BOTH").wgt(1.0).insets(0, 10, 10, 10).height("REMAINDER"));
        
        
//        //Separador do meio
//        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
//        Border border = BorderFactory.createLineBorder(STD_BLUE_COLOR, 1);
//        separator.setBorder(border);
//        this.add(separator, gbc.grid(1, 0).fill("VERTICAL").anchor("CENTER").wgt(0.0, 1.0).insets(10));

        
        
        //Panel da direita
        GenericJPanel rightPanel = new GenericJPanel().ltGridBag();
        this.add(rightPanel, gbc.xP().fill("BOTH").wgt(0.25, 1.0).insets(10, 0, 10, 10).anchor("NORTHWEST"));

        JLabel searchAlimentSectionLbl = new JLabel(new LanguageUtil("Pesquisa de alimentos", "Search aliments").get());
        searchAlimentSectionLbl.setFont(STD_BOLD_FONT.deriveFont(18f));
        rightPanel.add(searchAlimentSectionLbl, rightPanel.gbc.grid(0, 0).fill("HORIZONTAL").anchor("NORTHWEST").wgt(1.0, 0.0).insets(10, 10, 5, 10));

        GenericJPanel searchAlimentPanel = new GenericJPanel().ltGridBag();
        rightPanel.add(searchAlimentPanel, rightPanel.gbc.grid(0, 1).fill("HORIZONTAL").wgt(1.0, 0.0).insets(0, 10, 10, 5));

        JLabel searchAlimentUpperLbl = new JLabel(new LanguageUtil("Nome do Alimento", "Aliment's Name").get());
        searchAlimentUpperLbl.setFont(STD_BOLD_FONT.deriveFont(10f));
        searchAlimentPanel.add(searchAlimentUpperLbl, searchAlimentPanel.gbc.grid(0, 0).fill("HORIZONTAL").anchor("NORTHWEST").insets(0));

        HintInputField searchAlimentInputName = new HintInputField();
        searchAlimentInputName.setHint("Digite aqui...");
        searchAlimentPanel.add(searchAlimentInputName, searchAlimentPanel.gbc.grid(0, 1).fill("HORIZONTAL").wgt(1.0, 0.0).insets(5, 0, 0, 0));

     // Adicionando DocumentListener para monitorar mudanças no texto do campo de busca
        searchAlimentInputName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAliments();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAliments();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAliments();
            }

            private void searchAliments() {
            	alimentSearchList.setOriginList(AlimentController.searchAliments(new LanguageUtil("Nome", "Name").get(), searchAlimentInputName.getValue()))
				 				 .buildTableModel()
				 				 .init(); //init chama configureTable

				alimentSearchList.repaint();
				alimentSearchList.revalidate();
            }
        });
        
        alimentSearchList = new GenericJScrollPaneList<Aliment>()
            .setOriginList(Aliment.findAll())
            .setColumnNames(new Object[] {"Nome"})
            .setRowMapper(aliment -> new Object[] {aliment.name})
            .setCellFont(STD_REGULAR_FONT.deriveFont(12f));
        rightPanel.add(alimentSearchList, rightPanel.gbc.grid(0, 2).fill("BOTH").wgt(1.0));

        init();
    }

    private void init() {
        selectedAliment.init();
        alimentSearchList
            .setDoubleClickAction(aliment -> () -> {
                currentSelected = aliment;
                JTable table = new JTable(new AlimentNutritionalTable(aliment));
                scrollPaneSelectedAlimentInfo.setViewportView(table);
                selectedAliment.setText(aliment.name);
            })
            .init();
    }

    public Aliment getSelectedAliment() {
        return currentSelected;
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setBounds(100, 100, 883, 462);
        f.setContentPane(new SelectNewAlimentPanel(new StdButton()));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
	}
}
