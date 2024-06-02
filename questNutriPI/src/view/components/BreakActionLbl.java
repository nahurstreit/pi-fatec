package view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import view.components.utils.IDoAction;

public class BreakActionLbl extends JTextPane {

    private static final long serialVersionUID = 1L;
    private IDoAction event;
    
    private String text;

    private MouseAdapter mouseHover = new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        public void mouseExited(MouseEvent e) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    };
    
    public BreakActionLbl() {
    	this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (event != null) {
                    event.execute();
                }
            }
        });
    	this.turnHoverOn();
    	this.setEditable(false);
    	this.setCaretColor(Color.WHITE);
    }
    
    public BreakActionLbl init() {
    	this.setText(text);
    	return this;
    }
    
    public BreakActionLbl setUpText(String text) {
    	this.text = text;
    	return this;
    }

    public BreakActionLbl setAction(IDoAction event) {
        this.event = event;
        return this;
    }

    public BreakActionLbl setUpFont(Font font) {
        setFont(font);
        setText(text);
        return this;
    }

    public BreakActionLbl setUpColor(Color color) {
        setForeground(color);
        return this;
    }
    
    public BreakActionLbl centerText() {
        StyledDocument doc = getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        return this;
    }

    public BreakActionLbl turnHoverOff() {
        try {
            removeMouseListener(mouseHover);
        } catch (Exception e) {
            System.out.println(e);
        }
        return this;
    }

    public BreakActionLbl turnHoverOn() {
        addMouseListener(mouseHover);
        return this;
    }
}
