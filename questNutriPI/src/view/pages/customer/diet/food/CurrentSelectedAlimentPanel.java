/**
 * Package que contém as classes que controlam a visualização de Foods da dieta de um Customer.
 */
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

/**
 * Painel que exibe as informações detalhadas de um alimento selecionado atualmente,
 * incluindo seu nome e informações nutricionais em uma tabela.
 */
public class CurrentSelectedAlimentPanel extends GenericJPanel {
    private static final long serialVersionUID = 1L;

    private JScrollPane scrollPaneSelectedAlimentInfo;
    private BreakActionLbl selectedAliment;
    private JTable table;

    private Aliment aliment;
    private double quantity;

    /**
     * Construtor para inicializar o CurrentSelectedAlimentPanel com o frame que o chamou, um alimento e uma quantidade.
     *
     * @param callerFrame Frame genérico chamador que contém este painel.
     * @param aliment     Objeto Aliment representando o alimento selecionado.
     * @param quantity    Quantidade do alimento selecionado.
     */
    public CurrentSelectedAlimentPanel(GenericJFrame callerFrame, Aliment aliment, Double quantity) {
    	setCallerFrame(callerFrame);
        ltGridBag();
        this.aliment = aliment;
        this.quantity = quantity;

        JLabel comparisonLbl = new JLabel(new LanguageUtil("Janela de Comparação", "Comparison Window").get());
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

    /**
     * Inicializa a tabela de informações nutricionais do alimento com base no objeto Aliment e na quantidade.
     */
    public void init() {
        table = new JTable(new AlimentNutritionalTable(aliment, quantity));
        scrollPaneSelectedAlimentInfo.setViewportView(table);
    }

}
