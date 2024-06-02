package view.panels.pages;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import controller.AlimentController;
import model.entities.Aliment;
import view.QuestNutri;
import view.panels.components.GenericJPanel;
import view.utils.LanguageUtil;

public class AlimentsPage extends GenericListPage<Aliment> {
    private static final long serialVersionUID = 1L;

    public AlimentsPage(GenericJPanel ownerPanel) {
        super(ownerPanel, 
        	  new String[]{new LanguageUtil("Nome", "Name").get(), new LanguageUtil("Grupo", "Group").get()}, 
              new LanguageUtil("Controle de Alimentos", "Food Control").get());
        setCreateBtn(() -> {});
    }

    @Override
    protected List<Aliment> setInitialList() {
        return Aliment.findAll();
    }
    
    @Override
    protected Object[] setColumnNames() {
        return new Object[] {
                new LanguageUtil("Nome", "Name").get(), 
                new LanguageUtil("Grupo Alimentar", "Food Group").get(), 
                new LanguageUtil("Kcal", "Calories").get()
            };
    }
    
    @Override
    protected Function<Aliment, Object[]> setRowMapper() {
    	return aliment -> new Object[] {aliment.name, aliment.alimentGroup, aliment.kcal};
    }
    
    @Override
    protected Function<Aliment, Runnable> setDoubleClickAction() {
    	return aliment -> () -> {};
    }
    
    @Override
    protected BiFunction<String, String, List<Aliment>> setSearchMethod() {
    	return AlimentController::searchAliments;
    }
    
    @Override
    protected void setPopUpOptions() {
    	if(QuestNutri.isEditAuth()) createDeleteItemPopUpOption(new LanguageUtil("Excluir Registro", "Delete Record").get(), aliment -> () -> AlimentController.deleteAliment(aliment));
    }

}