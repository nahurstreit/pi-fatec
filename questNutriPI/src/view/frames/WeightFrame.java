package view.frames;

import java.awt.Color;

import controller.entities.WeightController;
import model.entities.Customer;
import model.entities.Weight;
import utils.validations.Validate;
import utils.validations.ValidationRule;
import utils.view.LanguageUtil;
import view.QuestNutri;
import view.components.QuestNutriJOP;
import view.components.buttons.StdButton;
import view.components.forms.FormBoxInput;
import view.components.forms.FormSection;
import view.components.generics.GenericJFrame;
import view.components.generics.GenericJPanel;
import view.components.generics.GenericJScrollPaneList;

/**
 * Frame para gerenciamento de pesos de clientes.
 */
public class WeightFrame extends SubFrame {
	private static final long serialVersionUID = 1L;

	private Customer customer;
	private GenericJPanel framePanel;
	private GenericJScrollPaneList<Weight> weightList;
	
	private FormBoxInput updateInput;
	
	private FormBoxInput wgtValue;
	
	private static WeightFrame opened;
	
    /**
     * Construtor para inicializar o frame de gerenciamento de pesos.
     * 
     * @param callerFrame O frame que chamou este frame.
     * @param customer    O cliente para o qual os pesos estão sendo gerenciados.
     * @param updateInput O input para atualização dos pesos no frame anterior.
     */
	public WeightFrame(GenericJFrame callerFrame, Customer customer, FormBoxInput updateInput) {
		super(callerFrame, null);
		this.customer = customer;
		this.updateInput = updateInput;
		
		framePanel = new GenericJPanel().ltGridBag();
		this.add(framePanel);
		
		framePanel.setBackground(Color.white);
		
		int x = QuestNutri.app.getX() + QuestNutri.app.getWidth()/10;
		int y = QuestNutri.app.getY() + QuestNutri.app.getHeight()/10;
		
		this.setBounds(x, y, 500,500);

		setTitle(new LanguageUtil("Pesos - ", "Weights - ").get() + customer.getName());
		if(opened != null) {
			opened.dispose();
		}
		opened = this;
		
	}
	
    /**
     * Configura a lista de pesos para exibição no frame.
     */
	private void setUpWgtList() {
		weightList = new GenericJScrollPaneList<Weight>(
					Weight.findAllByCustomerPK(customer.getId()),
					new Object[]{new LanguageUtil("Data de Registro", "Register Date").get(), new LanguageUtil("Peso (kg)", "Weight (kg)").get()},
					wgt -> new Object[] {wgt.getRegisterDate(), wgt.value},
					wgt -> () -> {}
				).addDeleteItemPopUpOption(new LanguageUtil("Excluir Registro de Peso", "Delete Weight Record").get(), 
								wgt -> () -> WeightController.deleteWeight(wgt, updateInput))
				 .init();
		framePanel.add(weightList, framePanel.gbc.yP().wgt(1.0).insets("1", 0, 15));
	}
	
    /**
     * Configura o formulário superior para adicionar novos pesos.
     */
	private void setUpUpperForm() {
		wgtValue = new FormBoxInput(framePanel).setLbl(new LanguageUtil("Adicionar novo peso (kg)", "Add new weight (kg)").get())
											   .addValidation(
													   new ValidationRule(
															   value -> {
																   try {
																		Double.parseDouble(value.replace(',', '.'));
																		return true;
																	} catch (Exception e) {
																		return false;
																	}
															   }, new LanguageUtil("Peso inválido.", "Invalid weight.").get()));
		
		wgtValue.setButtonBox(addNewWeight(), true);
		
		FormSection addNewWeight = new FormSection(framePanel).setLateralDistance(0)
															  .addRow(wgtValue)
															  .init();
		
		framePanel.add(addNewWeight, framePanel.gbc.grid(0).fill("BOTH").insets("3", 0, 15).wgt(1.0, 0));
		
	}
	
    /**
     * Cria e configura o botão para adicionar um novo peso.
     * 
     * @return O botão configurado para registrar o novo peso.
     */
	private StdButton addNewWeight() {
		StdButton addNew = StdButton.stdBtnConfig(new LanguageUtil("Registrar", "Register").get());
		addNew.setAction(() -> {
			if(Validate.formFields(wgtValue)) {
				if(!wgtValue.getValue().replace(',', '.').isBlank()) {
					Weight wgt = new Weight(customer, Double.parseDouble(wgtValue.getValue().replace(',', '.')));
					try {
						if(wgt.save()) {
							framePanel.remove(weightList);
							setUpWgtList();
							framePanel.revalidate();
							framePanel.repaint();
							updateInput.setValue(wgt.value + "kg");
							QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Novo peso registrado!", "New weight registered!").get());
						} else {
							QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível registrar.", "Unable to register.").get());
						}
					} catch (Exception e) {
						QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Houve um erro ao registrar.", "An error occurred while registering.").get());
					}
				}
			}
		});
		return addNew;
	}
	
	
    /**
     * Método para inicializar e configurar o frame de gerenciamento de pesos.
     * 
     * @return O próprio frame para implementar fluent interface.
     */
	public WeightFrame init() {
		setUpUpperForm();
		setUpWgtList();
		this.setVisible(true);
		return this;
	}
	
}