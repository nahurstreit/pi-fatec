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

/**
 * Página genérica para exibir listas de itens com funcionalidades de pesquisa e interação.
 *
 * @param <T> Tipo genérico dos itens na lista.
 */
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

    
    /**
     * Construtor da página genérica de lista.
     *
     * @param ownerPanel    Painel proprietário onde esta página será exibida.
     * @param searchOptions Opções para o JComboBox de tipo de pesquisa.
     * @param pageTitle     Título da página.
     */
    public GenericListPage(GenericJPanel ownerPanel, String[] searchOptions, String pageTitle) {
        super(ownerPanel);
        this.ltGridBag();
        init();

        GenericComponent blueBox = new GenericComponent();
        blueBox.setBackground(STD_BLUE_COLOR);
        blueBox.ltGridBag();

        // Caixa de cima do painel branco
        whiteBox = new GenericComponent();
        whiteBox.setBackground(STD_WHITE_COLOR);
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
        upperBox.setBackground(STD_WHITE_COLOR);
        upperBox.ltGridBag();
        upperBox.add(typeBox, upperBox.gbc.wgt(0).fill("NONE").insets(10));
        upperBox.add(searchBox, upperBox.gbc.wgt(1.0, 0).fill("BOTH").insets("2", 0, 10));

        whiteBox.add(upperBox, whiteBox.gbc.anchor("NORTH").wgt(1.0, 0).fill("HORIZONTAL"));

        whiteBox.add(itemListComponent, whiteBox.gbc.gridY(1).anchor("NORTH").wgt(1.0).fill("BOTH").insets(10, 0, 0, 0));

        blueBox.add(whiteBox, blueBox.gbc.anchor("NORTHWEST").wgt(1.0).fill("BOTH").insets(30));

        JLabel pageLbl = new JLabel(pageTitle, JLabel.CENTER);
        pageLbl.setFont(STD_BOLD_FONT.deriveFont(40f));
        this.add(pageLbl, gbc.grid(0).wgt(1.0, 0).fill("HORIZONTAL").anchor("CENTER").width("REMAINDER"));

        this.add(blueBox, gbc.yP().wgt(1.0).insets("1", 20, centralBoxLateralDownPadding).fill("BOTH").width("REMAINDER"));
    }
    
    /**
     * Método de inicialização dos componentes da página.
     */
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
    
    /**
     * Método para configurar o padding lateral da caixa central.
     *
     * @param value Valor do padding lateral.
     */
    protected void setCentralBoxLateralPadding(int value) {
        this.centralBoxLateralDownPadding = value;
    }
    
    /**
     * Define um botão de criação na página com ação específica.
     *
     * @param createFunction Ação a ser executada ao clicar no botão.
     */
    protected void setCreateBtn(Action createFunction) {
        StdButton createOne = new StdButton(new LanguageUtil("Criar novo", "Create New").get(), createFunction)
                                .setColors(Color.white, STD_BLUE_COLOR)
                                .setUpFont(STD_BOLD_FONT.deriveFont(15f))
                                .setUpSize(new Dimension(150, 35));
        this.add(createOne, gbc.grid(1, 0).anchor("EAST").insets("4", centralBoxLateralDownPadding, 0).wgt(0).fill("NONE").width(1));
    }
    
    /**
     * Realiza a pesquisa na lista com base no termo de pesquisa atual.
     */
    private void performSearch() {
        String searchField = (String) comboBox.getSelectedItem();
        String searchTerm = inputSearchBox.getText();
        List<T> results = searchItems.apply(searchField, searchTerm);
        updateItemList(results);
    }
    
    /**
     * Método para forçar a atualização da lista.
     */
    public void updateList() {
    	performSearch();
    }
    
    /**
     * Método para atualizar a lista de itens exibidos na página.
     *
     * @param newList Nova lista de itens a ser exibida.
     * @return true se a atualização foi bem sucedida, false caso contrário.
     */
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

    /**
     * Método abstrato para definir a lista inicial de itens a ser exibida.
     *
     * @return Lista inicial de objetos do tipo T.
     */
    protected abstract List<T> setInitialList();
    
    
    /**
     * Método abstrato para definir o método de pesquisa de itens na lista.
     *
     * @return BiFunction que define como buscar itens na lista com base no campo e termo de pesquisa.
     */
    protected abstract BiFunction<String, String, List<T>> setSearchMethod();
    
    /**
     * Método abstrato para definir os nomes das colunas na lista de exibição.
     *
     * @return Array de Strings com os nomes das colunas.
     */
    protected abstract Object[] setColumnNames();    
    
    /**
     * Método abstrato para definir o mapeamento de objetos para linhas na tabela.
     *
     * @return Função que mapeia objetos do tipo T para um array de objetos para exibição.
     */
    protected abstract Function<T, Object[]> setRowMapper();
    
    /**
     * Método abstrato para definir a ação a ser executada ao duplo clique em um item da lista.
     *
     * @return Função que define a ação a ser executada ao duplo clique em um item.
     */
    protected abstract Function<T, Runnable> setDoubleClickAction();
    
    /**
     * Método abstrato para definir as opções de menu de contexto para itens da lista.
     */
    protected abstract void setPopUpOptions();
    
    /**
     * Método para criar uma opção de menu de contexto customizada.
     *
     * @param text   Texto da opção de menu.
     * @param action Ação a ser executada ao selecionar a opção.
     */
    protected void createPopUpOption(String text, Function<T, Runnable> action) {
    	itemListComponent.addPopUpOption(text, action);
    }
    
    /**
     * Método para criar uma opção de menu de contexto para deletar itens com ação adicional.
     *
     * @param text            Texto da opção de menu.
     * @param additionalAction Ação adicional a ser executada ao selecionar a opção de deletar.
     */
    protected void createDeleteItemPopUpOption(String text, Function<T, Supplier<Boolean>> additionalAction) {
    	itemListComponent.addDeleteItemPopUpOption(text, additionalAction);
    }


}