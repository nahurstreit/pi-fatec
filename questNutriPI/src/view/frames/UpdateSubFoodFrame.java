package view.frames;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.entities.SubFoodController;
import model.entities.SubFood;
import utils.interfaces.GeneralVisualSettings;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.generics.GenericJFrame;
import view.components.generics.GenericJPanel;
import view.components.utils.IDoAction;
import view.pages.customer.diet.food.CurrentSelectedAlimentPanel;
import view.pages.customer.diet.food.SelectNewAlimentPanel;

public class UpdateSubFoodFrame extends SubFrame implements GeneralVisualSettings {
	private static final long serialVersionUID = 1L;
	
	private static UpdateSubFoodFrame opened;
	
	private SubFood subFood;
	
	private CurrentSelectedAlimentPanel left;
	private SelectNewAlimentPanel right;
	
	private IDoAction afterUpdate;

	public UpdateSubFoodFrame(GenericJFrame callerFrame, SubFood subFood, IDoAction afterUpdate) {
		super(callerFrame, null);
		this.subFood = subFood;
		this.afterUpdate = afterUpdate;
		initialize();
		setBounds(100, 100, 883, 462);
		setResizable(false);
		initialize();
		if(opened != null) opened.dispose();
		opened = this;
	}
	

	private void initialize() {
		GenericJPanel panel = new GenericJPanel().ltGridBag();
		left = new CurrentSelectedAlimentPanel(this, subFood.aliment, subFood.quantity); 
		
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
	
	private StdButton updateBtn() {
		StdButton btn = StdButton.stdBtnConfig(new LanguageUtil("Salvar como Novo Alimento", "Save as New Aliment").get());
		btn.setAction(() -> {
			if(right.getSelectedAliment() == null) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Nenhum alimento selecionado.", "No aliment selected.").get());
			} else {
				try {
					if(SubFoodController.updateSubFoodAliment(subFood, right.getSelectedAliment())) {
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
