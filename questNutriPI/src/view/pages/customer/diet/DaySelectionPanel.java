/**
 * Package que contém as classes que controlam a visualização da dieta do Customer.
 */
package view.pages.customer.diet;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import utils.view.LanguageUtil;
import view.components.generics.GenericJPanel;

/**
 * Painel para seleção de dias da semana usando checkbox.
 * Cada checkbox representa um dia da semana e pode ser selecionado ou desselecionado.
 */
public class DaySelectionPanel extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
    private JCheckBox[] checkBoxes;
    private final String[] DAYS;
    private static final int[] DAYS_NUMBER = {64, 32, 16, 8, 4, 2, 1};
    
    /**
     * Construtor para inicializar o DaySelectionPanel e um valor inicial de dias selecionados.
     *
     * @param ownerPanel Painel genérico pai que contém este DaySelectionPanel.
     * @param daysOfWeek Valor inteiro representando os dias da semana selecionados, usando a máscara de bits.
     */
	public DaySelectionPanel(GenericJPanel ownerPanel, int daysOfWeek) {
		super(ownerPanel);
		DAYS = new String[] {
                new LanguageUtil("D", "S").get(),
                new LanguageUtil("S", "M").get(),
                new LanguageUtil("T", "T").get(),
                new LanguageUtil("Q", "W").get(),
                new LanguageUtil("Q", "T").get(),
                new LanguageUtil("S", "F").get(),
                new LanguageUtil("S", "S").get()
                };
		ltGridBag();
		setBackground(STD_STRONG_GRAY_COLOR);
		initDaysSelection(daysOfWeek);
	}
	
    /**
     * Inicializa a seleção de dias da semana com base no valor passado.
     *
     * @param daysOfWeek Valor inteiro representando os dias da semana selecionados, usando a máscara de bits.
     */
	private void initDaysSelection(int daysOfWeek) {
        checkBoxes = new JCheckBox[DAYS.length];

        for (int i = 0; i < DAYS.length; i++) {
            JLabel label = new JLabel(DAYS[i]);
            label.setFont(STD_BOLD_FONT);
            label.setForeground(STD_WHITE_COLOR);
            label.setOpaque(true);
            label.setBackground(STD_STRONG_GRAY_COLOR);
            add(label, gbc.grid(i, 0).wgt(1.0).insets("3", 0, 5).anchor("CENTER"));

            JCheckBox checkBox = new JCheckBox();
            checkBox.setPreferredSize(new Dimension(30, 30));
            checkBox.setBackground(STD_STRONG_GRAY_COLOR);
            checkBox.setOpaque(true);
            checkBoxes[i] = checkBox;
            add(checkBox, gbc.yP().insets(5, 12, 5, 5));

            if ((DAYS_NUMBER[i] & daysOfWeek) == DAYS_NUMBER[i]) {
                checkBox.setSelected(true);
            }
        }

    }

    /**
     * Verifica se um dia específico da semana está selecionado.
     *
     * @param dayIndex Índice do dia da semana (0 a 6).
     * @return true se o dia estiver selecionado, false caso contrário.
     * @throws IndexOutOfBoundsException se o índice do dia estiver fora do intervalo válido.
     */
    public boolean isDaySelected(int dayIndex) {
        if (dayIndex < 0 || dayIndex >= checkBoxes.length) {
            throw new IndexOutOfBoundsException("Invalid day index: " + dayIndex);
        }
        return checkBoxes[dayIndex].isSelected();
    }

    /**
     * Define se um dia específico da semana deve ser selecionado ou não.
     *
     * @param dayIndex Índice do dia da semana (0 a 6).
     * @param selected true para selecionar o dia, false para desselecionar.
     * @throws IndexOutOfBoundsException se o índice do dia estiver fora do intervalo válido.
     */
    public void setDaySelected(int dayIndex, boolean selected) {
        if (dayIndex < 0 || dayIndex >= checkBoxes.length) {
            throw new IndexOutOfBoundsException("Invalid day index: " + dayIndex);
        }
        checkBoxes[dayIndex].setSelected(selected);
    }

    /**
     * Obtém o valor de dias da semana selecionados como uma soma dos valores de bit.
     *
     * @return Valor inteiro representando os dias da semana selecionados.
     */
    public int getSelectedDaysValue() {
        int value = 0;
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isSelected()) {
                value += DAYS_NUMBER[i];
            }
        }
        return value;
    }
	
	
}