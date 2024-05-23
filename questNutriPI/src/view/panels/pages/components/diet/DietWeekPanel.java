package view.panels.pages.components.diet;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import model.entities.Meal;
import view.QuestNutri;
import view.panels.components.GenericComponent;
import view.panels.components.GenericJPanel;

public class DietWeekPanel extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	private List<Meal> meals;
	private int[] avbDays = {64, 32, 16, 8, 4, 2, 1}; //Variável que controla os dias da semana dos respectivos panels
	private String[] avbStrDays = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};
	public DietDayPanel currentDayFocus = null;
	private ArrayList<DietDayPanel> daysPanels = new ArrayList<DietDayPanel>();
	public GenericJPanel holderDays = new GenericJPanel();

	public DietWeekPanel(GenericJPanel ownerPanel, List<Meal> meals) {
		super(ownerPanel);
		this.ltGridBag();
		this.meals = meals;
		this.setBackground(STD_STRONG_GRAY);
		loadDays();
	}
	
	public void swapFocus(DietDayPanel dayToFocus) {
		if(currentDayFocus != null) {
			holderDays.remove(currentDayFocus);
			holderDays.add(currentDayFocus, holderDays.gbc.grid(currentDayFocus.position, 0).fill("VERTICAL").wgt(1.0).insets(0, 5));
			currentDayFocus = dayToFocus;
			if(dayToFocus != null) {
				holderDays.add(currentDayFocus, holderDays.gbc.fill("BOTH").wgt(1.0).insets(0, 5));
			}
		} else {
			if(dayToFocus != null) {
				holderDays.remove(dayToFocus);
				currentDayFocus = dayToFocus;
				holderDays.add(dayToFocus, holderDays.gbc.fill("BOTH").wgt(1.0).insets(0, 5));
			}
		}
		
		this.refreshDays();
	}
	
	private void loadDays() {
	    CountDownLatch latch = new CountDownLatch(avbDays.length); //Contagem de quantas threads precisam ser abertas
	    holderDays.ltGridBag();
	    holderDays.setBackground(STD_NULL_COLOR);
	    
	    Dimension panelsSize = new Dimension(150, QuestNutri.app.getHeight());
	    
	    for (int i = 0; i < avbDays.length; i++) {
	        int dayNum = i;
	        new Thread(() -> {
	        	DietDayPanel day = new DietDayPanel(meals, avbDays[dayNum], this, dayNum);
	        	day.setPreferredSize(panelsSize);
	        	day.setMinimumSize(panelsSize);
	        	day.setMaximumSize(panelsSize);
	            daysPanels.add(day);
	            holderDays.add(day, holderDays.gbc.grid(dayNum, 0).fill("VERTICAL").wgt(1.0).insets(0, 5));
	            latch.countDown();
	        }).start();
	    }

	    try {
	        latch.await();//Executa todas as threads antes de continuar
	        this.add(holderDays, gbc.fill("BOTH").wgt(1.0).insets(10, 5));
	        this.refresh();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
        
	
	private void refreshDays() {
		for(DietDayPanel day: daysPanels) {
			day.setVisible(true);
			if (this.currentDayFocus != null && day != this.currentDayFocus) {
				day.setVisible(false);
			}
			this.refresh();
		}
	}
	
	public String getDayName(int day) {
		for(int i = 0; i < this.avbDays.length; i++) {
			if(day == this.avbDays[i]) return avbStrDays[i];
		}
		return "";
	}
	
	public GenericJPanel getHolderDays() {
		return holderDays;
	}
}