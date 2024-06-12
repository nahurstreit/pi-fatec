package controller;

import java.util.List;

import model.entities.Aliment;
import view.components.QuestNutriJOP;
import view.frames.AlimentFrame;
import view.utils.LanguageUtil;

public class AlimentController {
	/**
	 * Procura os alimentos que satisfaçam as condições em: 'searchField LIKE searchTerm' no banco de dados.;
	 * @param searchField - Coluna de busca
	 * @param searchTerm - String procurada
	 * @return Lista de alimentos do banco de dados que satisfazem a busca.
	 */
    public static List<Aliment> searchAliments(String searchField, String searchTerm) {
        String searchParam = "";
        searchTerm = searchTerm.replaceAll("\\W", "");
        if (!searchTerm.isBlank()) {
            switch (searchField) {
                case "Nome": case "Name":
                    searchParam = "name";
                    break;
                case "Grupo": case "Group":
                    searchParam = "alimentGroup";
                    break;
            }
            
            searchParam += " LIKE '%" + searchTerm + "%'";
        }
        
        return Aliment.findAll(searchParam);
    }

    /**
     * Abre um novo AlimentFrame do alimento indicado
     * @param aliment - Alimento desejável a exibir as informações no frame.
     */
    public static void openAlimentFrame(Aliment aliment) {
        AlimentFrame frame = new AlimentFrame(aliment);
        frame.setVisible(true);
    }
    
    public static void createAliment(Aliment aliment, String name, String group, String kcal) {
    	aliment.setName(name)
    		   .setAlimentGroup("Customizado")
    		   .setKcal(kcal);
    	
    	try {
        	if(aliment.save()) {
        		QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento salvo!", "Aliment saved!").get());
        	}
		} catch (Exception e) {
			QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento salvo!", "Aliment saved!").get());
		}

    }
    
    public static boolean saveAliment(Aliment aliment, String name, String kcal) {
    	aliment.save();
    	return true;
    }
    
    public static boolean deleteAliment(Aliment aliment) {
    	boolean res = false;
    	if(aliment.isCustom()) {
	    		try {
					res = aliment.delete();
				} catch (Exception e) {
				}
    	} else {
    		QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não é permitido excluir esse registro.", "!").get());
    		return false;
    	}
    	
    	if(!res) {
    		QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Não foi possível excluir!", "!").get());
    	} else {
    		QuestNutriJOP.showMessageDialog(null, new LanguageUtil("Alimento excluído.", "!").get());
    	}
    	return res;
    }
    
}