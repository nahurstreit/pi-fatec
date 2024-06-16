package view.pages.customer.diet;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import utils.view.LanguageUtil;
import view.components.generics.GenericJPanel;

public class DaySelectionPanel extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
    private JCheckBox[] checkBoxes;
    private final String[] DAYS;
    private static final int[] DAYS_NUMBER = {64, 32, 16, 8, 4, 2, 1};
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

    public boolean isDaySelected(int dayIndex) {
        if (dayIndex < 0 || dayIndex >= checkBoxes.length) {
            throw new IndexOutOfBoundsException("Invalid day index: " + dayIndex);
        }
        return checkBoxes[dayIndex].isSelected();
    }

    public void setDaySelected(int dayIndex, boolean selected) {
        if (dayIndex < 0 || dayIndex >= checkBoxes.length) {
            throw new IndexOutOfBoundsException("Invalid day index: " + dayIndex);
        }
        checkBoxes[dayIndex].setSelected(selected);
    }

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