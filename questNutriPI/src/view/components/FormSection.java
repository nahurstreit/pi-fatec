package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import view.panels.components.GenericComponent;
import view.panels.components.GenericJPanel;

public class FormSection extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	private List<List<FormBoxInput>> rows = new ArrayList<List<FormBoxInput>>();
	private String sessionName;
	
	private Dimension interactionBtnSize = new Dimension(75, 25);
	private StdButton interactionBtn;

	private int lateralDistance = 15;
	private int nameUpperDistance = 20;
	private int nameDownDistance = 5;
	
	public FormSection(GenericJPanel ownerPanel) {
		super(ownerPanel);
		ltGridBag();
		setBackground(Color.white);
	}
	
	public FormSection addRow(FormBoxInput ...formBoxInputs) {
		List<FormBoxInput> holderList = new ArrayList<FormBoxInput>();
		for(FormBoxInput box: formBoxInputs) {
			holderList.add(box);
		}
		
		this.rows.add(holderList);
		return this;
	}
	
	public FormSection setUpName(String name) {
		this.sessionName = name;
		return this;
	}
	
	public FormSection setInteractBtn(StdButton btn) {
		this.interactionBtn = btn;
		if(interactionBtn != null) {
			interactionBtn.setPreferredSize(interactionBtnSize);
			interactionBtn.setMaximumSize(interactionBtnSize);
		}
		return this;
	}
	
	public FormSection setLateralDistance(int distance) {
		this.lateralDistance = distance;
		return this;
	}
	
	public FormSection setUpperDistance(int distance) {
		this.nameUpperDistance = distance;
		return this;
	}
	
	public FormSection setDownDistance(int distance) {
		this.nameDownDistance = distance;
		return this;
	}
	
	/**
	 * Método de inicialização visual do FormSection. Este deve ser o <b>último</b> método chamado
	 * pela fluent interface. Deixar de chamar este método não resultará em erros, mas não será exibido
	 * a parte gráfica do objeto.
	 * @details
	 * O objeto não é inicializado graficamente quando é instanciado pois, com a implementação
	 * do fluent interface, é esperado a chamada sucessiva de métodos para definir suas configurações
	 * gráficas. Se as partes gráficas do objeto fossem criadas no momento da instanciação, não seria
	 * possível aplicar fluent interface sem aplicar sucessivas atualizações do objeto, em cada um dos métodos,
	 * o que poderia reduzir drasticamente a performance da aplicação.
	 * @return Retorna o próprio objeto FormSection para implementação de fluent interface.
	 */
	public FormSection init() {
		GenericJPanel panel = new GenericJPanel().ltGridBag();
		panel.setBackground(Color.white);
		panel.gbc.fill("BOTH").wgt(1.0, 0).grid(0).anchor("NORTHWEST");  //Layout inicial DO FORM
		if(sessionName != null) {
			JLabel upperName = new JLabel(sessionName, JLabel.CENTER);
			upperName.setFont(STD_BOLD_FONT.deriveFont(20f));
			
			panel.gbc.insets(nameUpperDistance, 0, nameDownDistance, 0); //Distância superior dos forms.
			panel.add(upperName, panel.gbc);
			
			if(interactionBtn != null) {				
				panel.add(interactionBtn, 
						  panel.gbc.anchor("EAST")
						  		   .wgt(0)
						  		   .fill("NONE")
						  		   .insets(
						  				   nameUpperDistance, 
						  				   lateralDistance, 
						  				   nameDownDistance, 
						  				   nameUpperDistance
						  			)
				);
				panel.gbc.fill("BOTH").wgt(1.0, 0).grid(0).anchor("NORTHWEST");
			}
			
			panel.gbc.yP();
		}
		panel.gbc.insets(0, lateralDistance);
		GenericJPanel allRowsPanel = new GenericJPanel().ltGridBag().setBorder();
		allRowsPanel.gbc.fill("BOTH").wgt(1.0, 0).grid(0).anchor("NORTHWEST");
		for(int i = 0; i < rows.size(); i++) {
			GenericJPanel rowPanel = new GenericJPanel().ltGridBag();
			rowPanel.gbc.grid(0).fill("BOTH").wgt(1.0).anchor("WEST"); //Layout inicial DA LINHA
			rowPanel.setPreferredSize(new Dimension(50, 75));
			for(FormBoxInput box: rows.get(i)) {
				rowPanel.add(box, rowPanel.gbc);
				rowPanel.gbc.xP();
			}
			allRowsPanel.add(rowPanel, allRowsPanel.gbc);
			allRowsPanel.gbc.yP();
		}
		panel.add(allRowsPanel, panel.gbc);
		add(panel, gbc.fill("BOTH").wgt(1.0).insets(0, lateralDistance, nameDownDistance*3, lateralDistance));
		return this;
	}
	
}