package controller.entities;

import java.util.List;

import model.entities.Aliment;
import utils.interfaces.GeneralVisualSettings;
import utils.interfaces.IDoAction;
import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.frames.AlimentFrame;

/**
 * Controlador responsável por operações relacionadas a alimentos na aplicação.
 */
public abstract class AlimentController implements GeneralVisualSettings{
	/**
	 * Procura os alimentos que satisfaçam as condições em: 'searchField LIKE
	 * searchTerm' no banco de dados.;
	 * 
	 * @param searchField - Coluna de busca
	 * @param searchTerm  - String procurada
	 * @return Lista de alimentos do banco de dados que satisfazem a busca.
	 */
	public static List<Aliment> searchAliments(String searchField, String searchTerm) {
		String searchParam = "";
		searchTerm = searchTerm.replaceAll("\\W", "");
		if (!searchTerm.isBlank()) {
			switch (searchField) {
			case "Nome":
			case "Name":
				searchParam = "name";
				break;
			case "Grupo":
			case "Group":
				searchParam = "alimentGroup";
				break;
			}

			searchParam += " LIKE '%" + searchTerm + "%'";
		}

		return Aliment.findAll(searchParam);
	}

	/**
	 * Abre um novo AlimentFrame do alimento indicado
	 * 
	 * @param aliment - Alimento desejável a exibir as informações no frame.
	 * @param onUpdate - Ação a ser executada depois do update.
	 */
	public static void openAlimentFrame(Aliment aliment, IDoAction onUpdate) {
		AlimentFrame frame = new AlimentFrame(aliment, onUpdate);
		frame.setVisible(true);
	}

    /**
     * Cria um novo alimento customizado com os dados fornecidos.
     *
     * @param aliment       - Objeto Aliment a ser atualizado ou criado.
     * @param name          - Nome do alimento.
     * @param alimentGroup  - Grupo do alimento.
     * @param kcal          - Calorias do alimento.
     * @param carb          - Carboidratos do alimento.
     * @param protein       - Proteínas do alimento.
     * @param fat           - Gorduras do alimento.
     * @param humidity      - Umidade do alimento.
     * @param kj            - kJ do alimento.
     * @param dietaryFiber  - Fibra dietética do alimento.
     * @param cholesterol   - Colesterol do alimento.
     * @param sodium        - Sódio do alimento.
     * @param calcium       - Cálcio do alimento.
     * @param magnesium     - Magnésio do alimento.
     * @param manganese     - Manganês do alimento.
     * @param phosphorus    - Fósforo do alimento.
     * @param iron          - Ferro do alimento.
     * @param potassium     - Potássio do alimento.
     * @param copper        - Cobre do alimento.
     * @param zinc          - Zinco do alimento.
     * @param retinol       - Retinol do alimento.
     * @param rE            - rE do alimento.
     * @param rAE           - rAE do alimento.
     * @param thiamine      - Tiamina do alimento.
     * @param riboflavin    - Riboflavina do alimento.
     * @param pyridoxine    - Piridoxina do alimento.
     * @param niacin        - Niacina do alimento.
     * @param vitaminC      - Vitamina C do alimento.
     * @param ash           - Cinzas do alimento.
     * @param afterUpdate   - Ação a ser executada após a atualização.
     */
	public static void createAliment(Aliment aliment, String name, String alimentGroup, String kcal, String carb, String protein, String fat, String humidity, String kj, String dietaryFiber, String cholesterol, String sodium, String calcium, String magnesium, String manganese, String phosphorus, String iron, String potassium, String copper, String zinc, String retinol, String rE, String rAE, String thiamine, String riboflavin, String pyridoxine, String niacin, String vitaminC, String ash, IDoAction afterUpdate) {
		aliment.setName(name).setAlimentGroup("Customizado").setKcal(kcal).setkJ(kj).setCarb(carb).setProtein(protein).setFat(fat)
		.setHumidity(humidity).setDietaryFiber(dietaryFiber).setCholesterol(cholesterol).setSodium(sodium).setCalcium(calcium).setMagnesium(magnesium)
		.setManganese(manganese).setPhosphorus(phosphorus).setIron(iron).setPotassium(potassium).setCopper(copper).setZinc(zinc).setRetinol(retinol).setrE(rE)
		.setrAE(rAE).setThiamine(thiamine).setRiboflavin(riboflavin).setPyridoxine(pyridoxine).setNiacin(niacin).setVitaminC(vitaminC).setAsh(ash);

		try {
			if (aliment.save()) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento salvo!", "Aliment saved!").get());
			}
		} catch (Exception e) {
			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("NÃ£o foi possÃ­vel salvar o alimento!", "Unable to save aliment!").get());
		}

	}

	/**
     * Salva as alterações feitas em um alimento.
     *
     * @param aliment       - Objeto Aliment a ser atualizado.
     * @param name          - Nome do alimento.
     * @param alimentGroup  - Grupo do alimento.
     * @param kcal          - Calorias do alimento.
     * @param kJ            - kJ do alimento.
     * @param carb          - Carboidratos do alimento.
     * @param protein       - Proteínas do alimento.
     * @param fat           - Gorduras do alimento.
     * @param humidity      - Umidade do alimento.
     * @param dietaryFiber  - Fibra dietética do alimento.
     * @param cholesterol   - Colesterol do alimento.
     * @param sodium        - Sódio do alimento.
     * @param calcium       - Cálcio do alimento.
     * @param magnesium     - Magnésio do alimento.
     * @param manganese     - Manganês do alimento.
     * @param phosphorus    - Fósforo do alimento.
     * @param iron          - Ferro do alimento.
     * @param potassium     - Potássio do alimento.
     * @param copper        - Cobre do alimento.
     * @param zinc          - Zinco do alimento.
     * @param retinol       - Retinol do alimento.
     * @param rE            - rE do alimento.
     * @param rAE           - rAE do alimento.
     * @param thiamine      - Tiamina do alimento.
     * @param riboflavin    - Riboflavina do alimento.
     * @param pyridoxine    - Piridoxina do alimento.
     * @param niacin        - Niacina do alimento.
     * @param vitaminC      - Vitamina C do alimento.
     * @param ash           - Cinzas do alimento.
     * @param onUpdate      - Ação a ser executada após a atualização.
     * @return true se as alterações foram salvas com sucesso, false caso contrário.
     */
	public static boolean saveAliment(Aliment aliment, String name, String alimentGroup, String kcal, String kJ,
			String carb, String protein, String fat, String humidity, String dietaryFiber, String cholesterol,
			String sodium, String calcium, String magnesium, String manganese, String phosphorus, String iron,
			String potassium, String copper, String zinc, String retinol, String rE, String rAE, String thiamine,
			String riboflavin, String pyridoxine, String niacin, String vitaminC, String ash, IDoAction onUpdate) {

		boolean res = false;
		
		if(aliment.isCustom()) {
			try {
				aliment.setName(name)
				.setAlimentGroup("Customizado")
				.setKcal(kcal)
				.setkJ(kJ)
				.setCarb(carb)
				.setProtein(protein)
				.setFat(fat)
				.setHumidity(humidity)
				.setDietaryFiber(dietaryFiber)
				.setCholesterol(cholesterol)
				.setSodium(sodium)
				.setCalcium(calcium)
				.setMagnesium(magnesium)
				.setManganese(manganese)
				.setPhosphorus(phosphorus)
				.setIron(iron)
				.setPotassium(potassium)
				.setCopper(copper)
				.setZinc(zinc)
				.setRetinol(retinol)
				.setrE(rE).setrAE(rAE)
				.setThiamine(thiamine)
				.setRiboflavin(riboflavin)
				.setPyridoxine(pyridoxine)
				.setNiacin(niacin)
				.setVitaminC(vitaminC)
				.setAsh(ash);
				
				
				res = aliment.save();
				if(res) {
					QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento salvo!", "Aliment saved!").get());
					onUpdate.execute();
				}
			} catch (Exception e) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("NÃ£o foi possÃ­vel salvar o alimento!", "Unable to save aliment!").get());
			}
		}
		return res;
	}

	/**
	 * MÃ©todo para a exclusÃ£o de registro de um alimento, esta aÃ§Ã£o sÃ³ Ã© permitida caso o alimento seja customizado.
	 * Para alimetos customizados, Ã© solicitado a confirmaÃ§Ã£o do usuario por meio de uma String,
	 * se o alimento nÃ£o Ã© customizado entÃ£o uma mensagem de erro Ã© exibida.
	 * 
	 * @param aliment A instancia de aliment a ser excluÃ­da
	 * @return true se o alimento foi excluÃ­do com sucesso, false caso contrÃ¡rio.
	 */
	public static boolean deleteAliment(Aliment aliment) {
		boolean res = false;
		
		final String STD_DELETE_STRING = new LanguageUtil("EXCLUIR", "DELETE").get();

		if(aliment.isCustom()) {
			try {
				String choice = QuestNutriJOP.showInputDialog(null,
						new LanguageUtil(
								"VocÃª estÃ¡ tentando excluir um alimento customizado: \nAlimento:" + aliment.name
										+ "\nEssa aÃ§Ã£o Ã© IRREVERSÃ�VEL. Digite '" + STD_DELETE_STRING
										+ "' para deletar o registro.",
								"You are trying to delete a custom aliment: \nAliment:" + aliment.name
										+ "\nThis action is IRREVERSIBLE. Type '" + STD_DELETE_STRING
										+ "' to delete the registry.")
								.get());
				if(choice != null) {
					if(!choice.isBlank()) {
						if(choice.equals(STD_DELETE_STRING)) {
							res = aliment.delete();
							if(res) {
								QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento excluÃ­do!", "Excluded aliment!").get());
							} else {
								QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Falha ao excluir o Alimento", "Failed to delete aliment").get());
							}
						} else {
							QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Texto InvÃ¡lido", "Invalid Text").get());
						}
					};
				}
			} catch (Exception e) {
			}
		} else {
			QuestNutriJOP.showMessageDialog(null,
					new LanguageUtil("NÃ£o Ã© permitido excluir esse registro!", "Deleting this record is not allowed!")
							.get());
			return false;
		}
		
		return res;
	}

}