package view.pages.customer.diet.food;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.entities.Aliment;
import utils.view.LanguageUtil;
import view.components.generics.GenericJFrame;
import view.components.generics.GenericJPanel;
import view.components.labels.BreakActionLbl;
import view.components.tables.AlimentNutritionalTable;

public class CurrentSelectedAlimentPanel extends GenericJPanel {
    private static final long serialVersionUID = 1L;

    private JScrollPane scrollPaneSelectedAlimentInfo;
    private BreakActionLbl selectedAliment;
    private JTable table;

    private Aliment aliment;
    private double quantity;

    public CurrentSelectedAlimentPanel(GenericJFrame callerFrame, Aliment aliment, Double quantity) {
    	setCallerFrame(callerFrame);
        ltGridBag();
        this.aliment = aliment;
        this.quantity = quantity;

        JLabel comparisonLbl = new JLabel("Janela de Comparação");
        comparisonLbl.setFont(STD_BOLD_FONT.deriveFont(18f));
        this.add(comparisonLbl, gbc.grid(0).fill("BOTH").wgt(1.0, 0).anchor("NORTHWEST").insets(20, 20, 15, 20));

        GenericJPanel selectedAlimentPanel = new GenericJPanel().ltGridBag();
        this.add(selectedAlimentPanel, gbc.yP().insets(0, 20, 10, 20));

        JLabel selectedAlimentUpperLbl = new JLabel(new LanguageUtil("Selecionado atualmente", "Currently selected").get());
        selectedAlimentUpperLbl.setFont(STD_MEDIUM_FONT.deriveFont(10f));

        selectedAlimentPanel.add(selectedAlimentUpperLbl, selectedAlimentPanel.gbc.fill("HORIZONTAL").grid(0).wgt(1.0, 0).anchor("NORTHWEST"));

        // Label superior do lado direito que diz qual é o aliemnto selecionado atualmente.
        selectedAliment = new BreakActionLbl()
                .setUpText(aliment.name)
                .setUpFont(STD_BOLD_FONT.deriveFont(12f))
                .turnHoverOff()
                .setBGColor(this.getBackground());

        selectedAlimentPanel.add(selectedAliment, selectedAlimentPanel.gbc.fill("BOTH").grid(0, 1).wgt(1.0).anchor("NORTHWEST"));

        scrollPaneSelectedAlimentInfo = new JScrollPane();
        this.add(scrollPaneSelectedAlimentInfo, gbc.yP().fill("BOTH").wgt(1.0).insets(0, 20, 20, 20));

        // Inicializando a tabela
        init();
    }

    public void init() {
        table = new JTable(new AlimentNutritionalTable(aliment, quantity));
        scrollPaneSelectedAlimentInfo.setViewportView(table);
    }

}
