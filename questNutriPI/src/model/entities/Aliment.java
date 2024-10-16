package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import model.dao.AlimentDAO;
import model.utils.HibernateUtil;

/**
 * Entidade que representa um alimento no sistema.
 * Extende as funcionalidades básicas de persistência da classe AlimentDAO.
 */
@Entity
@Table(name = "Aliments")
public class Aliment extends AlimentDAO {
	
	/**
	 * Id associado a esse objeto no banco de dados
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAliment")
	private Integer idAliment;

    /**
     * Indica se o alimento é customizado (1 para customizado, 0 para não customizado).
     */
	@Column(name = "ali_custom", columnDefinition = "BIT")
	private Integer custom = 1;

   /**
     * Nome do alimento.
     */
	@Column(name = "ali_name")
	public String name;

    /**
     * Grupo alimentar ao qual o alimento pertence.
     */
	@Column(name = "alimentGroup")
	public String alimentGroup;


    /**
     * Quantidade de calorias alimento.
     */
	@Column(name = "ali_kcal")
	public String kcal;
	
    /**
     * Conteúdo energético do alimento em quilojoules (kJ).
     */
	@Column(name = "ali_kJ")
	public String kJ;

    /**
     * Quantidade de carboidratos do alimento.
     */
	@Column(name = "ali_carb")
	public String carb;

    /**
     * Quantidade de proteínas do alimento.
     */
	@Column(name = "ali_protein")
	public String protein;

    /**
     * Quantidade de gorduras do alimento.
     */
	@Column(name = "ali_fat")
	public String fat;

    /**
     * Teor de umidade do alimento.
     */
	@Column(name = "humidity")
	public String humidity;

    /**
     * Conteúdo de fibras dietéticas do alimento.
     */
	@Column(name = "dietaryFiber")
	public String dietaryFiber;

    /**
     * Conteúdo de colesterol do alimento.
     */
    @Column(name = "cholesterol")
    public String cholesterol;

    /**
     * Conteúdo de sódio do alimento.
     */
    @Column(name = "sodium")
    public String sodium;

    /**
     * Conteúdo de cálcio do alimento.
     */
    @Column(name = "calcium")
    public String calcium;

    /**
     * Conteúdo de magnésio do alimento.
     */
    @Column(name = "magnesium")
    public String magnesium;

    /**
     * Conteúdo de manganês do alimento.
     */
    @Column(name = "manganese")
    public String manganese;

    /**
     * Conteúdo de fósforo do alimento.
     */
    @Column(name = "phosphorus")
    public String phosphorus;

    /**
     * Conteúdo de ferro do alimento.
     */
    @Column(name = "iron")
    public String iron;

    /**
     * Conteúdo de potássio do alimento.
     */
    @Column(name = "potassium")
    public String potassium;

    /**
     * Conteúdo de cobre do alimento.
     */
    @Column(name = "copper")
    public String copper;

    /**
     * Conteúdo de zinco do alimento.
     */
    @Column(name = "zinc")
    public String zinc;

    /**
     * Conteúdo de retinol (vitamina A) do alimento.
     */
    @Column(name = "retinol")
    public String retinol;

    /**
     * Conteúdo de equivalente de retinol (vitamina A) do alimento.
     */
    @Column(name = "RE")
    public String rE;

    /**
     * Conteúdo de equivalente de atividade de retinol (vitamina A) do alimento.
     */
    @Column(name = "RAE")
    public String rAE;

    /**
     * Conteúdo de tiamina (vitamina B1) do alimento.
     */
    @Column(name = "thiamine")
    public String thiamine;

    /**
     * Conteúdo de riboflavina (vitamina B2) do alimento.
     */
    @Column(name = "riboflavin")
    public String riboflavin;

    /**
     * Conteúdo de piridoxina (vitamina B6) do alimento.
     */
    @Column(name = "pyridoxine")
    public String pyridoxine;

    /**
     * Conteúdo de niacina (vitamina B3) do alimento.
     */
    @Column(name = "niacin")
    public String niacin;

    /**
     * Conteúdo de vitamina C do alimento.
     */
    @Column(name = "vitaminC")
    public String vitaminC;

    /**
     * Conteúdo de cinzas do alimento.
     */
    @Column(name = "ash")
    public String ash;

    /**
     * Construtor padrão da classe Aliment.
     */
    public Aliment() {
    }

	/**
	 * Retorna se o objeto Aliment é customizável. Alimentos da tabela TACO, NÃO
	 * PODEM SER CUSTOMIZADOS e alimentos que foram criados manualmente NÃO podem
	 * deixar de ser customizáveis.
	 * 
	 * @return - Booleano indicando se o alimento é customizável.
	 */
	public boolean isCustom() {
		return this.custom == 1;
	}

	/**
	 * Método para recuperar o ID do Alimento.
	 * 
	 * @return Retorna o ID do Alimento.
	 */
	public Integer getId() {
		return idAliment;
	}

	@Override
	public String toString() {
		return "Alimento: " + this.name + "\n    kcal: " + this.kcal + "\n    carb: " + this.carb + "\n    protein: "
				+ this.protein + "\n    fat: " + this.fat;
	}

	/**
	 * Método para retornar uma visão pequena do objeto.
	 * 
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
	public String smallInfo() {
		return "{id: " + idAliment + ", name: \"" + name + "\"}";

	}

	/**
	 * Método para salvar este objeto no banco de dados, caso seja um registro customizável.
	 * @return <b>boolean</b> - O resultado da operação.
	 * <br>Se <b>true</b> - O objeto foi salvo.
	 * <br>Se <b>false</b> - O objeto não foi salvo.
	 */
	@Override
	public boolean save() {
		boolean result = false;
		if(this.isCustom()) {
			super.save();
			result = true;
		} else {
			HibernateUtil.refreshInstance(this);
		}

		return result;
	}

	/**
	 * Método para excluir este objeto no banco de dados, caso seja um registro customizável.
	 * @return <b>boolean</b> - O resultado da operação.
	 * <br>Se <b>true</b> - O objeto foi excluído.
	 * <br>Se <b>false</b> - O objeto não pôde ser excluído.
	 */
	@Override
	public boolean delete() {
		boolean result = false;
		if (this.isCustom()) {
			super.delete();
			result = true;
		}

		return result;
	}

	/**
	 * // * Método para definir o nome do alimento.
	 * 
	 * @param name - String que representa o nome do alimento.
	 * @return o próprio objeto Aliment para implementar fluent interface.
	 */
	public Aliment setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Método para definir o grupo do Alimento.
	 * 
	 * @param alimentGroup - String do grupo do alimento.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setAlimentGroup(String alimentGroup) {
		this.alimentGroup = alimentGroup;
		return this;
	}

	/**
	 * Método para definir as quilocalorias do Alimento.
	 * 
	 * @param kcal - String das quilocalorias.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setKcal(String kcal) {
		this.kcal = kcal;
		return this;
	}

	/**
	 * Método para definir os quilojoules do Alimento.
	 * 
	 * @param kJ - String dos quilojoules.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setkJ(String kJ) {
		this.kJ = kJ;
		return this;
	}

	/**
	 * Método para definir os carboidratos do Alimento.
	 * 
	 * @param carb - String dos carboidratos.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setCarb(String carb) {
		this.carb = carb;
		return this;
	}

	/**
	 * Método para definir a proteína do Alimento.
	 * 
	 * @param protein - String da proteína.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setProtein(String protein) {
		this.protein = protein;
		return this;
	}

	/**
	 * Método para definir a gordura do Alimento.
	 * 
	 * @param fat - String da gordura.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setFat(String fat) {
		this.fat = fat;
		return this;
	}

	/**
	 * Método para definir a umidade do Alimento.
	 * 
	 * @param humidity - String da umidade.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setHumidity(String humidity) {
		this.humidity = humidity;
		return this;
	}

	/**
	 * Método para definir a fibra dietética do Alimento.
	 * 
	 * @param dietaryFiber - String da fibra dietética.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setDietaryFiber(String dietaryFiber) {
		this.dietaryFiber = dietaryFiber;
		return this;
	}

	/**
	 * Método para definir o colesterol do Alimento.
	 * 
	 * @param cholesterol - String do colesterol.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setCholesterol(String cholesterol) {
		this.cholesterol = cholesterol;
		return this;
	}

	/**
	 * Método para definir o sódio do Alimento.
	 * 
	 * @param sodium - String do sódio.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setSodium(String sodium) {
		this.sodium = sodium;
		return this;
	}

	/**
	 * Método para definir o cálcio do Alimento.
	 * 
	 * @param calcium - String do cálcio.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setCalcium(String calcium) {
		this.calcium = calcium;
		return this;
	}

	/**
	 * Método para definir o magnésio do Alimento.
	 * 
	 * @param magnesium - String do magnésio.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setMagnesium(String magnesium) {
		this.magnesium = magnesium;
		return this;
	}

	/**
	 * Método para definir o manganês do Alimento.
	 * 
	 * @param manganese - String do manganês.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setManganese(String manganese) {
		this.manganese = manganese;
		return this;
	}

	/**
	 * Método para definir o fósforo do Alimento.
	 * 
	 * @param phosphorus - String do fósforo.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setPhosphorus(String phosphorus) {
		this.phosphorus = phosphorus;
		return this;
	}

	/**
	 * Método para definir o ferro do Alimento.
	 * 
	 * @param iron - String do ferro.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setIron(String iron) {
		this.iron = iron;
		return this;
	}

	/**
	 * Método para definir o potássio do Alimento.
	 * 
	 * @param potassium - String do potássio.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setPotassium(String potassium) {
		this.potassium = potassium;
		return this;
	}

	/**
	 * Método para definir o cobre do Alimento.
	 * 
	 * @param copper - String do cobre.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setCopper(String copper) {
		this.copper = copper;
		return this;
	}

	/**
	 * Método para definir o zinco do Alimento.
	 * 
	 * @param zinc - String do zinco.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setZinc(String zinc) {
		this.zinc = zinc;
		return this;
	}

	/**
	 * Método para definir o retinol do Alimento.
	 * 
	 * @param retinol - String do retinol.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setRetinol(String retinol) {
		this.retinol = retinol;
		return this;
	}

	/**
	 * Método para definir o resíduo de extrato etéreo do Alimento.
	 * 
	 * @param rE - String do resíduo de extrato etéreo.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setrE(String rE) {
		this.rE = rE;
		return this;
	}

	/**
	 * Método para definir o retinol equivalente do Alimento.
	 * 
	 * @param rAE - String do retinol equivalente.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setrAE(String rAE) {
		this.rAE = rAE;
		return this;
	}

	/**
	 * Método para definir a tiamina do Alimento.
	 * 
	 * @param thiamine - String da tiamina.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setThiamine(String thiamine) {
		this.thiamine = thiamine;
		return this;
	}

	/**
	 * Método para definir a riboflavina do Alimento.
	 * 
	 * @param riboflavin - String da riboflavina.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setRiboflavin(String riboflavin) {
		this.riboflavin = riboflavin;
		return this;
	}

	/**
	 * Método para definir a piridoxina do Alimento.
	 * 
	 * @param pyridoxine - String da piridoxina.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setPyridoxine(String pyridoxine) {
		this.pyridoxine = pyridoxine;
		return this;
	}

	/**
	 * Método para definir a niacina do Alimento.
	 * 
	 * @param niacin - String da niacina.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setNiacin(String niacin) {
		this.niacin = niacin;
		return this;
	}

	/**
	 * Método para definir a vitamina C do Alimento.
	 * 
	 * @param vitaminC - String da vitamina C.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setVitaminC(String vitaminC) {
		this.vitaminC = vitaminC;
		return this;
	}

	/**
	 * Método para definir o conjunto mineral total (cinzas) do Alimento.
	 * 
	 * @param ash - String do conjunto mineral.
	 * @return Retorna o próprio Aliment, para implementação de fluent interface.
	 */
	public Aliment setAsh(String ash) {
		this.ash = ash;
		return this;
	}

}