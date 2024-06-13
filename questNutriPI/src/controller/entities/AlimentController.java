package controller.entities;

import java.util.List;

import model.entities.Aliment;
import utils.view.LanguageUtil;
import javax.swing.JFrame;
import model.entities.Customer;
import model.entities.Food;
import view.components.QuestNutriJOP;
import view.frames.AlimentFrame;
import view.panels.components.GeneralJPanelSettings;
import view.utils.LanguageUtil;

public class AlimentController {
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
	 */
	public static void openAlimentFrame(Aliment aliment) {
		AlimentFrame frame = new AlimentFrame(aliment);
		frame.setVisible(true);
	}

	public static void createAliment(Aliment aliment, String name, String alimentGroup, String kcal) {
		aliment.setName(name).setAlimentGroup("Customizado").setKcal(kcal);

		try {
			if (aliment.save()) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento salvo!", "Aliment saved!").get());
			}
		} catch (Exception e) {
			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento salvo!", "Aliment saved!").get());
		}

	}

	public static boolean saveAliment(Aliment aliment, String name, String alimentGroup, String kcal, String kJ,
			String carb, String protein, String fat, String humidity, String dietaryFiber, String cholesterol,
			String sodium, String calcium, String magnesium, String manganese, String phosphorus, String iron,
			String potassium, String copper, String zinc, String retinol, String rE, String rAE, String thiamine,
			String riboflavin, String pyridoxine, String niacin, String vitaminC, String ash) {

		if (aliment.isCustom()) {
			try {
				aliment.setName(name)
				.setAlimentGroup("Customizado")
				.setKcal(kcal)
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
				
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento salvo!", "Aliment saved!").get());
			
			} catch (Exception e) {
				QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento salvo!", "Aliment saved!").get());
			}
		}
		aliment.save();
		return true;
	}

	public static boolean deleteAliment(Aliment aliment) {
		boolean res = false;

		String choice = QuestNutriJOP.showInputDialog(null,
				new LanguageUtil(
						"Você está tentando excluir um alimento customizado: \nAlimento:" + aliment.name
								+ "\nEssa ação é IRREVERSÍVEL. Digite '" + GeneralJPanelSettings.STD_DELETE_STRING
								+ "' para deletar o registro.",
						"You are trying to delete a custom aliment: \nAliment:" + aliment.name
								+ "\nThis action is IRREVERSIBLE. Type '" + GeneralJPanelSettings.STD_DELETE_STRING
								+ "' to delete the registry.")
						.get());

		if (aliment.isCustom()) {
			try {
				res = aliment.delete();
			} catch (Exception e) {
			}
		} else {
			QuestNutriJOP.showMessageDialog(null,
					new LanguageUtil("Não é permitido excluir esse registro!", "Deleting this record is not allowed!")
							.get());
			return false;
		}

		if (!res) {
			QuestNutriJOP.showMessageDialog(null,
					new LanguageUtil("Não foi possível excluir!", "Unable to delete!").get());
		} else {
			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento excluído!", "Excluded aliment!").get());
		}
		return res;
	}

}