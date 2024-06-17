package view.frames;

import java.awt.Dimension;

import view.components.generics.GenericJFrame;

public abstract class SubFrame extends GenericJFrame {
	private static final long serialVersionUID = 1L;

	public SubFrame(GenericJFrame callerFrame, Dimension size) {
		super(size);
		setCallerFrame(callerFrame);
	}

}