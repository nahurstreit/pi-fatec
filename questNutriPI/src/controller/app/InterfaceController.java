package controller.app;
	
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Function;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import model.entities.Aliment;
import utils.interfaces.GeneralVisualSettings;
import view.components.tables.CustomTableModel;
	
public class InterfaceController implements GeneralVisualSettings {
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
        tableHeader.setFont(STD_BOLD_FONT.deriveFont(15f));
        tableHeader.setBackground(STD_BLUE_COLOR);
        tableHeader.setForeground(Color.white);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cellComponent.setFont(STD_REGULAR_FONT.deriveFont(15f));
                return cellComponent;
            }
        };

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        
        // Criação do JPopupMenu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Excluir");
        popupMenu.add(deleteItem);

        // Ação para o item de exclusão
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    T selectedItem = tableModel.getObjectAt(row);
                    //Ação de exclusão do registro
                    originList.remove(selectedItem);
                    tableModel.removeRow(row);
                }
            }
        });

        table.addMouseListener(new java.awt.event.MouseAdapter() {
        	 @Override
             public void mousePressed(java.awt.event.MouseEvent evt) {
                 if (evt.isPopupTrigger()) {
                     showPopup(evt);
                 }
             }

             @Override
             public void mouseReleased(java.awt.event.MouseEvent evt) {
                 if (evt.isPopupTrigger()) {
                     showPopup(evt);
                 }
             }

             private void showPopup(java.awt.event.MouseEvent evt) {
                 int row = table.rowAtPoint(evt.getPoint());
                 if (row >= 0) {
                     table.setRowSelectionInterval(row, row);
                     popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                 }
             }
        	
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