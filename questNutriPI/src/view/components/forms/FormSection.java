package view.components.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import utils.view.LanguageUtil;
import view.components.buttons.StdButton;
import view.components.generics.GenericComponent;
import view.components.generics.GenericJPanel;

/**
 * Classe FormSection representa uma seção de formulário com várias linhas de inputs.
 * <br>Usa a fluent interface para permitir a configuração sequencial de propriedades.
 */
public class FormSection extends GenericComponent {
    private static final long serialVersionUID = 1L;

    private List<List<FormBoxInput>> rows = new ArrayList<List<FormBoxInput>>();
    private String sessionName;

    private Dimension interactionBtnSize = new Dimension(75, 25);
    private StdButton interactionBtn;

    private int lateralDistance = 15;
    private int upperDistance = 15;
    private int lowerDistance = 15;
    
    private int nameUpperDistance = 5;
    private int nameLowerDistance = 5;
    
    private Integer fieldLeftDistance = null;
    private Integer fieldRightDistance = null;
    
    private boolean showRequiredLbl;
    
    private boolean hideBorder = false;
    
    private Color internalColor;

    /**
     * Construtor da classe FormSection.
     * Inicializa a seção de formulário com o painel pai.
     *
     * @param ownerPanel - O painel que contém esta seção de formulário.
     */
    public FormSection(GenericJPanel ownerPanel) {
        super(ownerPanel);
        ltGridBag();
        setBackground(STD_WHITE_COLOR);
        showRequiredLbl = true;

    }

    /**
     * Adiciona uma linha de FormBoxInput à seção de formulário.
     *
     * @param formBoxInputs - Os FormBoxInput a serem adicionados na linha.
     * @return O próprio objeto FormSection para permitir fluent interface.
     */
    public FormSection addRow(FormBoxInput ...formBoxInputs) {
        List<FormBoxInput> holderList = new ArrayList<FormBoxInput>();
        for(FormBoxInput box: formBoxInputs) {
            holderList.add(box);
        }

        this.rows.add(holderList);
        return this;
    }

    /**
     * Define o nome da seção.
     *
     * @param name - O nome da seção.
     * @return O próprio objeto FormSection para permitir fluent interface.
     */
    public FormSection setUpName(String name) {
        this.sessionName = name;
        return this;
    }

    /**
     * Define o botão de interação para a seção de formulário.
     *
     * @param btn - O botão de interação.
     * @return O próprio objeto FormSection para permitir fluent interface.
     */
    public FormSection setInteractBtn(StdButton btn) {
        this.interactionBtn = btn;
        if(interactionBtn != null) {
            interactionBtn.setPreferredSize(interactionBtnSize);
            interactionBtn.setMaximumSize(interactionBtnSize);
        }
        return this;
    }

    /**
     * Define a distância superior para os componentes dentro da seção de formulário.
     *
     * @param distance - A distância superior.
     * @return O próprio objeto FormSection para permitir fluent interface.
     */
    public FormSection setUpperDistance(int distance) {
        this.upperDistance = distance;
        return this;
    }
    
    /**
     * Define a distância inferior para os componentes dentro da seção de formulário.
     *
     * @param distance - A distância inferior.
     * @return O próprio objeto FormSection para permitir fluent interface.
     */
    public FormSection setLowerDistance(int distance) {
        this.lowerDistance = distance;
        return this;
    }
    
    /**
     * Define a distância lateral para os componentes dentro da seção de formulário.
     *
     * @param distance - A distância lateral.
     * @return O próprio objeto FormSection para permitir fluent interface.
     */
    public FormSection setLateralDistance(int distance) {
        this.lateralDistance = distance;
        return this;
    }

    /**
     * Define a distância superior para o nome da seção.
     *
     * @param distance - A distância superior.
     * @return O próprio objeto FormSection para permitir fluent interface.
     */
    public FormSection setNameUpperDistance(int distance) {
        this.nameUpperDistance = distance;
        return this;
    }
    
    /**
     * Define a distância inferior para o nome da seção.
     *
     * @param distance - A distância inferior.
     * @return O próprio objeto FormSection para permitir fluent interface.
     */
    public FormSection setNameLowerDistance(int distance) {
        this.nameLowerDistance = distance;
        return this;
    }
    
    public FormSection hideRequiredLbl() {
        this.showRequiredLbl = false;
        return this;
    }
    
    public FormSection setInternalColor(Color color) {
    	this.internalColor = color;
    	return this;
    }
    
    public FormSection hideBorder() {
    	this.hideBorder = true;
    	return this;
    }
    
    public FormSection setAllFieldsLateralDistance(Integer left, Integer right) {
    	if(left != null) fieldLeftDistance = left;
    	if(right != null) fieldRightDistance = right;
    	
    	return this;
    }

    /**
     * Método de inicialização visual do FormSection.
     * Este deve ser o <b>último</b> método chamado pela fluent interface.
     * Deixar de chamar este método não resultará em erros, mas não será exibida a parte gráfica do objeto.
     *
     * @details
     * O objeto não é inicializado graficamente quando é instanciado pois, com a implementação
     * da fluent interface, é esperado a chamada sucessiva de métodos para definir suas configurações
     * gráficas. Se as partes gráficas do objeto fossem criadas no momento da instanciação, não seria
     * possível aplicar fluent interface sem aplicar sucessivas atualizações do objeto, em cada um dos métodos,
     * o que poderia reduzir drasticamente a performance da aplicação.
     *
     * @return O próprio objeto FormSection para permitir fluent interface.
     */
    public FormSection init() {
        GenericJPanel panel = new GenericJPanel().ltGridBag();
        panel.setBackground(Color.white);
        panel.gbc.fill("BOTH").wgt(1.0, 0).grid(0).anchor("NORTHWEST");  //Layout inicial DO FORM
        if(sessionName != null) {
            JLabel upperName = new JLabel(sessionName, JLabel.CENTER);
            upperName.setFont(STD_BOLD_FONT.deriveFont(20f));

            panel.gbc.insets(nameUpperDistance, lateralDistance, nameLowerDistance, lateralDistance); //Distância superior dos forms.
            panel.add(upperName, panel.gbc);

            if(interactionBtn != null) {
                panel.add(interactionBtn, 
                          panel.gbc.anchor("EAST")
                                   .wgt(0)
                                   .fill("NONE")
                                   .insets(
                                           nameUpperDistance, 
                                           lateralDistance, 
                                           nameLowerDistance, 
                                           lateralDistance
                                    )
                );
                panel.gbc.fill("BOTH").wgt(1.0, 0).grid(0).anchor("NORTHWEST");
            }

            panel.gbc.yP();
        }
        panel.gbc.insets(0, lateralDistance);
        GenericJPanel allRowsPanel = new GenericJPanel().ltGridBag();
        if(!hideBorder) allRowsPanel.setBorder();
        allRowsPanel.setBackground(Color.red);
        allRowsPanel.gbc.fill("BOTH").wgt(1.0, 0).grid(0).anchor("NORTHWEST");
        for(int i = 0; i < rows.size(); i++) {
            GenericJPanel rowPanel = new GenericJPanel().ltGridBag();
            if(internalColor != null) rowPanel.setBackground(internalColor);
            rowPanel.gbc.grid(0).fill("BOTH").wgt(1.0).anchor("WEST"); //Layout inicial DA LINHA
            rowPanel.setPreferredSize(new Dimension(50, 75));
            for(FormBoxInput box: rows.get(i)) {
            	if(fieldLeftDistance != null) box.setLeftDistance(fieldLeftDistance);
            	if(fieldRightDistance != null) box.setRightDistance(fieldRightDistance);
            	box.init();
            	if(internalColor != null) box.setBackground(internalColor);
            	box.setPreferredSize(new Dimension(10, 10));
                rowPanel.add(box, rowPanel.gbc);
                rowPanel.gbc.xP();
            }
            allRowsPanel.add(rowPanel, allRowsPanel.gbc);
            allRowsPanel.gbc.yP();
        }
        panel.add(allRowsPanel, panel.gbc);
        if(showRequiredLbl) {
        	JLabel requiredLbl = new JLabel(new LanguageUtil("(*) = Campos obrigatórios.", "(*) = Required fields.").get());
            requiredLbl.setFont(STD_BOLD_FONT.deriveFont(9f));
            panel.add(requiredLbl, panel.gbc.yP().anchor("EAST").insets(0, lateralDistance, lowerDistance, lateralDistance).fill("NONE").wgt(1.0, 0));  
        }
        
        add(panel, gbc.grid(0).fill("BOTH").wgt(1.0).insets(upperDistance, lateralDistance, 0, lateralDistance));
        return this;
    }
}
