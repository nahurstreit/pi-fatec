package view.panels.pages.components.diet;

import java.awt.Color;

import javax.swing.JPanel;

import models.Meal;
import view.components.ActionLbl;
import view.panels.components.GenericJPanel;

public class DietDayPanel extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	
	public DietWeekPanel dietMainPanel;
	public int weekDay;
	
	private GenericJPanel nameBox = new GenericJPanel();
	public int position;
	
	private final Color BG_COLOR = new Color(85, 183, 254);

	public DietDayPanel(Meal[] meals, int weekDay, DietWeekPanel dietMainPanel, int position) {
		super(dietMainPanel);
		this.ltGridBag();
		this.weekDay = weekDay;
		this.dietMainPanel = dietMainPanel;
		this.position = position;
		this.setBackground(Color.red);
		populate(meals);
	}
	
	public void populate(Meal[] meals) {
		ActionLbl lblDay = new ActionLbl(dietMainPanel.getDayName(this.weekDay), () -> callFocus());
		lblDay.setForeground(Color.white);
		lblDay.setFont(STD_REGULAR_FONT.deriveFont(15f));
		
		nameBox.ltGridBag().add(lblDay, nameBox.gbc.wgt(1.0).insets(10).grid(1,0).width("REMAINDER"));
		nameBox.setBackground(Color.green);
		
		this.add(nameBox, gbc.grid(0).wgt(1.0, 0).fill("BOTH"));
		
		JPanel test = new JPanel();
		test.setBackground(Color.MAGENTA);
		this.add(test, gbc.wgt(1.0).fill("BOTH").grid(0, 1));
		
//		for(int i = 0; i < meals.length; i++) {
//			this.add(new DietMealPanel(meals[i], this), gbc.insets(10).grid(0, i+1));
//		}
		
		this.refresh();
	}
	
	public void callFocus() {
		ActionLbl lblCloseX = new ActionLbl("X", null);
		lblCloseX.setNewAction(() -> { dropFocus(); nameBox.remove(lblCloseX);}); 
		lblCloseX.setForeground(Color.white);
		lblCloseX.setFont(STD_BOLD_FONT.deriveFont(20f));
		nameBox.add(lblCloseX, nameBox.gbc.grid(0).width(1).wgt(0).insets(10, 40, 10, 0));

		
		this.dietMainPanel.swapFocus(this);
	}
	
	public void dropFocus() {
		this.dietMainPanel.swapFocus(null);
	}
}