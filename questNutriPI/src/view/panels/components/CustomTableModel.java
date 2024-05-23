package view.panels.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel<T> extends DefaultTableModel {
    private List<T> objects = new ArrayList<>();

    // Construtor que define as colunas
    public CustomTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount); 
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // Torna as células não editáveis
        return false;
    }

    // Método para adicionar uma linha com o objeto genérico
    public void addObject(T obj, Object[] rowData) {
        objects.add(obj);
        addRow(rowData);
    }

    // Método para obter o objeto genérico de uma linha específica
    public T getObjectAt(int row) {
        return objects.get(row);
    }
}