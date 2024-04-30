package view.components;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class HintPasswordInputField extends HintInputField {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hint;
    private boolean showingHint;
    private ArrayList<Character> realText = new ArrayList<Character>();
    private int currentCaret = 0;

    public HintPasswordInputField(String hint, Dimension size, float fontSize) {
    	super(hint, size, fontSize);
    	this.hint = hint;
    	this.setText(hint);
    	this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				boolean allowed = true;
				char[] blockedKeys = {
						 '',
						 '',
						 ''
				};
				
				for(char key: blockedKeys) {
					if(e.getKeyChar() == key) {
						allowed = false;
					}
				}
				
				if(allowed) {
					addChar(e.getKeyChar());
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				setHiddenText();
				setCaretPosition(currentCaret);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				
				switch(code) {
				case KeyEvent.VK_BACK_SPACE:
					try {
						if(realText.size() != 0) {
							if(currentCaret != realText.size()) {
								deleteChar(false);
								
							} else {
								realText.remove(realText.size() - 1);
								currentCaret--;
//								System.out.println(getRealText());
							}
						}
					} catch (IndexOutOfBoundsException e2) {
						System.out.println("String vazia");
					}
					setHiddenText();
					break;
				case KeyEvent.VK_LEFT:
					if(currentCaret - 1 >= 0) currentCaret--;
					break;
				case KeyEvent.VK_RIGHT:
					if(currentCaret + 1 <= realText.size()) currentCaret++;
					break;
				case KeyEvent.VK_CAPS_LOCK:
					break;
				case KeyEvent.VK_DELETE:
					if(realText.size() != 0) {
						if(currentCaret != realText.size()) {
							deleteChar(true);
						}
					}
					break;
				default:					
				}

			}
		});
    }
    
    private void setHiddenText() {
    	String shownText = "";
    	for(int i = 0; i < realText.size(); i++) shownText += "*";
    	super.setText(shownText);
    }
    
    private String getRealText() {
    	String text = "";
    	for(int i = 0; i < realText.size(); i++) text += realText.get(i);
    	return text;
    }
    
    private void deleteChar(boolean... delKey) {
    	boolean delMode = false;
    	if(delKey.length > 0) delMode = delKey[0];
    	ArrayList<Character> beforeHolder;
		ArrayList<Character> afterHolder;
		
		if(delMode) {
			beforeHolder = generateList(realText, 0, currentCaret);
			afterHolder = generateList(realText, currentCaret + 1, realText.size());
		} else {
			beforeHolder = generateList(realText, 0, currentCaret - 1);
			afterHolder = generateList(realText, currentCaret, realText.size());
		}
		
		System.out.println("Before: " + beforeHolder.toString());
		System.out.println("After: "+afterHolder.toString());

		realText = new ArrayList<Character>();
		System.out.println("RealText: "+getRealText());
	
		realText.addAll(beforeHolder);
		realText.addAll(afterHolder);

		System.out.println(getRealText());
    }
    
    private void addChar(char c) {
    	if(currentCaret != realText.size()) {
        	ArrayList<Character> beforeHolder;
    		ArrayList<Character> afterHolder;
    		
			beforeHolder = generateList(realText, 0, currentCaret);
			afterHolder = generateList(realText, currentCaret, realText.size());
			
			beforeHolder.add(c);
			
			realText = new ArrayList<Character>();
			realText.addAll(beforeHolder);
			realText.addAll(afterHolder);
		
    	} else {
    		realText.add(c);

    	}
		currentCaret++;
		System.out.println(getRealText());
    }
    
    
    private ArrayList<Character> generateList(ArrayList<Character> originList, int startIndex, int endIndex) {
    	ArrayList<Character> holderList = new ArrayList<Character>();
    	for(int i = startIndex; i < endIndex; i++) {
    		holderList.add(originList.get(i));
    	}
    	return holderList;
    }

}