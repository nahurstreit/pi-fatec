package view.pages.generics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.buttons.StdButton.Action;
import view.components.generics.GenericComponent;
import view.components.generics.GenericJPanel;
import view.components.generics.GenericJScrollPaneList;
import view.components.inputs.HintInputField;

public abstract class GenericListPage<T> extends GenericPage {
    private static final long serialVersionUID = 1L;

    protected GenericComponent whiteBox;
    protected GenericJScrollPaneList<T> itemListComponent = null;
    protected JComboBox<String> comboBox;
    protected HintInputField inputSearchBox;
    
    private List<T> initialList;
    private Object[] columnNames;
    private Function<T, Object[]> rowMapper;
    private Function<T, Runnable> doubleClickAction;
    private BiFunction<String, String, List<T>> searchItems;
    
    /**
     * Variável para controlar a distância lateral e inferior do central box;
     */
    private int centralBoxLateralDownPadding = 40;

    public GenericListPage(GenericJPanel ownerPanel, String[] searchOptions, String pageTitle) {
        super(ownerPanel);
        this.ltGridBag();
        init();

        GenericComponent blueBox = new GenericComponent();
        blueBox.setBackground(STD_BLUE_COLOR);
        blueBox.ltGridBag();

        // Caixa de cima do painel branco
        whiteBox = new GenericComponent();
        whiteBox.setBackground(Color.green);
        whiteBox.ltGridBag();

        // Caixa do tipo de pesquisa
        GenericComponent typeBox = new GenericComponent();
        typeBox.setBackground(Color.white);
        typeBox.ltGridBag();

        // Label superior tipo de pesquisa
        JLabel lblTypeBox = new JLabel(new LanguageUtil("Pesquisar por", "Search by").get());
        lblTypeBox.setFont(STD_BOLD_FONT.deriveFont(20f));
        typeBox.add(lblTypeBox, typeBox.gbc.grid(0, 0).wgt(1.0).fill("BOTH").insets("3", 0, 10));

        // Criação da drop box com os possíveis valores de pesquisa
        comboBox = new JComboBox<>(searchOptions);

        // Personalizando a fonte no item selecionado
        comboBox.setFont(GenericJPanel.STD_BOLD_FONT.deriveFont(16f));
        // Personalizando a fonte dos itens na JComboBox
        comboBox.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setFont(GenericJPanel.STD_REGULAR_FONT.deriveFont(15f));
                return this;
            }
        });
        
        //Adicionando um ActionListener ao comboBox para limpar o inputSearchBox quando uma nova seleção for feita
        comboBox.addActionListener(e -> {
            inputSearchBox.setText(""); // Limpar o texto do inputSearchBox
        });

        // Adicionando o JComboBox à caixa do tipo de pesquisa (typeBox)
        typeBox.add(comboBox, typeBox.gbc.yP().insets("1", 0, 10));

        // Caixa do termo de pesquisa
        GenericComponent searchBox = new GenericComponent();
        searchBox.setBackground(Color.white);
        searchBox.ltGridBag();

        // Label superior tipo de pesquisa
        JLabel lblSearchBox = new JLabel(new LanguageUtil("Termo de Pesquisa", "Search Term").get());
        lblSearchBox.setFont(STD_BOLD_FONT.deriveFont(20f));
        searchBox.add(lblSearchBox, searchBox.gbc.grid(0, 0).wgt(1.0).fill("BOTH").insets("3", 0, 10));

        //InputBox tipo de pesquisa
        inputSearchBox = new HintInputField("", new Dimension(100, 25), 15f);
        
        //Adicionar FocusListener ao inputSearchBox
        inputSearchBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                inputSearchBox.setFocusable(false);
                SwingUtilities.invokeLater(() -> inputSearchBox.setFocusable(true));
            }
        });
        
        //Adicionar MouseListener ao inputSearchBox para mover o caret
        inputSearchBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    inputSearchBox.setCaretPosition(inputSearchBox.getText().length());
                });
            }
        });
        
        //Adicionar o DocumentListener ao inputSearchBox
        inputSearchBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                performSearch();
            }
        });
        
        searchBox.add(inputSearchBox, searchBox.gbc.yP().insets("1", 0, 10));

        GenericComponent upperBox = new GenericComponent();
        upperBox.setBackground(Color.red);
        upperBox.ltGridBag();
        upperBox.add(typeBox, upperBox.gbc.wgt(0).fill("NONE").insets(10));
        upperBox.add(searchBox, upperBox.gbc.wgt(1.0, 0).fill("BOTH").insets("2", 0, 10));

        whiteBox.add(upperBox, whiteBox.gbc.anchor("NORTH").wgt(1.0, 0).fill("HORIZONTAL"));

        whiteBox.add(itemListComponent, whiteBox.gbc.gridY(1).anchor("NORTH").wgt(1.0).fill("BOTH").insets(10));

        blueBox.add(whiteBox, blueBox.gbc.anchor("NORTHWEST").wgt(1.0).fill("BOTH").insets(30));

        JLabel pageLbl = new JLabel(pageTitle);
        pageLbl.setFont(STD_BOLD_FONT.deriveFont(40f));
        this.add(pageLbl, gbc.grid(0));

        this.add(blueBox, gbc.yP().wgt(1.0).insets("1", 20, centralBoxLateralDownPadding).fill("BOTH"));
    }
    
    private void init() {
    	initialList = setInitialList();
    	columnNames = setColumnNames();
    	rowMapper = setRowMapper();
    	doubleClickAction = setDoubleClickAction();
    	searchItems = setSearchMethod();
    	
        //Inicializando o componente itemListComponent
        itemListComponent = new GenericJScrollPaneList<T>(initialList, columnNames, rowMapper, doubleClickAction);
    	setPopUpOptions();
    	itemListComponent.init();
    }
    
    protected void setCentralBoxLateralPadding(int value) {
        this.centralBoxLateralDownPadding = value;
    }
    
    protected void setCreateBtn(Action createFunction) {
        StdButton createOne = new StdButton(new LanguageUtil("Criar novo", "Create New").get(), createFunction)
                                .setColors(Color.white, STD_BLUE_COLOR)
                                .setUpFont(STD_BOLD_FONT.deriveFont(15f))
                                .setUpSize(new Dimension(150, 35));
        this.add(createOne, gbc.grid(0).anchor("EAST").insets("4", centralBoxLateralDownPadding, 0).wgt(0).fill("NONE"));
    }
    
    private void performSearch() {
        String searchField = (String) comboBox.getSelectedItem();
        String searchTerm = inputSearchBox.getText();
        List<T> results = searchItems.apply(searchField, searchTerm);
        updateItemList(results);
    }
    
    public void updateList() {
    	performSearch();
    }

	/**
     * MÃ©todo que representa o conjunto inicial de resultados da lista.
     * @return Lista com os objetos da lista.
     */
    protected abstract List<T> setInitialList();
    
    
    /**
     * MÃ©todo que representa a chamada de pesquisa dos objetos na lista.
     * @param searchField - coluna no banco de dados
     * @param searchTerm - termo a ser procurado
     * @return Lista com os objetos que satisfaÃ§am a condiÃ§Ã£o.
     */
    protected abstract BiFunction<String, String, List<T>> setSearchMethod();
    
    /**
     * MÃ©todo que representa o nome das colunas na lista de exibiÃ§Ã£o.
     * <br>Para implementar faÃ§a um return de um array de Strings que representam as colunas a serem exibidas.
     * @return Objeto de Strings
     */
    protected abstract Object[] setColumnNames();    
    
    protected abstract Function<T, Object[]> setRowMapper();
    
    protected void createPopUpOption(String text, Function<T, Runnable> action) {
    	itemListComponent.addPopUpOption(text, action);
    }
    
    protected void createDeleteItemPopUpOption(String text, Function<T, Supplier<Boolean>> additionalAction) {
    	itemListComponent.addDeleteItemPopUpOption(text, additionalAction);
    }
    
    protected abstract void setPopUpOptions();
    
    protected abstract Function<T, Runnable> setDoubleClickAction();

    public boolean updateItemList(List<T> newList) {
        try {
            // Atualiza a lista de origem
            itemListComponent.setOriginList(newList)
                             .buildTableModel()
                             .init(); // init chama configureTable

            // Revalida e repinta a pÃ¡gina para aplicar as alteraÃ§Ãµes
            this.revalidate();
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}