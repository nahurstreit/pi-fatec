package view.panels.pages.components.diet;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.entities.Meal;
import utils.FoodUtil;
import view.components.ActionLbl;
import view.components.QuestNutriJOP;
import view.components.StdButton;
import view.panels.components.GenericJPanel;
import view.utils.LanguageUtil;

public class DietDayPanel extends GenericJPanel {
	private static final long serialVersionUID = 1L;
	
	
	public DietWeekPanel dietMainPanel;
	public int weekDay;
	public int position;
	
	private GenericJPanel nameBox = new GenericJPanel().ltGridBag().setBGColor(STD_BLUE_COLOR);
	private GenericJPanel mealsGeneralPanel = new GenericJPanel().ltGridBag().setBGColor(STD_LIGHT_GRAY);
	private JScrollPane mealsScrollPane;
	
	private ActionLbl lblDay;
	
	public boolean isOnFocus = false;
	
	private List<DietMealPanel> mealsCards;
	
	private ActionLbl exitLbl;
	private StdButton creatMealBtn;
	
	private List<Meal> meals;
	
	private GenericJPanel kcalBox = new GenericJPanel().ltGridBag().setBGColor(STD_BLUE_COLOR);
	private JLabel kcalLbl;

	public DietDayPanel(List<Meal> meals, int weekDay, DietWeekPanel dietMainPanel, int position) {
		super(dietMainPanel);
		this.ltGridBag();
		this.meals = meals;
		this.weekDay = weekDay;
		this.dietMainPanel = dietMainPanel;
		this.position = position;
		this.setBackground(STD_LIGHT_GRAY);
		calculateDayKcal();
		populate(meals);
		
		exitLbl = new ActionLbl("X").setUpFont(STD_BOLD_FONT.deriveFont(15f))
									.setUpColor(Color.white)
									.setNewAction(() -> dropFocus());
		
		creatMealBtn = new StdButton(new LanguageUtil("Criar Refeição", "Add Meal").get())
									.setUpFont(STD_BOLD_FONT.deriveFont(12f))
									.setColors(STD_BLUE_COLOR, STD_WHITE_COLOR)
									.setAction(() -> {
										QuestNutriJOP.showMessageDialog(null, "Criar refeição");
									});
	}
	
	private void calculateDayKcal() {
		double total = 0.0;
		for(Meal meal: meals) {
			if((meal.daysOfWeek & weekDay) == weekDay) {
				total += FoodUtil.calculateMealKcal(meal);
			}
		}
		
		kcalLbl = new JLabel(String.format("%.2f", total) + " kcal", JLabel.CENTER);
		kcalLbl.setForeground(STD_WHITE_COLOR);
		kcalLbl.setFont(STD_BOLD_FONT.deriveFont(12f));
	}
	
	public void populate(List<Meal> meals) {        
		lblDay = new ActionLbl(dietMainPanel.getDayName(this.weekDay), () -> callFocus(), JLabel.CENTER);
		lblDay.setForeground(Color.white);
		lblDay.setFont(STD_BOLD_FONT.deriveFont(15f));
		nameBoxOffFocus();
		this.add(nameBox, gbc.grid(0).wgt(1.0, 0).fill("BOTH"));
		
		kcalBox.add(kcalLbl, kcalBox.gbc.fill("BOTH").wgt(1.0));
		kcalBox.setMaximumSize(new Dimension(100, 20));
		kcalBox.setMinimumSize(new Dimension(100, 20));
		kcalBox.setPreferredSize(new Dimension(100, 20));
		this.add(kcalBox, gbc.wgt(1.0, 0).fill("HORIZONTAL").yP());
		
		this.add(mealsGeneralPanel, gbc.wgt(1.0).fill("BOTH").yP());
		
		mealsCards = new ArrayList<DietMealPanel>();
		for(int i = 0; i < meals.size(); i++) {
			DietMealPanel mealCard = new DietMealPanel(meals.get(i), this);
			mealsCards.add(mealCard);
			mealsGeneralPanel.add(mealCard, mealsGeneralPanel.gbc.insets(10).grid(0, i+1).anchor("NORTHWEST").fill("BOTH").wgt(1.0));
		}
		
		this.refresh();
	}
	
	public boolean isOnFocus() {
		return isOnFocus;
	}
	
	public void callFocus() {
		isOnFocus = true;
		lblDay.setNewAction(() -> dropFocus());
		for(DietMealPanel mealCard: mealsCards) mealCard.expandMeal();
        mealsScrollPane = new JScrollPane(mealsGeneralPanel);
        mealsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mealsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mealsScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0)); //Remove as bordas do ScrollPane

        nameBoxOnFocus();
        this.remove(kcalBox);
        this.remove(mealsGeneralPanel);
        this.add(mealsScrollPane, gbc.wgt(1.0).fill("BOTH").grid(0, 1).insets(15));
		
		this.dietMainPanel.swapFocus(this);
	}
	
	public void dropFocus() {
		isOnFocus = false;
		lblDay.setNewAction(() -> callFocus());
		nameBoxOffFocus();
		
		
		this.add(kcalBox, kcalBox.gbc.fill("HORIZONTAL").wgt(1.0, 0).grid(0, 1));
		
		
		for(DietMealPanel mealCard: mealsCards) mealCard.retractMeal();
		this.remove(mealsScrollPane);
        this.add(mealsGeneralPanel, gbc.wgt(1.0).fill("BOTH").grid(0, 2).insets());
		this.dietMainPanel.swapFocus(null);
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
	
	private void nameBoxOffFocus() {
		try {
			nameBox.remove(exitLbl);
			nameBox.remove(lblDay);
			nameBox.remove(creatMealBtn);
		} catch (Exception e) {
		}
		
		nameBox.add(lblDay, nameBox.gbc.wgt(1.0).insets(10).grid(0).fill("BOTH"));
	}
} 