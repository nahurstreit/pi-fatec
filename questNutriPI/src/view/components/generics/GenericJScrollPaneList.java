package view.components.generics;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import utils.interfaces.GeneralVisualSettings;
import view.components.tables.CustomTableModel;

public class GenericJScrollPaneList<T> extends JScrollPane implements GeneralVisualSettings {
    private static final long serialVersionUID = 1L;

    // Configurações
    private List<T> originList;
    private Object[] columnNames;
    private Function<T, Object[]> rowMapper; // Método de formatação das linhas
    private Function<T, Runnable> doubleClickAction; // Ação de double click na lista

    private CustomTableModel<T> tableModel;
    private JTable table;
    
    private JPopupMenu popUpMenu;
    
    private List<JMenuItem> popUpMenuItems = new ArrayList<>(); // Opções de interação ao clicar com o botão direito.

    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cellComponent.setFont(STD_REGULAR_FONT.deriveFont(15f));
            return cellComponent;
        }
    };

    public GenericJScrollPaneList(List<T> originList, 
								  Object[] columnNames,
								  Function<T, Object[]> rowMapper, //Método de formatação das linhas
								  Function<T, Runnable> doubleClickAction //Ação de double click na lista
    							 ) {
    	this.originList = originList;
    	this.columnNames = columnNames;
    	this.rowMapper = rowMapper;
    	this.doubleClickAction = doubleClickAction;
    }
    
    public GenericJScrollPaneList() {
    }
    
    private void configureTable() {
        table.setRowHeight(20);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(STD_BOLD_FONT.deriveFont(15f));
        tableHeader.setBackground(STD_BLUE_COLOR);
        tableHeader.setForeground(STD_WHITE_COLOR);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    public GenericJScrollPaneList<T> setOriginList(List<T> originList) {
        this.originList = originList;
        return this;
    }

    public GenericJScrollPaneList<T> setColumnNames(Object[] columnNames) {
        this.columnNames = columnNames;
        return this;
    }

    public GenericJScrollPaneList<T> setRowMapper(Function<T, Object[]> rowMapper) {
        this.rowMapper = rowMapper;
        return this;
    }

    public GenericJScrollPaneList<T> setDoubleClickAction(Function<T, Runnable> doubleClickAction) {
        this.doubleClickAction = doubleClickAction;
        return this;
    }

    public GenericJScrollPaneList<T> buildTableModel() {
        tableModel = new CustomTableModel<>(columnNames, 0);
        for (T item : originList) {
            tableModel.addObject(item, rowMapper.apply(item));
        }
        return this;
    }

    public GenericJScrollPaneList<T> init() {
    	buildTableModel();
        table = new JTable(tableModel);
        configureTable();
        setDoubleClickInteraction();
        setPopupMenu();
        this.setViewportView(table);
        return this;
    }
    
    public GenericJScrollPaneList<T> addPopUpOption(String text, Function<T, Runnable> action) {
    	JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                T selectedItem = tableModel.getObjectAt(row);
                action.apply(selectedItem).run();
            }
        });
        popUpMenuItems.add(menuItem);
        
        return this;
    }
    
    public GenericJScrollPaneList<T> addDeleteItemPopUpOption(String text, Function<T, Supplier<Boolean>> additionalAction) {
        JMenuItem deleteItem = new JMenuItem(text);
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    T selectedItem = tableModel.getObjectAt(row);
                    Supplier<Boolean> action = additionalAction.apply(selectedItem);
                    if (action.get()) {
                        tableModel.removeRow(row);
                    }
                }
            }
        });
        popUpMenuItems.add(deleteItem);
        
        return this;
    }

    private void setPopupMenu() {
    	popUpMenu = new JPopupMenu();
    	for(JMenuItem item: popUpMenuItems) {
    		popUpMenu.add(item);
    	}
    	
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (evt.isPopupTrigger()) {
                    showPopup(evt);
                }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                if (evt.isPopupTrigger()) {
                    showPopup(evt);
                }
            }

            private void showPopup(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    table.setRowSelectionInterval(row, row);
                    popUpMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        });
    }

    private void setDoubleClickInteraction() {
        table.addMouseListener(new MouseAdapter() {
            @Override	
            public void mouseClicked(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0 && evt.getClickCount() == 2) {
                    T selectedItem = tableModel.getObjectAt(row);
                    doubleClickAction.apply(selectedItem).run();
                }
            }
        });
    }

    //Métodos adicionais para interações de clique, mouse press/release, etc.
    public GenericJScrollPaneList<T> setMousePressedInteraction(MouseAdapter mouseAdapter) {
        table.addMouseListener(mouseAdapter);
        return this;
    }

    public GenericJScrollPaneList<T> setMouseReleasedInteraction(MouseAdapter mouseAdapter) {
        table.addMouseListener(mouseAdapter);
        return this;
    }

    public GenericJScrollPaneList<T> setMouseClickedInteraction(MouseAdapter mouseAdapter) {
        table.addMouseListener(mouseAdapter);
        return this;
    }
}
