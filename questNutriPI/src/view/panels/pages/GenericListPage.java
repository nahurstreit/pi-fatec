package view.panels.pages;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.function.Function;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.components.HintInputField;
import view.components.StdButton;
import view.components.StdButton.Action;
import view.panels.components.GenericComponent;
import view.panels.components.GenericJPanel;

public abstract class GenericListPage<T> extends GenericPage {
    private static final long serialVersionUID = 1L;

    protected GenericComponent whiteBox;
    protected JScrollPane itemList = null;
    protected JComboBox<String> comboBox;
    protected HintInputField inputSearchBox;
    
    /**
     * Variável para controlar a distância lateral e inferior do central box;
     */
    private int centralBoxLateralDownPadding = 40;

    public GenericListPage(GenericJPanel ownerPanel, String[] searchOptions, String pageTitle, Function<List<T>, JScrollPane> listFetcher) {
        super(ownerPanel);
        this.ltGridBag();

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
        JLabel lblTypeBox = new JLabel("Pesquisar por");
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
        
     // Adicionando um ActionListener ao comboBox para limpar o inputSearchBox quando uma nova seleção for feita
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
        JLabel lblSearchBox = new JLabel("Termo de Pesquisa");
        lblSearchBox.setFont(STD_BOLD_FONT.deriveFont(20f));
        searchBox.add(lblSearchBox, searchBox.gbc.grid(0, 0).wgt(1.0).fill("BOTH").insets("3", 0, 10));

        // InputBox tipo de pesquisa
        inputSearchBox = new HintInputField("", new Dimension(100, 25), 15f);
        
        // Adicionar FocusListener ao inputSearchBox
        inputSearchBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Não precisa fazer nada quando o foco é ganho
            }

            @Override
            public void focusLost(FocusEvent e) {
                inputSearchBox.setFocusable(false);
                SwingUtilities.invokeLater(() -> inputSearchBox.setFocusable(true));
            }
        });
        
     // Adicionar MouseListener ao inputSearchBox para mover o caret
        inputSearchBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    inputSearchBox.setCaretPosition(inputSearchBox.getText().length());
                });
            }
        });
        
        // Adicionar o DocumentListener ao inputSearchBox
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

        itemList = listFetcher.apply(fetchInitialList());

        whiteBox.add(itemList, whiteBox.gbc.gridY(1).anchor("NORTH").wgt(1.0).fill("BOTH").insets(10));

        blueBox.add(whiteBox, blueBox.gbc.anchor("NORTHWEST").wgt(1.0).fill("BOTH").insets(30));

        JLabel pageLbl = new JLabel(pageTitle);
        pageLbl.setFont(STD_BOLD_FONT.deriveFont(40f));
        this.add(pageLbl, gbc.grid(0));

        this.add(blueBox, gbc.yP().wgt(1.0).insets("1", 20, centralBoxLateralDownPadding).fill("BOTH"));
    }
    
    protected void setCentralBoxLateralPadding(int value) {
    	this.centralBoxLateralDownPadding = value;
    }
    
    protected void setCreateBtn(Action createFunction) {
        StdButton createOne = new StdButton("Criar novo", createFunction)
        						.setColors(Color.white, STD_BLUE_COLOR)
    							.setUpFont(STD_BOLD_FONT.deriveFont(15f))
    							.setUpSize(new Dimension(150, 35));
        this.add(createOne, gbc.grid(0).anchor("EAST").insets("4", centralBoxLateralDownPadding, 0).wgt(0).fill("NONE"));
    }

    protected abstract void performSearch();

    protected abstract List<T> fetchInitialList();
    
    protected abstract List<T> searchItems(String searchField, String searchTerm);

    public boolean setItemList(JScrollPane list) {
        try {
            // Remove o componente existente da página
            this.whiteBox.remove(itemList);

            // Atualiza a referência para o novo componente
            this.itemList = list;

            // Adiciona o novo componente à página
            this.whiteBox.add(itemList, whiteBox.gbc.gridY(1).anchor("NORTH").wgt(1.0).fill("BOTH").insets(10));

            // Revalida e repinta a página para aplicar as alterações
            this.revalidate();
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
