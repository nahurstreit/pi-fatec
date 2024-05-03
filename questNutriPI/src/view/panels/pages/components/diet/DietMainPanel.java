package view.panels.pages.components.diet;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import models.Meal;
import view.panels.pages.components.GenericComponent;

public class DietMainPanel extends GenericComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Meal[] meals;
	private int[] avbDays = {64, 32, 16, 8, 4, 2, 1}; //Variável que controla os dias da semana dos respectivos panels
	private String[] avbStrDays = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};
	public int currentDayFocus = 0;
	
	private final Color BG_COLOR = new Color(103, 103, 103);

	public DietMainPanel(Meal[] meals) {
		super();
		this.meals = meals;
		this.setBackground(BG_COLOR);
		populate(meals);
	}
	
	protected void initGbc() {
		super.initGbc();
        gbc.gridwidth = 1;
		gbc.insets = new Insets(10, 10, 10, 0);
	}
	
	public void swapFocus(int dayToFocus) {
		currentDayFocus = dayToFocus;
		this.populate(meals);
	}
	
	private void populate(Meal[] meals) {
		this.removeAll();		
		for(int i = 0; i < avbDays.length; i++) {
			if(i == avbDays.length - 1) gbc.insets = new Insets(10, 10, 10, 10);
			gbc.gridx = i;
			DietDayPanel dayPanel = new DietDayPanel(meals, avbDays[i], this);
			dayPanel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dayPanel.callFocus();
				}
			});
			this.add(dayPanel, gbc);
			if(this.currentDayFocus != 0) {
				if(avbDays[i] != currentDayFocus) {
					dayPanel.setVisible(false);
				}
			}
		}
		
		this.refresh();
	}
	
	private void refresh() {
		this.revalidate();
		this.repaint();
	}
	
	public String getDayName(int day) {
		for(int i = 0; i < this.avbDays.length; i++) {
			if(day == this.avbDays[i]) return avbStrDays[i];
		}
		return "";
	}
}