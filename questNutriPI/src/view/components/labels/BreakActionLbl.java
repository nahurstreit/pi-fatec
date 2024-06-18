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

import utils.interfaces.IDoAction;

/**
 * JTextPane personalizado que suporta eventos de clique, alterações visuais ao passar o mouse e formatação de texto.
 */
public class BreakActionLbl extends JTextPane {

    private static final long serialVersionUID = 1L;
    private IDoAction event;
    private boolean hover;

    private String text;

    /**
     * Constructor padrão.
     */
    public BreakActionLbl() {
        this.setEditable(false);
        hover = true;
        this.setCaretColor(this.getBackground());
        this.turnHoverOn();
    }

    /**
     * Inicializa a label corretamente com as definições de cor e fundo.
     * @return O próprio objeto para implementar fluent interface.
     */
    public BreakActionLbl init() {
        this.setText(text);
        return this;
    }

    /**
     * Define o texto a ser exibido no componente.
     *
     * @param text Texto a ser exibido no componente
     * @return O próprio objeto para implementar fluent interface.
     */
    public BreakActionLbl setUpText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Define a ação a ser executada quando o componente é clicado.
     *
     * @param event Ação a ser executada no clique
     * @return O próprio objeto para implementar fluent interface.
     */
    public BreakActionLbl setAction(IDoAction event) {
        if (this.event != null) {
            removeClickListener();
        }
        this.event = event;
        initAction();
        return this;
    }

    /**
     * Adiciona um listener de mouse para executar a ação quando o componente for clicado.
     */
    private void initAction() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (event != null) {
                    event.execute();
                }
            }
        });
    }

    /**
     * Configura a fonte do texto exibido no componente.
     *
     * @param font Fonte a ser configurada
     * @return O próprio objeto para implementar fluent interface.
     */
    public BreakActionLbl setUpFont(Font font) {
        setFont(font);
        setText(text);
        return this;
    }

    /**
     * Configura a cor do texto exibido no componente.
     *
     * @param color Cor do texto a ser configurada
     * @return O próprio objeto para implementar fluent interface.
     */
    public BreakActionLbl setUpColor(Color color) {
        setForeground(color);
        return this;
    }
    
    /**
     * Configura a cor de fundo do componente.
     *
     * @param color Cor de fundo a ser configurada
     * @return O próprio objeto para implementar fluent interface.
     */
    public BreakActionLbl setBGColor(Color color) {
        setBackground(color);
        this.setCaretColor(color);
        return this;
    }

    /**
     * Centraliza o texto dentro do componente.
     *
     * @return O próprio objeto para implementar fluent interface.
     */
    public BreakActionLbl centerText() {
        StyledDocument doc = getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        return this;
    }
    
    /**
     * Alinha o texto à esquerda dentro do componente.
     *
     * @return O próprio objeto para implementar fluent interface.
     */
    public BreakActionLbl alignTextLeft() {
        StyledDocument doc = getStyledDocument();
        SimpleAttributeSet left = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), left, false);
        return this;
    }

    /**
     * Desativa o efeito de alteração de cursor ao passar o mouse por cima do componente.
     *
     * @return O próprio objeto para implementar fluent interface.
     */
    public BreakActionLbl turnHoverOff() {
        if (hover) {
            removeHoverListener();
            hover = false;
        }
        return this;
    }

    /**
     * Ativa o efeito de alteração de cursor ao passar o mouse por cima do componente.
     *
     * @return O próprio objeto para implementar fluent interface.
     */
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

    /**
     * Remove o listener de hover do componente.
     */
    private void removeHoverListener() {
        MouseListener[] listeners = this.getMouseListeners();
        for (MouseListener listener : listeners) {
            if (listener instanceof MouseAdapter) {
                this.removeMouseListener(listener);
            }
        }
    }

    /**
     * Remove o listener de clique do componente.
     */
    private void removeClickListener() {
        MouseListener[] listeners = this.getMouseListeners();
        for (MouseListener listener : listeners) {
            if (listener instanceof MouseAdapter) {
                this.removeMouseListener(listener);
            }
        }
    }
}
