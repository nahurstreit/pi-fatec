package view.components.generics;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import utils.interfaces.IAssetsLoad;

/**
 * Classe que define um componente JFrame personalizado para o sistema QuestNutri.
 */
public class GenericJFrame extends JFrame implements IAssetsLoad {
	private static final long serialVersionUID = 1L;

	/**
	 * Nome do frame.
	 */
	protected String frameName;
	
	/**
	 * Objeto GenericJFrame que chamou esse objeto GenericJFrame
	 */
	private GenericJFrame callerFrame;
	
	/**
	 * Array contendo todos os frames que foram chamados por esse GenericJFrame. Isso é feito para que quando
	 * o GenericJFrame pai seja fechado, os GenericJFrame que foram chamados a partir desse, fechem junto.
	 */
	private List<GenericJFrame> stackFrames;
	
	/**
	 * Método construtor que define o tamanho da janela.
	 * @param size - Tamanho da janela
	 */
	public GenericJFrame(Dimension size) {
		if(size != null) {
			setSize(size);
			setMinimumSize(size);
		}
		setIconImage(FRAME_ICON);
		stackFrames = new ArrayList<GenericJFrame>();
		closeStack();
	}
	
	/**
	 * Constructor padrão.
	 */
	public GenericJFrame() {
		this(null);
	}
	
	/**
	 * Método que adiciona um outro objeto GenericJFrame como propriedade deste objeto GenericJFrame. Para que, quando este GenericJFrame seja fechado
	 * os demais GenericJFrame associados a essse, sejam fechados também.
	 * @param frame - GenericJFrame a ser associado a este objeto.
	 * @return O próprio objeto GenericJFrame para implementar fluent interface.
	 */
	public GenericJFrame addStackFrame(GenericJFrame frame) {
		stackFrames.add(frame);
		return this;
	}
	
	/**
	 * Método que define o GenericJFrame que chamou este objeto GenericJFrame.
	 * @param frame - GenericJFrame que chamou este objeto.
	 * @return O próprio objeto GenericJFrame para implementar fluent interface.
	 */
	public GenericJFrame setCallerFrame(GenericJFrame frame) {
		try {
			this.callerFrame = frame;
			callerFrame.addStackFrame(this);
		} catch (Exception e) {
		}

		return this;
	}
	
	/**
	 * Obtém qual é o GenericJFrame que chamou este outro GenericJFrame.
	 * @return GenericJFrame que chamou este objeto.
	 */
	public GenericJFrame getCallerFrame() {
		return this.callerFrame;
	}
	
	/**
	 * Método que chama o dispose de todos os GenericJFrame que estejam associados a este objeto.
	 */
	private void closeStack() {
		this.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        for(GenericJFrame frame: stackFrames) {
		        	frame.dispose();
		        }
		        stackFrames.clear();
		    }
		});
	}
	
	/**
	 * Método que define esse frame como o encerrador da aplicação.
	 * @return O próprio objeto GenericJFrame para implementar fluent interface.
	 */
	public GenericJFrame setDefaultCloseApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return this;
	}
	
	/**
	 * Método que define o título de exibiçaõ deste frame.
	 * @param title - String do título
	 * @return O próprio objeto GenericJFrame para implementar fluent interface.
	 */
	public GenericJFrame setUpTitle(String title) {
		setTitle(title);
		return this;
	}
	
	/**
	 * Método que define o nome de referência deste objeto. Serve apenas para track em programação.
	 * @param name - String do nome.
	 * @return O próprio objeto GenericJFrame para implementar fluent interface.
	 */
	public GenericJFrame setFrameName(String name) {
		this.frameName = name;
		return this;
	}
	
	/**
	 * Volta o nome do frame quando for chamado.
	 */
	@Override
	public String toString() {
		return frameName;
	}
}