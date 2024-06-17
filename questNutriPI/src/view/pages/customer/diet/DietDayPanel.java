package view.pages.customer.diet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.entities.Meal;
import utils.FoodUtil;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.generics.GenericJPanel;
import view.components.labels.ActionLbl;
import view.components.utils.IDoAction;

public class DietDayPanel extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	public DietWeekPanel dietWeekPanel;
	public int weekDay;
	public int position;
	
	private GenericJPanel nameBox = new GenericJPanel().ltGridBag().setBGColor(STD_BLUE_COLOR);
	private GenericJPanel mealsGeneralPanel = new GenericJPanel().ltGridBag().setBGColor(STD_LIGHT_GRAY_COLOR);
	
	private JScrollPane mealsScrollPane;
	
	private ActionLbl lblDay;
	
	public boolean isOnFocus = false;
	
	private List<DietMealPanel> mealsCards;
	
	private ActionLbl exitLbl;
	private StdButton creatMealBtn;
	
	private List<Meal> meals = new ArrayList<Meal>();
	
	private GenericJPanel kcalBox = new GenericJPanel().ltGridBag().setBGColor(STD_BLUE_COLOR);
	private JLabel kcalLbl;
	
	private static final int BETWEEN_DISTANCE = 10;
	private static final int LATERAL_DISTANCE = 10;
	
	private DietMealPanel caller = null;

	public DietDayPanel(DietWeekPanel dietWeekPanel, int weekDay, int position) {
	    super(dietWeekPanel);
	    this.ltGridBag();
	    this.weekDay = weekDay;
	    this.dietWeekPanel = dietWeekPanel;
	    this.position = position;
	    this.setBackground(STD_LIGHT_GRAY_COLOR);
	    
	    meals = new ArrayList<>();
	    initializeComponents(); // Chama o método para inicializar os componentes visuais
	}

	private void initializeComponents() {
	    nameBox = new GenericJPanel().ltGridBag().setBGColor(STD_BLUE_COLOR);
	    mealsGeneralPanel = new GenericJPanel().ltGridBag().setBGColor(STD_LIGHT_GRAY_COLOR);
	    kcalBox = new GenericJPanel().ltGridBag().setBGColor(STD_BLUE_COLOR);
	    
	    exitLbl = new ActionLbl("X").setUpFont(STD_BOLD_FONT.deriveFont(15f))
	                                .setUpColor(Color.white)
	                                .setNewAction(this::dropFocus);
	    
	    creatMealBtn = new StdButton(new LanguageUtil("Criar Refeição", "Add Meal").get())
	                                .setUpFont(STD_BOLD_FONT.deriveFont(12f))
	                                .setColors(STD_BLUE_COLOR, STD_WHITE_COLOR)
	                                .setAction(this::createMeal);
	}
	
	private void createMeal() {
	    try {
	        Meal newMeal = new Meal().setCustomer(dietWeekPanel.getCustomer())
	                            .setDaysOfWeek(weekDay)
	                            .setHour("00:00")
	                            .setName("Nova Refeição");
	        
	        newMeal.save();
	        
	        //Adiciona a nova Meal à lista de Meals deste DietDayPanel
	        meals.add(0, newMeal);
	        
	        //Limpa e recria todos os cartões de refeição
	        mealsGeneralPanel.removeAll();
	        createMeals(); //Recria os cartões de refeição
	        expandAllMeals();
	        
	        mealsGeneralPanel.refresh();
	        
	        //Força a revalidação e a repintura do painel
	        revalidate();
	        repaint();
	        rollTo(null);
	        
	    } catch (Exception e) {
	        QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível criar uma nova refeição.", "Unable to create new meal.").get());
	    }
	}
	
	public DietDayPanel init() {
		calculateDayKcal();
		populate(meals);
		return this;
	}
	
	public void resetMeals() {
		try {
			meals.clear();
		} catch (Exception e) {}
		
		try {
			mealsCards.clear();
		} catch (Exception e) {}
		
		try {
			mealsGeneralPanel.removeAll();
		} catch (Exception e) {}
		
		this.refresh();
	}
	
	public DietDayPanel setMeals(Meal ...meals) {
		for(Meal meal: meals) {
			this.meals.add(meal);
		}
		return this;
	}
	
	
	public void mealWasUpdated(IDoAction ...runAfter) {
	    dietWeekPanel.updateMeals(this, runAfter);
	}
	
	public void callUpdate() {
		meals = new ArrayList<Meal>();
		for(Meal meal : dietWeekPanel.getRecentMeal()) {
			setMeals(meal);
		}
	}
 	
	public double calculateDayKcal() {
		double total = 0.0;
		try {
			for(Meal meal: meals) {
				if((meal.daysOfWeek & weekDay) == weekDay) {
					total += FoodUtil.calculateMealKcal(meal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return total;
	}
	
	public void updateDayKcal() {
		try {
			kcalLbl.setText(String.format("%.2f", calculateDayKcal()) + " kcal");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void populate(List<Meal> meals) {        
		lblDay = new ActionLbl(dietWeekPanel.getDayName(this.weekDay), () -> callFocus(), JLabel.CENTER);
		lblDay.setForeground(Color.white);
		lblDay.setFont(STD_BOLD_FONT.deriveFont(15f));
		nameBoxOffFocus();
		this.add(nameBox, gbc.grid(0).wgt(1.0, 0).fill("BOTH"));
		
		kcalLbl = new JLabel(String.format("%.2f", calculateDayKcal()) + " kcal", JLabel.CENTER);
		kcalLbl.setForeground(STD_WHITE_COLOR);
		kcalLbl.setFont(STD_BOLD_FONT.deriveFont(12f));
		
		kcalBox.add(kcalLbl, kcalBox.gbc.fill("BOTH").wgt(1.0));
		kcalBox.setMaximumSize(new Dimension(100, 20));
		kcalBox.setMinimumSize(new Dimension(100, 20));
		kcalBox.setPreferredSize(new Dimension(100, 20));
		this.add(kcalBox, gbc.wgt(1.0, 0).fill("HORIZONTAL").yP());
		this.add(mealsGeneralPanel, gbc.wgt(1.0).fill("BOTH").yP());
		
		this.refresh();
	}
	
	public void createMeals() {        
	    mealsCards = new ArrayList<DietMealPanel>();
	    for(int i = 0; i < meals.size(); i++) {
	        DietMealPanel mealCard = new DietMealPanel(this, meals.get(i));
	        mealsCards.add(mealCard);
	        mealsGeneralPanel.add(mealCard, mealsGeneralPanel.gbc.insets(BETWEEN_DISTANCE, LATERAL_DISTANCE).grid(0, i+1).anchor("NORTHWEST").fill("BOTH").wgt(1.0));
	    }
	    
	    mealsGeneralPanel.refresh();
	    this.refresh();
	}
	
	public void callFocus() {
		isOnFocus = true;
		lblDay.setNewAction(() -> dropFocus());
		caller = null; //Redefine o valor do painel de meal que chamou o foco.
		
		swapPanel();

        nameBoxOnFocus();
        this.remove(kcalBox);
        this.remove(mealsGeneralPanel);
        this.add(mealsScrollPane, gbc.wgt(1.0).fill("BOTH").grid(0, 1).insets(15));
		
		this.dietWeekPanel.swapFocus(this);
	}
	
	private void swapPanel() {
		expandAllMeals();
        mealsScrollPane = new JScrollPane(mealsGeneralPanel);
        mealsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mealsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mealsScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0)); //Remove as bordas do ScrollPane
        mealsScrollPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                mealsScrollPane.getVerticalScrollBar().setValue(caller != null? caller.getY() : 0);
                refresh();
            }
        });
        
        mealsScrollPane.revalidate();
        mealsScrollPane.repaint();
	}
	
	/**
	 * Método para chamar o foco do dia na visualização de uma refeição.
	 * <br>Esse método recebe um mealPanel para rolar a visualização do JPanel até o meal que chamou o foco.
	 * @param mealPanel - Objeto mealPanel que chamou o foco.
	 */
	public void callFocus(DietMealPanel mealPanel) {
		callFocus();
		
		if(mealsCards.indexOf(mealPanel) != -1) caller = mealPanel;
	}
	
	public void dropFocus() {
		isOnFocus = false;
		lblDay.setNewAction(() -> callFocus());
		nameBoxOffFocus();
		this.add(kcalBox, kcalBox.gbc.fill("HORIZONTAL").wgt(1.0, 0).grid(0, 1));
		
		for(DietMealPanel mealCard: mealsCards) mealCard.retractMeal();
		this.remove(mealsScrollPane);
        this.add(mealsGeneralPanel, gbc.wgt(1.0).fill("BOTH").grid(0, 2).insets());
		this.dietWeekPanel.swapFocus(null);
		this.dietWeekPanel.updateMeals(null);
		updateDayKcal();
	}
	
	private void nameBoxOnFocus() {
		int focusPadding = 15;
		
		nameBox.remove(lblDay);
		
		nameBox.gbc.wgt(1.0).grid(0).fill("VERTICAL");
		
		nameBox.add(exitLbl,
					nameBox.gbc.anchor("WEST")
							   .insets(focusPadding, focusPadding, focusPadding, 0)
					);
		
		nameBox.add(creatMealBtn, 
					nameBox.gbc.anchor("EAST")
							   .insets(focusPadding, 0, focusPadding, focusPadding)
					);
		
		nameBox.add(lblDay, 
					nameBox.gbc.anchor("CENTER")
						   .insets(focusPadding, 0)
					);
	}
	
	public void expandAllMeals() {
		try {
			for(DietMealPanel mealCard: mealsCards) mealCard.expandMeal();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void nameBoxOffFocus() {
		try {
			nameBox.remove(exitLbl);
			nameBox.remove(lblDay);
			nameBox.remove(creatMealBtn);
		} catch (Exception e) {
		}
		
		nameBox.add(lblDay, nameBox.gbc.wgt(1.0).insets(10).grid(0).fill("BOTH"));
	}
	
	public void dropMeals() {
		mealsGeneralPanel.removeAll();
		mealsCards.clear();
		populate(meals);
		this.refresh();
	}
	
	public void rollTo(DietMealPanel mealPanel) {
		if(mealPanel != null) {
			mealsScrollPane.getVerticalScrollBar().setValue(mealPanel.getY());
		} else {
			mealsScrollPane.getVerticalScrollBar().setValue(0);
		}
        
        mealsScrollPane.revalidate();
        mealsScrollPane.repaint();
        refresh();
	}
	
	
	public DietMealPanel findMealPosition(Meal meal) {
	    for (DietMealPanel mealPanel : mealsCards) {
	        if(mealPanel.hasThisMeal(meal)) {
	            return mealPanel;
	        }
	    }
	    return null;
	}
} 