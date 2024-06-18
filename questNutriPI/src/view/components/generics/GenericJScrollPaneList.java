package view.components.generics;

import java.awt.Component;
import java.awt.Font;
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

/**
 * Classe que define um componente JScrollPane já embutido com uma lista, para ser usado no sistema QuestNutri.
 * 
 * @param <T> O tipo dos elementos da lista. Toda linha terá armazenada em si um objeto desse tipo. Dessa forma é possível definir
 * a exibição das linhas com as propriedades de um objeto desse tipo e também interargir com esse objeto com funções.
 */
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

    private Font cellFont = STD_REGULAR_FONT.deriveFont(15f);
    private Font headerFont = STD_BOLD_FONT.deriveFont(15f);
    
    private DefaultTableCellRenderer cellRenderer;

    /**
     * Método construtor da classe.
     * @param originList - Array que contém os dados que comporão o conteúdo da lista deste JScrollPane.
     * @param columnNames - Nome das colunas da lista
     * @param rowMapper - A função que deve ser executada para preencher a lista. Sempre será passado à função o conteúdo do array.
     * @param doubleClickAction - O que deve acontecer caso o usuário dê um duplo clique em um dos itens da lista. Sempre será passado à função o conteúdo associado da linha.
     */
    public GenericJScrollPaneList(List<T> originList, 
								  Object[] columnNames,
								  Function<T, Object[]> rowMapper, //Método de formatação das linhas
								  Function<T, Runnable> doubleClickAction //Ação de double click na lista
    							 ) {
    	this.originList = originList;
    	this.columnNames = columnNames;
    	this.rowMapper = rowMapper;
    	this.doubleClickAction = doubleClickAction;
        setViewportBorder(null);
    }
    
    /**
     * Método construtor padrão.
     */
    public GenericJScrollPaneList() {
    }
    
    /**
     * Método que inicializa a configuração da tabela.
     */
    private void configureTable() {
        table.setRowHeight(20);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(headerFont);
        tableHeader.setBackground(STD_BLUE_COLOR);
        tableHeader.setForeground(STD_WHITE_COLOR);

        setCellRenderer();
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    /**
     * Método para definir os dados de origem da lista.
     * @param originList - Lista contendo os dados de origem
     * @return o próprio objeto para implementar fluent interface.
     */
    public GenericJScrollPaneList<T> setOriginList(List<T> originList) {
        this.originList = originList;
        return this;
    }

    /**
     * Método para definir os nomes da coluna. Deverá ser enviado um array de Objetos que sejam String.
     * 
     * @param columnNames Array de Objetos do tipo string.
     * @return o próprio objeto para implementar fluent interface.
     */
    public GenericJScrollPaneList<T> setColumnNames(Object[] columnNames) {
        this.columnNames = columnNames;
        return this;
    }

    /**
     * Método para definir a função que será executada para preencher os dados das linhas.
     * 
     * @param rowMapper Função que recebe um objeto T da lista e retorna um array de Objetos para preencher as colunas.
     * @return o próprio objeto para implementar fluent interface.
     */
    public GenericJScrollPaneList<T> setRowMapper(Function<T, Object[]> rowMapper) {
        this.rowMapper = rowMapper;
        return this;
    }

    /**
     * Método para definir a ação a ser executada em um duplo clique na lista.
     * 
     * @param doubleClickAction Função que recebe um objeto T da lista e retorna um Runnable para ser executado.
     * @return o próprio objeto para implementar fluent interface.
     */
    public GenericJScrollPaneList<T> setDoubleClickAction(Function<T, Runnable> doubleClickAction) {
        this.doubleClickAction = doubleClickAction;
        return this;
    }
    
    /**
     * Método para construir o modelo da tabela com os dados de origem.
     * 
     * @return o próprio objeto para implementar fluent interface.
     */
    public GenericJScrollPaneList<T> buildTableModel() {
        tableModel = new CustomTableModel<>(columnNames, 0);
        for (T item : originList) {
            tableModel.addObject(item, rowMapper.apply(item));
        }
        return this;
    }

    /**
     * Método para inicializar a tabela.
     * 
     * @return o próprio objeto para implementar fluent interface.
     */
    public GenericJScrollPaneList<T> init() {
    	buildTableModel();
        table = new JTable(tableModel);
        configureTable();
        setDoubleClickInteraction();
        setPopupMenu();
        this.setViewportView(table);
        return this;
    }
    
    
    /**
     * Método para adicionar uma opção de menu popup.
     * 
     * @param text Texto da opção do menu.
     * @param action Ação a ser executada quando a opção for selecionada.
     * @return o próprio objeto para implementar fluent interface.
     */
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
    
    /**
     * Método para adicionar uma opção de menu popup para deletar um item.
     * 
     * @param text Texto da opção do menu.
     * @param additionalAction Função para validar se o item foi de fato excluído. Recebe um objeto do tipo T, e retorna um booleano indicando a exclusão.
     * @return o próprio objeto para implementar fluent interface.
     */
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
                        originList.remove(selectedItem);
                        rebuildTableModel();
                    }
                }
            }
        });
        popUpMenuItems.add(deleteItem);
        
        return this;
    }
    
    /**
     * Método para recriar a tabela quando um registro for excluído.
     */
    private void rebuildTableModel() {
        //Recria o modelo da tabela completamente
        tableModel = new CustomTableModel<>(columnNames, 0);
        for (T item : originList) {
            tableModel.addObject(item, rowMapper.apply(item));
        }
        table.setModel(tableModel); //Define o novo modelo na tabela
        configureTable(); //Configura novamente a tabela
    }
    
    
    /**
     * Método para definir a fonte das células da lista.
     * 
     * @param font Fonte a ser usada nas células.
     * @return o próprio objeto para implementar fluent interface.
     */
    public GenericJScrollPaneList<T> setCellFont(Font font) {
    	if(font != null) {
    		this.cellFont = font;
    	};
    	
    	return this;
    }
    
    /**
     * Método para definir a fonte do cabeçalho.
     * 
     * @param font Fonte a ser usada no cabeçalho.
     * @return o próprio objeto para implementar fluent interface.
     */
    public GenericJScrollPaneList<T> setHeaderFont(Font font) {
    	if(font != null) {
    		this.headerFont = font;
    	};
    	
    	return this;
    }
    
    /**
     * Método para aplicar as configurações de fonte nas células.
     */
    private void setCellRenderer() {
    	cellRenderer = new DefaultTableCellRenderer() {
    	        private static final long serialVersionUID = 1L;

    	        @Override
    	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    	            cellComponent.setFont(cellFont);
    	            return cellComponent;
    	        }
    	    };
    }

    /**
     * Método que configura o menu popup.
     */
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

    /**
     * Método que configura a interação de duplo clique.
     */
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

    /**
     * Método para definir a interação de pressionar o mouse.
     * 
     * @param mouseAdapter Adaptador de mouse a ser usado.
     * @return o próprio objeto para implementar fluent interface.
     */
    public GenericJScrollPaneList<T> setMousePressedInteraction(MouseAdapter mouseAdapter) {
        table.addMouseListener(mouseAdapter);
        return this;
    }

    /**
     * Método para definir a interação de soltar o mouse.
     * 
     * @param mouseAdapter Adaptador de mouse a ser usado.
     * @return o próprio objeto para implementar fluent interface.
     */
    public GenericJScrollPaneList<T> setMouseReleasedInteraction(MouseAdapter mouseAdapter) {
        table.addMouseListener(mouseAdapter);
        return this;
    }

    /**
     * Método para definir a interação de clique do mouse.
     * 
     * @param mouseAdapter Adaptador de mouse a ser usado.
     * @return o próprio objeto para implementar fluent interface.
     */
    public GenericJScrollPaneList<T> setMouseClickedInteraction(MouseAdapter mouseAdapter) {
        table.addMouseListener(mouseAdapter);
        return this;
    }
    
}
