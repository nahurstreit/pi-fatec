package view.components.generics;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import utils.interfaces.GeneralVisualSettings;
import view.components.utils.StdGBC;

/**
 * Classe JPanel genérica que oferece funcionalidades adicionais como gerenciamento de proprietários, configurações visuais e layout.
 */
public class GenericJPanel extends JPanel implements GeneralVisualSettings {		
	private static final long serialVersionUID = 1L;
	
	public GenericJPanel ownerPanel;
	private GenericJFrame callerFrame;
	
	public StdGBC gbc = new StdGBC();
	
    /**
     * Construtor que cria um GenericJPanel genérico com um proprietário específico.
     *
     * @param ownerPanel GenericJPanel proprietário deste GenericJPanel
     */
	public GenericJPanel(GenericJPanel ownerPanel) {
		this.ownerPanel = ownerPanel;
		if(ownerPanel != null) {
			this.callerFrame = ownerPanel.callerFrame;
		}
	}
	
    /**
     * Construtor que cria um JPanel genérico sem proprietário.
     */
	public GenericJPanel() {
		this(null);
	}
	
    /**
     * Define o GenericJFrame que chamou essse GenericJPanel.
     *
     * @param frame GenericJFrame que chama este GenericJPanel
     * @return O próprio objeto para implementar fluente interface
     */
	public GenericJPanel setCallerFrame(GenericJFrame frame) {
		if(ownerPanel == null) {
			this.callerFrame = frame;
		}
		return this;
	}
	
    
    /**
     * Define o proprietário deste GenericJFrame.
     *
     * @param ownerPanel GenericJFrame que é proprietário deste GenericJFrame
     * @return O próprio objeto para implementar fluente interface
     */
	public GenericJPanel setOwner(GenericJPanel ownerPanel) {
		this.ownerPanel = ownerPanel;
		return this;
	}
	
    /**
     * Retorna o GenericJPanel proprietário deste GenericJPanel.
     *
     * @return GenericJPanel proprietário
     */
	public GenericJPanel getOwner() {
		return this.ownerPanel;
	}
	
    /**
     * Retorna o GenericJFrame que chama este JPanel.
     *
     * @return GenericJFrame chamador deste GenericJPanel
     */
	public GenericJFrame getCallerFrame() {
		return callerFrame;
	}
	
    /**
     * Define a cor de fundo deste GenericJPanel.
     *
     * @param color Cor a ser definida como fundo
     * @return O próprio objeto para implementar a interface fluente
     */
	public GenericJPanel setBGColor(Color color) {
		setBackground(color);
		return this;
	}
	
    /**
     * Retorna a lista de GenericJPanel proprietários deste GenericJPanel, incluindo ele mesmo.
     *
     * @return Lista de GenericJPanel proprietários
     */
	public List<GenericJPanel> getFamilyOwners() {
		List<GenericJPanel> family = new ArrayList<GenericJPanel>();
		GenericJPanel current = this;
		
		while(current.getOwner() != null) {
			current = current.getOwner();
			family.add(current);
		}
		
		return family;
	}
	
	/**
	 * Método para rápida definição de layout como gridBagLayout;
	 * @return o próprio objeto para implementar fluent interface
	 */
	public GenericJPanel ltGridBag() {
		this.setLayout(new GridBagLayout());
		return this;
	}
	
    /**
     * Define uma borda preta simples para este GenericJPanel.
     *
	 * @return o próprio objeto para implementar fluent interface
     */
	public GenericJPanel setBorder() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		return this;
	}
	
    /**
     * Define uma borda com a cor especificada para este GenericJPanel.
     *
     * @param color Cor da borda a ser definida
	 * @return o próprio objeto para implementar fluent interface
     */
	public GenericJPanel setBorder(Color color) {
		setBorder(BorderFactory.createLineBorder(color));
		return this;
	}
	
    /**
     * Atualiza este GenericJPanel repintando e revalidando-o.
     */
	public void refresh() {
		this.revalidate();
		this.repaint();
	}
	
    /**
     * Retorna uma representação em formato de string deste GenericJPanel, incluindo informações sobre o GenericJFrame chamador e os GenericJPanel proprietários.
     *
     * @return Representação em string deste GenericJPanel
     */
	public String toString() {
		String res = "";
		String nameClass = this.getClass().toString().replaceAll(".*\\.", "");
		List<GenericJPanel> family = this.getFamilyOwners();
		String familyStr = "";
		for(int i = family.size() - 1; i >= 0; i--) {
			familyStr += "\n";
			int controllerSpaces = family.size() - i - 1;
			if(controllerSpaces != 0) {
				familyStr += giveTabSpace(controllerSpaces, (2 * (controllerSpaces)) - 1) + "|\n";
				familyStr += giveTabSpace(controllerSpaces, (2 * (controllerSpaces)) - 1) + "+-->";
			}

			familyStr += "("+ (family.size() - i) +") "+family.get(i).getClass().toString().replaceAll(".*\\.", "");
		}
		
		try {
			res = "Father Frame: "+getCallerFrame()
					+ "\nClass name: " + nameClass
					+ "\nFamily Owners Tree: " + familyStr
					+ "\n";
		} catch (Exception e) {
			res = "Untrackable!";
		}
		
		return res;
	}
	
    /**
     * Retorna uma sequência de espaços formatada para tabulação.
     *
     * @param quantity Quantidade de espaços de tabulação
     * @param spaces Tamanho dos espaços
     * @return Sequência de espaços formatada
     */
	private String giveTabSpace(int quantity, int ...spaces) {
		int space = 1;
		if(spaces.length > 0) {
			if(spaces[0] >= 1) space = spaces[0];
		}
		String response = "";
		
		for(int i = 0; i < space; i++) {
			for(int j = 0; j < quantity; j++) {
				response += " ";
			}
		}
		
		return response;
	}
	
	
}