package view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.components.utils.IDoAction;

public class ActionLbl extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IDoAction event;
	@SuppressWarnings("unused")
	private String text;
	@SuppressWarnings("unused")
	private ImageIcon img;
	
	private MouseAdapter mouseHover = new MouseAdapter() {			
		//Quando passar o mouse em cima, muda o cursor
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		
		//Quando tirar o mouse de cima, volta o cursor ao normal
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
	};
	
	public ActionLbl(String text, IDoAction event) {
		super(text);
		this.text = text;
		this.event = event;
		turnHoverOn();
		addAction();
	}
	
	public ActionLbl(String text, IDoAction event, int mode) {
		super(text, mode);
		this.text = text;
		this.event = event;
		turnHoverOn();
		addAction();
	}
	
	public ActionLbl(String text) {
		this(text, null);
	}
	
	public ActionLbl(ImageIcon img, IDoAction event) {
		super(img);
		this.img = img;
		this.event = event;
	}
	
	public ActionLbl(ImageIcon img) {
		this(img, null);
	}
	
	private void addAction() {
		this.addMouseListener(new MouseAdapter() {
			//Quando clicar aciona a ação enviada
			public void mouseClicked(MouseEvent e) {
		        if (event != null) {
		            event.execute();
		        }
			}
		});
	}
	
	public ActionLbl setNewAction(IDoAction event) {
		this.event = event;
		return this;
	}
	
	public ActionLbl setUpFont(Font font) {
		this.setFont(font);
		return this;
	}
	
	public ActionLbl setUpColor(Color color) {
		this.setForeground(color);
		return this;
	}
	
	public ActionLbl turnHoverOff() {
		try {
			this.removeMouseListener(mouseHover);
		} catch (Exception e) {
			System.out.println(e);
		}
		return this;
	}
	
	public ActionLbl turnHoverOn() {
		this.addMouseListener(mouseHover);
		return this;
	}
	
}