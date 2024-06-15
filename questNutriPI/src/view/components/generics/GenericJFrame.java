package view.components.generics;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import utils.interfaces.IAssetsLoad;

public class GenericJFrame extends JFrame implements IAssetsLoad {
	private static final long serialVersionUID = 1L;

	protected String frameName;
	private GenericJFrame callerFrame;
	
	private List<GenericJFrame> stackFrames;
	
	public GenericJFrame(Dimension size) {
		if(size != null) {
			setSize(size);
			setMinimumSize(size);
		}
		setIconImage(FRAME_ICON);
		stackFrames = new ArrayList<GenericJFrame>();
		closeStack();
	}
	
	public GenericJFrame() {
		this(null);
	}
	
	public GenericJFrame addStackFrame(GenericJFrame frame) {
		stackFrames.add(frame);
		return this;
	}
	
	public GenericJFrame setCallerFrame(GenericJFrame frame) {
		this.callerFrame = frame;
		callerFrame.addStackFrame(this);
		return this;
	}
	
	public GenericJFrame getCallerFrame() {
		return this.callerFrame;
	}
	
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
	
	public GenericJFrame setDefaultCloseApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return this;
	}
	
	public GenericJFrame setUpTitle(String title) {
		setTitle(title);
		return this;
	}
	
	public GenericJFrame setFrameName(String name) {
		this.frameName = name;
		return this;
	}
	
	@Override
	public String toString() {
		return frameName;
	}
}