package view.frames;

import javax.swing.JFrame;

import model.entities.Food;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.pages.customer.diet.food.SelectNewAlimentPanel;

public class NewFoodFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private SelectNewAlimentPanel panel;
	private Food food;
	
	public NewFoodFrame(Food food) {
		this.food = food;
		setBounds(100, 100, 650, 440);
		setResizable(false);
		setContentPane(panel = new SelectNewAlimentPanel(createBtn()));
	}
	
	public StdButton createBtn() {
		StdButton btn = StdButton.stdBtnConfig(new LanguageUtil("Selecionar Novo Alimento", "Select New Aliment").get());
		btn.setAction(() -> {
			if(panel.getSelectedAliment() != null) {
				food.setAliment(panel.getSelectedAliment());
				QuestNutriJOP.showMessageDialog(null, "Alimento selecionado: "+food.aliment);
			}
		});
		
		return btn;
	}
	
}