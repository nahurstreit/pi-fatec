/**
 * Package que contém as classes utilitárias de validação.
 */
package utils.validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import utils.view.LanguageUtil;
import view.components.QuestNutriJOP;
import view.components.forms.FormBoxInput;

/**
 * Classe abstrata utilitária para definir métodos de validação para reuso.
 */
public abstract class Validate {
    /**
     * Verifica se uma string contém pelo menos um dígito.
     *
     * @param str String a ser verificada.
     * @return true se a string contiver pelo menos um dígito, false caso contrário.
     */
	public static boolean hasNumber(String str) {
        return str != null && str.matches(".*\\d.*");
	}
	
    /**
     * Verifica se o tamanho de uma string está entre um mínimo e um máximo dado.
     *
     * @param str String a ser verificada.
     * @param min Tamanho mínimo da string.
     * @param max Tamanho máximo da string.
     * @return true se o tamanho da string estiver entre min e max, false caso contrário.
     */
	public static boolean sizeBetween(String str, int min, int max) {
		return str.length() >= min && str.length() <= max;
	}
	
    /**
     * Verifica se um número inteiro está dentro de um intervalo dado.
     *
     * @param num Número inteiro a ser verificado.
     * @param min Valor mínimo do intervalo.
     * @param max Valor máximo do intervalo.
     * @return true se o número estiver dentro do intervalo, false caso contrário.
     */
	public static boolean numberBetween(int num, int min, int max) {
		return num >= min && num <= max;
	}
	
	
    /**
     * Verifica se um número decimal está dentro de um intervalo dado.
     *
     * @param num Número decimal a ser verificado.
     * @param min Valor mínimo do intervalo.
     * @param max Valor máximo do intervalo.
     * @return true se o número estiver dentro do intervalo, false caso contrário.
     */
	public static boolean numberBetween(double num, double min, double max) {
		return num >= min && num <= max;
	}
	
	/**
	 * Método que retorna verdadeiro se uma string tem letras.
	 * @param str - String verificada
	 * @param allowed - caracteres permitidos pela validação.
	 * @return 
	 * <br><b>true</b> - se a String tiver letras.
	 * <br><b>false</b> - se a String não tiver letras.
	 */
	public static boolean hasChar(String str, Character ...allowed) {
	    if (str == null) {
	        return false;
	    }
	    
	    String allowedChars = "";
	    for (Character c : allowed) {
	        allowedChars += c;
	    }
	    
	    String regex = ".*[^\\d\\s" + allowedChars + "].*";
	    str = str.replaceAll("\\s", "");
	    return str.matches(regex);
	}
	
    /**
     * Verifica se uma string contém um caractere específico.
     *
     * @param str String a ser verificada.
     * @param searchChar Caractere a ser procurado.
     * @return true se a string contiver o caractere específico, false caso contrário.
     */
	public static boolean haveSpecifChar(String str, char searchChar) {
	    if (str == null) {
	        return false;
	    }
	    
	    String regex = Pattern.quote(String.valueOf(searchChar));
	    
	    return str.matches(".*" + regex + ".*");
	}
	
    /**
     * Verifica se uma string representa uma data válida no formato "dd/MM/yyyy".
     *
     * @param date String a ser verificada como data.
     * @return true se a string representar uma data válida, false caso contrário.
     */
	public static boolean isDate(String date) {
		try {
			LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));			
		} catch (Exception e) {
			return false;
		}
		return true;		
	}
	
    /**
     * Verifica se uma string tem tamanho maior que um limite especificado.
     *
     * @param str String a ser verificada.
     * @param limitSize Limite de tamanho.
     * @return true se a string tiver tamanho maior que o limite, false caso contrário.
     */
	public static boolean isBiggerThan(String str, int limitSize) {
		return str.length() > limitSize;
	}
	
	/**
	 * Método que faz a validação de um dado CPF passado como String.
	 * <br><b>Processo de validação: </b>
	 * <p>Os CPFs possuem um algoritmo de validação próprio, que confirma a validade da sequência de números de um dado CPF.
	 * <br>Um CPF é constituído em uma parte por uma sequência de quaisquer 9 números inteiros entre 0 e 9, e dois digitos, também entre 0 e 9.
	 * A definição dos dígitos de um CPF acontece através de um algoritmo específico em seus números, que é descrita
	 * da seguinte maneira:
	 * </p>
	 * <b>Primeiro Digito:</b>
	 * <p>
	 * Para a obter o primeiro digito é realizado o algoritmo nos 9 primeiros números do CPF.
	 * <br><br>Começando pelo número mais à esquerda, no sentido de leitura habitual, é realizada uma multiplicação por um número padrão inicial: 10.
	 * O resultado da multiplicação é armazenado para uso posterior. 
	 * Em seguida, deve-se caminhar para o <b>próximo</b> número mais à esquerda e multiplicá-lo pela sequência decrescente do número padrão inicial,
	 * ou seja, 9. O resultado é somado ao resultado da multiplicação anterior e armazenado novamente.
	 * <br><br>O processo se repete, caminhando para o próximo número mais à esquerda multiplicando por um novo número na ordem decrescente de 10.
	 * </p>
	 * <p>
	 * Depois de realizar a somatória de todos os números passando por esse algoritmo, o resultado obtido deve ser divido exatamente por 11,
	 * gerando um quociente e um resto.
	 * A partir do resto é definido o seguinte:
	 * <ul>
	 * <li> Se o resto for menor que 2, o digito será: 0.
	 * <li> Se o resto for maior ou igual a 2, o digito será: 11 - resto.
	 * </ul>
	 * 
	 * <br>
	 * <b>Segundo Digito:</b>
	 * O algoritmo para o segundo digito é semelhante ao do primeiro, sua maior diferença é considerar não apenas os 9 primeiros dígitos
	 * mas também o digito encontrado anteriormente. Além disso, o número padrão de multiplicação que decresce inicia-se pelo 11, não mais
	 * pelo 10.
	 *
	 * <br><br><b>Exemplo:</b> Validar o CPF de número <u>988.121.450-82</u>
	 * Obter primeiro dígito:<br>
	 * (<u>9</u> x <i>10</i>) = 90<br>
	 * (<u>8</u> x <i>9</i>) = 72<br>
	 * (<u>8</u> x <i>8</i>) = 64<br>
	 * (<u>1</u> x <i>7</i>) = 7<br>
	 * (<u>2</u> x <i>6</i>) = 12<br>
	 * (<u>1</u> x <i>5</i>) = 5<br>
	 * (<u>4</u> x <i>4</i>) = 16<br>
	 * (<u>5</u> x <i>3</i>) = 15<br>
	 * (<u>0</u> x <i>2</i>) = 0
	 * <br>
	 * SOMA TOTAL = <b> 281 </b>
	 * 201 / 11 = 25 com <b>resto 6</b>.
	 * Resto 6 > 2, portanto digito 1 = 11 - 6 = 5.
	 * 
	 * <br><br>
	 * Obter segundo digito:<br>
	 * (<u>9</u> x <i>11</i>) = 99<br>
	 * (<u>8</u> x <i>10</i>) = 80<br>
	 * (<u>8</u> x <i>9</i>) = 72<br>
	 * (<u>1</u> x <i>8</i>) = 8<br>
	 * (<u>2</u> x <i>7</i>) = 14<br>
	 * (<u>1</u> x <i>6</i>) = 6<br>
	 * (<u>4</u> x <i>5</i>) = 20<br>
	 * (<u>5</u> x <i>4</i>) = 20<br>
	 * (<u>0</u> x <i>3</i>) = 0<br>
	 * (<u>5</u> x <i>2</i>) = 10
	 * <br>
	 * SOMA TOTAL = <b> 329 </b>
	 * 329 / 11 = 29 com <b>resto 10</b>.
	 * Resto 10 > 2, portanto digito 2 = 11 - 10 = 1.
	 * <br>
	 * 
	 * <p>O número de CPF informado foi 988.121.450-54, mas a validação indicou que o primeiro digito está correto,
	 * mas o segundo deveria ser 1, portanto o CPF está incorreto.
	 * </p>
	 * @param cpf - String que contém o CPF a ser validado.
	 * @return <b>true</b> ou <b>false</b> -> retorna o resultado da validação do cpf, utilizando o algoritmo de identificação dos dígitos.
	 */
	public static boolean checkCpf(String cpf) {
		String cpfCopy = cpf.replaceAll("[\\s\\D]", "");
		ArrayList<Integer> cpfIntArray = new ArrayList<Integer>();
		try {
			for(int i = 0; i < cpfCopy.length(); i++) {
				cpfIntArray.add(Integer.parseInt(String.valueOf(cpfCopy.charAt(i)))); //Transforma o texto enviado em um array de Integer.
			}
		} catch (Exception e) {
			return false;
		}

		if(cpfIntArray.size() != 11) return false; //Se o tamanho for inválido, retorna falso --> Para caso o método seja utilizado sem padrão específico.
		
		ArrayList<Integer> cpfCheck = new ArrayList<Integer>();
		for(int i = 0; i < cpfIntArray.size() - 2; i++) cpfCheck.add(cpfIntArray.get(i)); //Copia os elementos de cpfIntArray.
		
		int remainderDiv1 = multiDecressiveSumCpfDigit(10, cpfCheck) % 11;
		int firstDigit = remainderDiv1 < 2? 0 : 11 - remainderDiv1;
//		System.out.println(firstDigit);
		
		if(firstDigit != cpfIntArray.get(cpfIntArray.size() - 2)) return false;
		else {
			cpfCheck.add(firstDigit);
			int remainderDiv2 = multiDecressiveSumCpfDigit(11, cpfCheck) % 11;
			int secDigit = remainderDiv2 < 2? 0 : 11 - remainderDiv2;
//			System.out.println(secDigit);
			if(secDigit != cpfIntArray.get(cpfIntArray.size() - 1)) return false;
		}
		
		return true;
		
	}
	
	/**
	 * Método que obtém a soma da multiplicação sucessiva dos números de um CPF
	 * por um determinado número inicial que decresce à cada dígito multiplicado.
	 * <p>
	 * Na validação de um CPF é necessário realizar um algoritmo que dita quais são os últimos dois dígitos de um CPF.
	 * Esse método tem como objetivo modularizar o processo de soma desse algoritmo, que é realizado duas vezes
	 * durante a validação.
	 * </p>
	 * 
	 * @param initialNumber - número inicial do iterador que decairá.
	 * @param array - array que contém os números inteiros a serem multiplicados.
	 * @return <b>int</b> -> a somatória de todos os números do array
	 */
	private static int multiDecressiveSumCpfDigit(int initialNumber, ArrayList<Integer> array) {
		int sum = 0;
		for(int i = 0; i < array.size(); i++) {
			sum += (array.get(i) * (initialNumber - i));
		}
		return sum;
	}
	
    /**
     * Valida os campos de um formulário.
     *
     * @param fields Campos do formulário a serem validados.
     * @return true se todos os campos forem válidos, false caso contrário.
     */
    public static boolean formFields(FormBoxInput ...fields) {
    	ArrayList<FormBoxInput> wrongFields = FormBoxInput.validateFields(fields);
    	
    	if(wrongFields.size() > 0) {
    		String errorMsg = new LanguageUtil(
    			    "Não foi possível salvar pois existem campos inválidos.\n\n", 
    			    "Unable to save because there are invalid fields.\n\n"
    			).get();        	
        	for(FormBoxInput wrong: wrongFields) {
        		errorMsg += wrong.getErrorText()+"\n";
        	}
        	
        	QuestNutriJOP.showMessageDialog(null, errorMsg, new LanguageUtil("Impossível salvar", "Unable to save").get(), 1, null);
        	return false;
    	}
    	
    	return true;

    }

}