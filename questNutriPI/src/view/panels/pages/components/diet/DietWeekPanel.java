package view.panels.pages.components.diet;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import model.entities.Meal;
import view.QuestNutri;
import view.panels.components.GenericComponent;
import view.panels.components.GenericJPanel;
import view.utils.LanguageUtil;
import view.utils.VUtils;

public class DietWeekPanel extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	private List<Meal> meals;
	private int[] avbDays = {64, 32, 16, 8, 4, 2, 1}; //Variável que controla os dias da semana dos respectivos panels
	private String[] avbStrDays;
	public DietDayPanel currentDayFocus = null;
	private ArrayList<DietDayPanel> daysPanels = new ArrayList<DietDayPanel>();
	public GenericJPanel holderDays = new GenericJPanel();
	private CountDownLatch latch = new CountDownLatch(avbDays.length); //Contagem de quantas threads precisam ser abertas
	
	public GenericJPanel holderLoading = new GenericJPanel().setBGColor(STD_WHITE_COLOR);

	public DietWeekPanel(GenericJPanel ownerPanel, List<Meal> meals) {
		super(ownerPanel);
		this.ltGridBag();
		this.meals = meals;
		this.setBackground(Color.white);
		
		//Inicialização do array de dias da semana.
        avbStrDays = new String[] {
                new LanguageUtil("Domingo", "Sunday").get(),
                new LanguageUtil("Segunda", "Monday").get(),
                new LanguageUtil("Terça", "Tuesday").get(),
                new LanguageUtil("Quarta", "Wednesday").get(),
                new LanguageUtil("Quinta", "Thursday").get(),
                new LanguageUtil("Sexta", "Friday").get(),
                new LanguageUtil("Sábado", "Saturday").get()
            };
		
        VUtils.applyLoadingGif(holderLoading, 30, 30, true, STD_BOLD_FONT.deriveFont(15f), 0, 5);
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
	    holderDays.ltGridBag();
	    holderDays.setBackground(STD_NULL_COLOR);
	    
	    Dimension panelsSize = new Dimension(150, QuestNutri.app.getHeight());
	    this.add(holderLoading, gbc.anchor("CENTER"));
	    
	    for (int i = 0; i < avbDays.length; i++) {
	        	DietDayPanel day = new DietDayPanel(avbDays[i], this, i);
	        	day.setPreferredSize(panelsSize);
	        	day.setMinimumSize(panelsSize);
	        	day.setMaximumSize(panelsSize);
	            daysPanels.add(day);
	            holderDays.add(day, holderDays.gbc.grid(i, 0).fill("VERTICAL").wgt(1.0).insets(0, 5));
	            latch.countDown();
	            
	            new Thread(() -> {
	            	for(int j = 0; j < meals.size(); j++) {
	            		if((meals.get(j).daysOfWeek & day.weekDay) == day.weekDay) {
	            			day.setMeals(meals.get(j));
	            		}
	            	}
	            }).start();
	    }

	    new Thread(() -> {
            try {
                latch.await(); //Espera todas as threads terminarem
                for(DietDayPanel day: daysPanels) day.init();
                javax.swing.SwingUtilities.invokeLater(() -> {
                	this.remove(holderLoading);
                	this.add(holderDays, gbc.fill("BOTH").wgt(1.0).insets(10, 5));
                    this.refresh();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            refreshDays();
        }).start();
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