package view.frames;

import controller.entities.SubFoodController;
import model.entities.SubFood;
import utils.interfaces.IDoAction;
import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.generics.GenericJFrame;
import view.pages.customer.diet.food.SelectNewAlimentPanel;

public class NewSubFoodFrame extends SubFrame {
	private static final long serialVersionUID = 1L;
	
	private SelectNewAlimentPanel panel;
	private SubFood subFood;
	private IDoAction afterUpdate;
	
	public NewSubFoodFrame(GenericJFrame callerFrame,  SubFood subFood, IDoAction afterUpdate) {
		super(callerFrame, null);
		this.subFood = subFood;
		if(afterUpdate != null) {
			this.afterUpdate = afterUpdate;
		} else {
			this.afterUpdate = () -> {};
		}
		
		setBounds(100, 100, 650, 440);
		setResizable(false);
		setContentPane(panel = new SelectNewAlimentPanel(createBtn()));
	}
	
	public StdButton createBtn() {
		StdButton btn = StdButton.stdBtnConfig(new LanguageUtil("Selecionar Novo Alimento", "Select New Aliment").get());
		btn.setAction(() -> {
			if(panel.getSelectedAliment() != null) {
				subFood.setAliment(panel.getSelectedAliment())
					   .setQuantity(0.0)
					   .setUnityQt("gramas");
				if(SubFoodController.createNewSubFood(subFood)) {
					afterUpdate.execute();
					this.dispose();
				};
			}
		});
		
		return btn;
	}
	
}