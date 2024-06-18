package view.frames;

import java.awt.Dimension;

import view.components.generics.GenericJFrame;

/**
 * Classe abstrata que representa um subframe dentro da aplicação. Extende GenericJFrame para gerenciar as características do frame.
 */
public abstract class SubFrame extends GenericJFrame {
	private static final long serialVersionUID = 1L;

    /**
     * Construtor para inicializar um SubFrame com um tamanho específico e um frame chamador opcional.
     * @param callerFrame O frame chamador que invocou este subframe.
     * @param size A dimensão (largura e altura) do subframe.
     */
	public SubFrame(GenericJFrame callerFrame, Dimension size) {
		super(size);
		setCallerFrame(callerFrame);
	}

}