package view.frames;

import controller.entities.FoodController;
import model.entities.Food;
import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.generics.GenericJFrame;
import view.pages.customer.diet.DietMealPanel;
import view.pages.customer.diet.food.SelectNewAlimentPanel;

/**
 * Frame utilizado para criar um novo alimento na dieta do cliente.
 * Este frame exibe um painel para selecionar um novo alimento e um botão para confirmar a seleção.
 */
public class NewFoodFrame extends SubFrame {
	private static final long serialVersionUID = 1L;
	
	private SelectNewAlimentPanel panel;
	private Food food;
	private DietMealPanel dietMealPanel;
	
    /**
     * Construtor da classe NewFoodFrame.
     *
     * @param callerFrame O frame que chamou este frame.
     * @param dietMealPanel O painel de refeição da dieta ao qual o alimento será adicionado.
     * @param food O alimento que será criado ou atualizado.
     */
	public NewFoodFrame(GenericJFrame callerFrame, DietMealPanel dietMealPanel,  Food food) {
		super(dietMealPanel.getCallerFrame(), null);
		this.dietMealPanel = dietMealPanel; 
		this.food = food;
		setBounds(100, 100, 650, 440);
		setResizable(false);
		setContentPane(panel = new SelectNewAlimentPanel(createBtn()));
	}
	
    /**
     * Cria e configura o botão de seleção de novo alimento.
     *
     * @return O botão configurado para selecionar um novo alimento.
     */
	public StdButton createBtn() {
		StdButton btn = StdButton.stdBtnConfig(new LanguageUtil("Selecionar Novo Alimento", "Select New Aliment").get());
		btn.setAction(() -> {
			if(panel.getSelectedAliment() != null) {
				food.setAliment(panel.getSelectedAliment())
					.setQuantity(0.0)
					.setUnityQt("gramas");
				if(FoodController.createNewFood(food)) {
					dietMealPanel.foodWasUpdated();
					this.dispose();
				};
			}
		});
		
		return btn;
	}
	
}