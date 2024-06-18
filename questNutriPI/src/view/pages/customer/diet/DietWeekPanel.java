package view.pages.customer.diet;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import model.entities.Customer;
import model.entities.Meal;
import utils.interfaces.IDoAction;
import utils.view.LanguageUtil;
import utils.view.PanelsUtil;
import view.QuestNutri;
import view.components.generics.GenericComponent;
import view.components.generics.GenericJPanel;

/**
 * Painel semanal de dieta de um cliente.
 */
public class DietWeekPanel extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	private List<Meal> meals;
	private int[] avbDays = {64, 32, 16, 8, 4, 2, 1}; //Variável que controla os dias da semana dos respectivos panels
	private String[] avbStrDays;
	public DietDayPanel currentDayFocus = null;
	private ArrayList<DietDayPanel> daysPanels = new ArrayList<DietDayPanel>();
	public GenericJPanel holderDays = new GenericJPanel();
	private CountDownLatch latch = new CountDownLatch(avbDays.length); //Contagem de quantas threads precisam ser abertas
	
	public GenericJPanel holderLoading = new GenericJPanel().setBGColor(STD_WHITE_COLOR);

    /**
     * Construtor do painel semanal de dieta.
     *
     * @param ownerPanel Painel proprietário onde este painel será exibido.
     * @param customer   Cliente cuja dieta será exibida.
     */
	public DietWeekPanel(GenericJPanel ownerPanel, Customer customer) {
		super(ownerPanel);
		this.customer = customer;
		this.meals = customer.getDiet();
		this.ltGridBag();
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
		
        PanelsUtil.applyLoadingGif(holderLoading, 30, 30, true, STD_BOLD_FONT.deriveFont(15f), 0, 5);
		loadDays();
	}
	
    /**
     * Método para trocar o foco para um determinado dia.
     *
     * @param dayToFocus Dia da dieta para o qual o foco deve ser trocado.
     */
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
	
    /**
     * Obtém as refeições mais recentes do cliente.
     *
     * @return Lista de refeições do cliente.
     */
	public List<Meal> getRecentMeal() {
		this.meals = customer.getDiet();
		return this.meals;
	}
	
    /**
     * Carrega os dias da semana no painel.
     */
	private void loadDays() {
	    holderDays.ltGridBag();
	    holderDays.setBackground(STD_NULL_COLOR);
	    this.add(holderLoading, gbc.anchor("CENTER"));
	    
	    createDaysPanels();
	    distributeMeals();
	    
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
	
    /**
     * Cria os painéis dos dias da semana.
     */
	private void createDaysPanels() {
		Dimension panelsSize = new Dimension(150, QuestNutri.app.getHeight());
		latch = new CountDownLatch(avbDays.length);
		for(int i = 0; i < avbDays.length; i++) {
        	DietDayPanel day = new DietDayPanel(this, avbDays[i], i, customer);
        	day.setPreferredSize(panelsSize);
        	day.setMinimumSize(panelsSize);
        	day.setMaximumSize(panelsSize);
            daysPanels.add(day);
            holderDays.add(day, holderDays.gbc.grid(i, 0).fill("VERTICAL").wgt(1.0).insets(0, 5));
            latch.countDown();
		}
	}
	
    /**
     * Distribui as refeições nos respectivos dias da semana.
     *
     * @param afterDistribute Ação a ser executada após a distribuição das refeições.
     */
	private void distributeMeals(IDoAction afterDistribute) {
        for(DietDayPanel day: daysPanels) {
        	day.resetMeals();
            new Thread(() -> {
            	if(meals != null) {
                    for(Meal meal: meals) {
                        if ((meal.daysOfWeek & day.weekDay) == day.weekDay) {
                            day.setMeals(meal);
                        }
                    }
                    day.createMeals();
                    day.updateDayKcal();
                    if(afterDistribute != null) afterDistribute.execute();
            	}
            }).start();
        }
    }
	
    /**
     * Distribui as refeições nos dias da semana.
     */
	private void distributeMeals() {
		distributeMeals(null);
	};
	
	
    /**
     * Atualiza as refeições de um dia da semana.
     *
     * @param callerDay Dia da semana que chamou a atualização.
     * @param runAfter   Ações a serem executadas após a atualização.
     */
	public void updateMeals(DietDayPanel callerDay, IDoAction ...runAfter) {
		getRecentMeal();
		distributeMeals(() -> {
			if(callerDay != null) {
				callerDay.expandAllMeals();
				for(IDoAction action: runAfter) {
					action.execute();
				}
			}
		});
		
		this.refresh();
	}
	
    /**
     * Atualiza a visibilidade dos dias da semana.
     */
	private void refreshDays() {
		for(DietDayPanel day: daysPanels) {
			day.setVisible(true);
			if (this.currentDayFocus != null && day != this.currentDayFocus) {
				day.setVisible(false);
			}
			this.refresh();
		}
	}
	
    /**
     * Obtém o nome de um dia da semana.
     *
     * @param day Dia da semana.
     * @return Nome do dia da semana.
     */
	public String getDayName(int day) {
		for(int i = 0; i < this.avbDays.length; i++) {
			if(day == this.avbDays[i]) return avbStrDays[i];
		}
		return "";
	}
	
    /**
     * Obtém o painel que contém os dias da semana.
     *
     * @return Painel  que contém os dias da semana.
     */
	public GenericJPanel getHolderDays() {
		return holderDays;
	}
	
    /**
     * Obtém o cliente associado ao painel de dieta.
     *
     * @return Cliente associado ao painel de dieta.
     */
	public Customer getCustomer() {
		return customer;
	}
	
}