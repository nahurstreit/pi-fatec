package view.components.labels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import view.components.utils.IDoAction;

public class BreakActionLbl extends JTextPane {

    private static final long serialVersionUID = 1L;
    private IDoAction event;
    private boolean hover;

    private String text;

    public BreakActionLbl() {
        this.setEditable(false);
        hover = true;
        this.setCaretColor(this.getBackground());
        this.turnHoverOn();
    }

    /**
     * Inicializa a label corretamente com as definições de cor e fundo.
     * @return
     */
    public BreakActionLbl init() {
        this.setText(text);
        return this;
    }

    public BreakActionLbl setUpText(String text) {
        this.text = text;
        return this;
    }

    public BreakActionLbl setAction(IDoAction event) {
        if (this.event != null) {
            removeClickListener();
        }
        this.event = event;
        setEvent();
        return this;
    }

    private void setEvent() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (event != null) {
                    event.execute();
                }
            }
        });
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
    
    public BreakActionLbl setBGColor(Color color) {
        setBackground(color);
        this.setCaretColor(color);
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
        if (hover) {
            removeHoverListener();
            hover = false;
        }
        return this;
    }

    public BreakActionLbl turnHoverOn() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        hover = true;
        
        return this;
    }

    private void removeHoverListener() {
        MouseListener[] listeners = this.getMouseListeners();
        for (MouseListener listener : listeners) {
            if (listener instanceof MouseAdapter) {
                this.removeMouseListener(listener);
            }
        }
    }

    private void removeClickListener() {
        MouseListener[] listeners = this.getMouseListeners();
        for (MouseListener listener : listeners) {
            if (listener instanceof MouseAdapter) {
                this.removeMouseListener(listener);
            }
        }
    }
}
