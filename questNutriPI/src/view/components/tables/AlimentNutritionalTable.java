/**
 * Package que contém o componente personalizado de tabelas.
 */
package view.components.tables;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.entities.Aliment;
import utils.view.LanguageUtil;

/**
 * Modelo de tabela para exibição dos valores nutricionais de um alimento.
 */
public class AlimentNutritionalTable extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    
    private List<String> columnNames = new ArrayList<>();
    private Double selectedPortion;
    private Aliment aliment;
    private Aliment totalAliment;
    private boolean hasSelectedPortion;

    /**
     * Construtor que inicializa a tabela de valores nutricionais com um alimento específico, uma porção selecionada
     * (opcional) e O total nutricional da refeição (opcional).
     *
     * @param aliment         O alimento cujos valores nutricionais serão exibidos na tabela.
     * @param selectedPortion A porção selecionada do alimento, se especificada.
     * @param totalAliment    O total nutricional da refeição.
     */
    public AlimentNutritionalTable(Aliment aliment, Double selectedPortion, Aliment totalAliment) {
        this.aliment = aliment;
        this.selectedPortion = selectedPortion;
        this.totalAliment = totalAliment;
        this.hasSelectedPortion = selectedPortion != null;
        setupColumnNames();
    }

    /**
     * Construtor que inicializa a tabela de valores nutricionais com um alimento específico e uma porção selecionada
     * (opcional).
     *
     * @param aliment         O alimento cujos valores nutricionais serão exibidos na tabela.
     * @param selectedPortion A porção selecionada do alimento, se especificada.
     */
    public AlimentNutritionalTable(Aliment aliment, Double selectedPortion) {
        this(aliment, selectedPortion, null);
    }
    
    /**
     * Construtor que inicializa a tabela de valores nutricionais com um alimento específico.
     *
     * @param aliment O alimento cujos valores nutricionais serão exibidos na tabela.
     */
    public AlimentNutritionalTable(Aliment aliment) {
    	this(aliment, null, null);
    }

    /**
     * Configura os nomes das colunas da tabela com base nos atributos definidos na instância.
     */
    private void setupColumnNames() {
        columnNames.add(new LanguageUtil("Nutrientes", "Nutrients").get());
        columnNames.add("100g");
        if (hasSelectedPortion) {
            columnNames.add(selectedPortion + "g");
        }
        if(totalAliment != null) {
            columnNames.add(new LanguageUtil("Total Refeição", "Meal's Total").get());
            columnNames.add(new LanguageUtil("% do Total", "% of Total").get());
        }
    }
    
    @Override
    public int getRowCount() {
        return 26;
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
                return formatValue(getNutrientValue(aliment, rowIndex));
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
            case 3:
                return formatValue(getNutrientValue(totalAliment, rowIndex));
            case 4:
                return calculatePercentage(rowIndex);
            default:
                return null;
        }
    }

    /**
     * Obtém o nome do nutriente com base no índice da linha.
     *
     * @param rowIndex Índice da linha para o nutriente.
     * @return Nome do nutriente correspondente ao índice.
     */
    private String getNutrientName(int rowIndex) {
        switch (rowIndex) {
            case 0: return new LanguageUtil("kcal", "Calories").get();
            case 1: return "kJ";
            case 2: return new LanguageUtil("Carboidratos", "Carb").get();
            case 3: return new LanguageUtil("Proteína", "Protein").get();
            case 4: return new LanguageUtil("Gordura", "Fat").get();
            case 5: return new LanguageUtil("Umidade", "Humidity").get();
            case 6: return new LanguageUtil("Fibra Dietética", "Dietary Fiber").get();
            case 7: return new LanguageUtil("Colesterol", "Cholesterol").get();
            case 8: return new LanguageUtil("Sódio", "Sodium").get();
            case 9: return new LanguageUtil("Cálcio", "Calcium").get();
            case 10: return new LanguageUtil("Magnésio", "Magnesium").get();
            case 11: return new LanguageUtil("Manganês", "Manganese").get();
            case 12: return new LanguageUtil("Fósforo", "Phosphorus").get();
            case 13: return new LanguageUtil("Ferro", "Iron").get();
            case 14: return new LanguageUtil("Potássio", "Potassium").get();
            case 15: return new LanguageUtil("Cobre", "Copper").get();
            case 16: return new LanguageUtil("Zinco", "Zinc").get();
            case 17: return new LanguageUtil("Retinol", "Retinol").get();
            case 18: return new LanguageUtil("Equivalente de Retinol", "RE").get();
            case 19: return new LanguageUtil("Equivalente de Retinol Ativo", "RAE").get();
            case 20: return new LanguageUtil("Tiamina", "Thiamine").get();
            case 21: return new LanguageUtil("Riboflavina", "Riboflavin").get();
            case 22: return new LanguageUtil("Piridoxina", "Pyridoxine").get();
            case 23: return new LanguageUtil("Niacina", "Niacin").get();
            case 24: return new LanguageUtil("Vitamina C", "Vitamin C").get();
            case 25: return new LanguageUtil("Cinza", "Ash").get();
            default: return "";
        }
    }

    /**
     * Obtém o valor do nutriente para um alimento específico com base no índice da linha.
     *
     * @param aliment  O alimento do qual se deseja obter o valor do nutriente.
     * @param rowIndex Índice da linha para o nutriente.
     * @return Valor do nutriente correspondente ao índice e ao alimento especificado.
     */
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

    /**
     * Formata o valor do nutriente para exibição na tabela.
     *
     * @param value Valor do nutriente a ser formatado.
     * @return Valor do nutriente formatado.
     */
    private String formatValue(String value) {
        try {
            return String.format("%.2f", Double.parseDouble(value.replace(',', '.')));
        } catch (Exception e) {
            return value;
        }
    }

    /**
     * Calcula a porcentagem do valor do nutriente em relação ao total da refeição, se especificado.
     *
     * @param rowIndex Índice da linha para o nutriente.
     * @return Porcentagem do valor do nutriente em relação ao total, formatada como string.
     */
    private String calculatePercentage(int rowIndex) {
        try {
            double value = Double.parseDouble(getNutrientValue(aliment, rowIndex).replace(',', '.')) * (selectedPortion / 100);
            double totalValue = Double.parseDouble(getNutrientValue(totalAliment, rowIndex).replace(',', '.'));
            return String.format("%.2f%%", (value / totalValue) * 100);
        } catch (Exception e) {
            return "";
        }
    }
}
