package view.panels.components;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entities.Aliment;
import view.utils.LanguageUtil;

public class AlimentNutritionalTable extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    
    private List<String> columnNames = new ArrayList<>();
    private Double selectedPortion;
    private Aliment aliment;
    private boolean hasSelectedPortion;
    
    public AlimentNutritionalTable(Aliment aliment, Double selectedPortion) {
        this.aliment = aliment;
        this.selectedPortion = selectedPortion;
        this.hasSelectedPortion = selectedPortion != null;
        setupColumnNames();
    }

    public AlimentNutritionalTable(Aliment aliment) {
        this(aliment, null);
    }

    private void setupColumnNames() {
        columnNames.add(new LanguageUtil("Nutrientes", "Nutrients").get());
        columnNames.add("100g");
        if (hasSelectedPortion) {
            columnNames.add(selectedPortion + "g");
        }
    }
    
    @Override
    public int getRowCount() {
        return 26; // Number of nutrients (excluding the 'name' and 'alimentGroup' rows)
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return getNutrientName(rowIndex);
            case 1:
            	String res = getNutrientValue(aliment, rowIndex);
            	try {
            		res = String.format("%.2f", Double.parseDouble(res.replace(',', '.')));
				} catch (Exception e) {
				}
            	
                return res;
            case 2:
                if (hasSelectedPortion) {
                    String value = getNutrientValue(aliment, rowIndex);
                    try {
                        double dValue = Double.parseDouble(value.replace(',', '.'));
                        dValue *= (selectedPortion / 100);
                        return String.format("%.2f", dValue);
                    } catch (NumberFormatException e) {
                        return value;
                    }
                }
                return null;
            default:
                return null;
        }
    }

    private String getNutrientName(int rowIndex) {
        switch (rowIndex) {
            case 0: return "kcal";
            case 1: return "kJ";
            case 2: return "carb";
            case 3: return "protein";
            case 4: return "fat";
            case 5: return "humidity";
            case 6: return "dietaryFiber";
            case 7: return "cholesterol";
            case 8: return "sodium";
            case 9: return "calcium";
            case 10: return "magnesium";
            case 11: return "manganese";
            case 12: return "phosphorus";
            case 13: return "iron";
            case 14: return "potassium";
            case 15: return "copper";
            case 16: return "zinc";
            case 17: return "retinol";
            case 18: return "rE";
            case 19: return "rAE";
            case 20: return "thiamine";
            case 21: return "riboflavin";
            case 22: return "pyridoxine";
            case 23: return "niacin";
            case 24: return "vitaminC";
            case 25: return "ash";
            default: return "";
        }
    }

    private String getNutrientValue(Aliment aliment, int rowIndex) {
        switch (rowIndex) {
            case 0: return aliment.kcal;
            case 1: return aliment.kJ;
            case 2: return aliment.carb;
            case 3: return aliment.protein;
            case 4: return aliment.fat;
            case 5: return aliment.humidity;
            case 6: return aliment.dietaryFiber;
            case 7: return aliment.cholesterol;
            case 8: return aliment.sodium;
            case 9: return aliment.calcium;
            case 10: return aliment.magnesium;
            case 11: return aliment.manganese;
            case 12: return aliment.phosphorus;
            case 13: return aliment.iron;
            case 14: return aliment.potassium;
            case 15: return aliment.copper;
            case 16: return aliment.zinc;
            case 17: return aliment.retinol;
            case 18: return aliment.rE;
            case 19: return aliment.rAE;
            case 20: return aliment.thiamine;
            case 21: return aliment.riboflavin;
            case 22: return aliment.pyridoxine;
            case 23: return aliment.niacin;
            case 24: return aliment.vitaminC;
            case 25: return aliment.ash;
            default: return "";
        }
    }
}
