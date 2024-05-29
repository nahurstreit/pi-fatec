package view.panels.pages;

import java.util.List;

import controller.AlimentController;
import controller.InterfaceController;
import model.entities.Aliment;
import view.panels.components.GenericJPanel;

public class AlimentsPage extends GenericListPage<Aliment> {
    private static final long serialVersionUID = 1L;

    public AlimentsPage(GenericJPanel ownerPanel) {
        super(ownerPanel, new String[]{"Nome", "Grupo", "kcal (100g)"}, "Controle de Alimentos", InterfaceController::getAlimentList);
    }

    @Override
    protected void performSearch() {
        String searchField = (String) comboBox.getSelectedItem();
        String searchTerm = inputSearchBox.getText();
        List<Aliment> results = searchItems(searchField, searchTerm);
        setItemList(InterfaceController.getAlimentList(results));
    }

    @Override
    protected List<Aliment> fetchInitialList() {
        return Aliment.findAll();
    }

    @Override
    protected List<Aliment> searchItems(String searchField, String searchTerm) {
        return AlimentController.searchAliments(searchField, searchTerm);
    }
}