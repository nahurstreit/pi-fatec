package controller;
	
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.util.function.Function;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import model.entities.Aliment;
import view.panels.components.CustomTableModel;
import view.panels.components.GeneralJPanelSettings;
	
public class InterfaceController {
	/**
	 * Método genérico para criação de tabelas interativas com os resultados do banco de dados
	 * @param <T> - Tipo do retorno da tabela
	 * @param originList - Lista a ser exibida
	 * @param columnNames - Nome das colunas superiores
	 * @param rowMapper - Formatações opcionais dos resultados nas colunas
	 * @param doubleClickAction - Função executada ao dar double click em algum dos itens na tabela
	 * @return -> Retorna um JScrollpane contendo a tabela já configurada.
	 */
    public static <T> JScrollPane createTable(List<T> originList, 
    										  Object[] columnNames,
    										  Function<T, Object[]> rowMapper, //Método de formatação das linhas
    										  Function<T, Runnable> doubleClickAction //Ação de double click na lista
    										  ) {
        CustomTableModel<T> tableModel = new CustomTableModel<>(columnNames, 0);
        for (T item : originList) {
            tableModel.addObject(item, rowMapper.apply(item));
        }

        JTable table = new JTable(tableModel);
        table.setRowHeight(20);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(15f));
        tableHeader.setBackground(GeneralJPanelSettings.STD_BLUE_COLOR);
        tableHeader.setForeground(Color.white);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cellComponent.setFont(GeneralJPanelSettings.STD_REGULAR_FONT.deriveFont(15f));
                return cellComponent;
            }
        };

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0 && evt.getClickCount() == 2) {
                    T selectedItem = tableModel.getObjectAt(row);
                    doubleClickAction.apply(selectedItem).run();
                }
            }
        });

        return scrollPane;
    }
	
	public static JScrollPane getAlimentList(List<Aliment> originList) {
        return createTable(
                originList,
                new Object[]{"Alimento", "Grupo", "kcal (100g)"},
                aliment -> new Object[]{
                        aliment.name,
                        aliment.alimentGroup,
                        aliment.kcal
                },
                aliment -> () -> {
                    // Implementar ação de clique duplo para Aliment
                }
        );
    }
	
	public static JScrollPane getAlimentList() {
		return InterfaceController.getAlimentList(Aliment.findAll());
	}

}