package view.frames;

import java.awt.Dimension;

import model.entities.Food;
import utils.interfaces.IDoAction;
import utils.view.LanguageUtil;
import view.components.generics.GenericJFrame;
import view.pages.customer.diet.food.FoodInfoPanel;

/**
 * Frame para gerenciamento de informações de Foods.
 * Estende SubFrame para manter a consistência visual e funcional do aplicativo.
 */
public class FoodInfoFrame extends SubFrame {
	private static final long serialVersionUID = 1L;
	
    // Referência estática para o último FoodInfoFrame aberto
	private static FoodInfoFrame opened;

    /**
     * Construtor para FoodInfoFrame.
     * 
     * @param callerFrame frame chamador, pode ser nulo se não houver frame chamador
     * @param food objeto Food representando o alimento a ser gerenciado
     * @param afterUpdate ação a ser executada após atualização do alimento
     */
	public FoodInfoFrame(GenericJFrame callerFrame, Food food, IDoAction afterUpdate) {
		super(callerFrame, new Dimension(1000, 500));
		setFrameName("Food frame");
		setTitle(new LanguageUtil("Gerenciamento de alimento", "Food management").get());
		setContentPane(new FoodInfoPanel(null, this, food, afterUpdate));
		if(opened != null) opened.dispose();
		opened = this;
	}

}