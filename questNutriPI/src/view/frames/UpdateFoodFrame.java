package view.frames;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.entities.FoodController;
import model.entities.Food;
import utils.interfaces.GeneralVisualSettings;
import utils.interfaces.IDoAction;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.generics.GenericJFrame;
import view.components.generics.GenericJPanel;
import view.pages.customer.diet.food.CurrentSelectedAlimentPanel;
import view.pages.customer.diet.food.SelectNewAlimentPanel;

/**
 * Frame para atualização de informações de uma Food.
 */
public class UpdateFoodFrame extends SubFrame implements GeneralVisualSettings {
	private static final long serialVersionUID = 1L;
	
	private static UpdateFoodFrame opened;
	
	private Food food;
	
	private CurrentSelectedAlimentPanel left;
	private SelectNewAlimentPanel right;
	
	private IDoAction afterUpdate;

    /**
     * Construtor para inicializar o frame de atualização de alimento.
     * 
     * @param callerFrame O frame que chamou este frame.
     * @param food        Food a ser atualizada.
     * @param afterUpdate A ação a ser executada após a atualização da Food.
     */
	public UpdateFoodFrame(GenericJFrame callerFrame, Food food, IDoAction afterUpdate) {
		super(callerFrame, null);
		this.food = food;
		this.afterUpdate = afterUpdate;
		initialize();
		setBounds(100, 100, 883, 462);
		setResizable(false);
		initialize();
		if(opened != null) opened.dispose();
		opened = this;
	}
	

	/**
     * Método para inicializar e configurar os componentes do frame.
     */
	private void initialize() {
		GenericJPanel panel = new GenericJPanel().ltGridBag();
		left = new CurrentSelectedAlimentPanel(this, food.aliment, food.quantity); 
		
		panel.add(left, panel.gbc.fill("BOTH").wgt(0.5, 1.0));
		
		//Separador do meio
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		Border border = BorderFactory.createLineBorder(STD_BLUE_COLOR, 1);
		separator.setBorder(border);
		panel.add(separator, panel.gbc.wgt(0, 1.0));
			
		right = new SelectNewAlimentPanel(updateBtn());
		panel.add(right, panel.gbc.wgt(1.0));
		
		this.setContentPane(panel);
	}
	
    /**
     * Método para criar e configurar o botão de atualização da Food.
     * 
     * @return O botão configurado para salvar a Food.
     */
	private StdButton updateBtn() {
		StdButton btn = StdButton.stdBtnConfig(new LanguageUtil("Salvar como Novo Alimento", "Save as New Aliment").get());
		btn.setAction(() -> {
			if(right.getSelectedAliment() == null) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Nenhum alimento selecionado.", "No aliment selected.").get());
			} else {
				try {
					if(FoodController.updateFoodAliment(food, right.getSelectedAliment())) {
						if(afterUpdate != null) afterUpdate.execute();
						QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento atualizado!", "Food updated!").get());
						dispose();
					} else {
						QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível atualizar.", "Food update have failed.").get());
					};
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		return btn;
	}
}
