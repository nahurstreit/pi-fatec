package view.components.inputs;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import view.components.utils.IDoAction;

/**
 * Classe que estende HintInputField para controlar Inputs do Usuário que devem ser escondidos ao mesmo tempo que
 * fornece um texto de orientação de preenchimento chamado "hint".
 * @see view.components.inputs.HintInputField
 */
public class HintPasswordInputField extends HintInputField {
	private static final long serialVersionUID = 1L;
	
	private IDoAction enterAction;
	
    private ArrayList<Character> realText = new ArrayList<Character>(); //Variável que armazena o verdadeiro texto digitado pelo usuário
    private int currentCaret = 0; //Variável que controla o caret(barra de localização) durante a digitação.

	/**
	 * Método Construtor da classe para controlar inputs do usuário que devem ser escondidos ao mesmo tempo que fornece uma dica de preenchimento
	 * @param hint -> (String) Texto a ser exibido na dica
	 * @param size -> (Dimension) Instância de Dimension para controlar o tamanho do TextField
	 * @param fontSize -> (float) Tamanho de exibição da fonte durante o preenchimento
	 */
    public HintPasswordInputField(String hint, Dimension size, float fontSize) {
    	super(hint, size, fontSize);
    	this.setText(hint);
    }
    
    public HintPasswordInputField init() {
    	setListeners();
    	return this;
    }
    
    private void setListeners() {
    	this.addKeyListener(new KeyListener() { //Adiciona um Listener para teclas digitadas
			
			
			/**
			 * Override da ação quando uma tecla de texto ou especial é digitada. Exclui setas e teclas de Função (FX).
			 * @Override
			 */
			public void keyTyped(KeyEvent e) {
				boolean allowed = true; //Variável de controle de adição das teclas
				char[] blockedKeys = { //Array com as teclas bloqueadas no input
						KeyEvent.VK_BACK_SPACE, 
	                    KeyEvent.VK_DELETE, 
	                    KeyEvent.VK_ESCAPE, 
	                    KeyEvent.VK_ENTER,
	                    KeyEvent.VK_TAB,
	                    KeyEvent.VK_CAPS_LOCK,
	                    KeyEvent.VK_WINDOWS,
				};
				
				for(char key: blockedKeys) { //Verifica se a tecla digitada é bloqueada
					if(e.getKeyChar() == key) {
						allowed = false;
					}
				}
				
				if(allowed) {
					addChar(e.getKeyChar()); //Se for permitido aquela tecla, chama o método que adiciona as teclas ao texto real
				}
			}
			
			/**
			 * Override da ação ao liberar uma tecla.
			 * @Override
			 */
			public void keyReleased(KeyEvent e) {
				setHiddenText();
				setCaretPosition(currentCaret);
			}
			
			/**
			 * Override da ação de pressionar qualquer tecla.
			 * @Override
			 */
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				
				switch(code) { //Switch para atribuir diferentes efeitos conforme teclas são clicadas.
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
				case KeyEvent.VK_ENTER:
					enterAction.execute();
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
    
    public HintPasswordInputField setEnterAction(IDoAction action) {
    	enterAction = action;
    	return this;
    }
    
    /**
     * Método que esconde o texto digitado por caracteres '*'
     */
    private void setHiddenText() {
    	String shownText = "";
    	for(int i = 0; i < realText.size(); i++) shownText += "*";
    	super.setText(shownText);
    }
    
    /**
     * Método que recupera o verdadeiro texto digitado pelo usuário
     * @return <b>String</b> -> Representa o verdadeiro texto que o usuário estava digitando.
     */
    public String getRealText() {
    	String text = "";
    	for(int i = 0; i < realText.size(); i++) text += realText.get(i);
    	return text;
    }
    
    /**
     * Método geral que deleta os caracteres anteriormente digitados com a tecla backspace ou delete.
     * @param delKey -> Valor booleano que indica se a tecla digitada foi DEL. As teclas Backspace e
     * DEL usam a mesma lógica, só mudam um detalhe, portanto a mesma função foi atribuída a ambas.
     */
    private void deleteChar(boolean... delKey) {
    	boolean delMode = false;
    	if(delKey.length > 0) delMode = delKey[0];
    	ArrayList<Character> beforeHolder;
		ArrayList<Character> afterHolder;
		
		//Separa os valores atuais em dois arrays distintos.
		//A posição de corte do array é definida se foi pressionado backspace ou del.
		if(delMode) {
			beforeHolder = generateList(realText, 0, currentCaret);
			afterHolder = generateList(realText, currentCaret + 1, realText.size());
		} else {
			beforeHolder = generateList(realText, 0, currentCaret - 1);
			afterHolder = generateList(realText, currentCaret, realText.size());
		}

		//Reseta o texto real
		realText = new ArrayList<Character>();
	
		//Junta os arrays separados e forma o texto real novamente com um caractere excluído.
		realText.addAll(beforeHolder);
		realText.addAll(afterHolder);
    }
    
    /**
     * Método que adiciona ao texto real o caractere clicado. É feita a verificação de onde colocar o caractere.
     * <br>Se o caret estiver em uma posição diferente do final, signifca que o usuário quer adicionar o caractere
     * em um espaço no meio do que já foi digitado, portanto a mesma ideia de separar o texto atual em dois arrays é 
     * realizada.
     * @param c -> (char) que indica o caractere a ser adicionado.
     */
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
    
    
    /**
     * Método genérico para a cópia de um trecho uma lista, começando de um ponto determinando e indo até outro ponto determinado.
     * @param originList -> (ArrayList<-Character->) A lista de origem
     * @param startIndex -> (int) número que representa a posição relativa de início no array de origem
     * @param endIndex -> (int) ponto final da cópia
     * @return <b>ArrayList<-Character-></> -> retorna a lista dividida pelos parâmetros passados.
     */
    private ArrayList<Character> generateList(ArrayList<Character> originList, int startIndex, int endIndex) {
    	try {
        	ArrayList<Character> holderList = new ArrayList<Character>();
        	for(int i = startIndex; i < endIndex; i++) {
        		holderList.add(originList.get(i));
        	}
        	return holderList;
		} catch (Exception e) {
			return new ArrayList<Character>();
		}

    }

}