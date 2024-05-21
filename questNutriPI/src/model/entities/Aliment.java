package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import model.dao.AlimentDAO;
import model.utils.HibernateUtil;

@Entity
@Table(name = "Aliments")
public class Aliment extends AlimentDAO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAliment")
	private Integer idAliment;

	@Column(name = "ali_custom", columnDefinition = "BIT")
	private Integer custom = 1;

	@Column(name = "ali_name")
	public String name;

	@Column(name = "alimentGroup")
	public String alimentGroup;

	@Column(name = "ali_kcal")
	public String kcal;

	@Column(name = "ali_kJ")
	public String kJ;

	@Column(name = "ali_carb")
	public String carb;

	@Column(name = "ali_protein")
	public String protein;

	@Column(name = "ali_fat")
	public String fat;

	@Column(name = "humidity")
	public String humidity;

	@Column(name = "dietaryFiber")
	public String dietaryFiber;

	@Column(name = "cholesterol")
	public String cholesterol;

	@Column(name = "sodium")
	public String sodium;

	@Column(name = "calcium")
	public String calcium;

	@Column(name = "magnesium")
	public String magnesium;

	@Column(name = "manganese")
	public String manganese;

	@Column(name = "phosphorus")
	public String phosphorus;

	@Column(name = "iron")
	public String iron;

	@Column(name = "potassium")
	public String potassium;

	@Column(name = "copper")
	public String copper;

	@Column(name = "zinc")
	public String zinc;

	@Column(name = "retinol")
	public String retinol;

	@Column(name = "RE")
	public String rE;

	@Column(name = "RAE")
	public String rAE;

	@Column(name = "thiamine")
	public String thiamine;

	@Column(name = "riboflavin")
	public String riboflavin;

	@Column(name = "pyridoxine")
	public String pyridoxine;

	@Column(name = "niacin")
	public String niacin;

	@Column(name = "vitaminC")
	public String vitaminC;

	@Column(name = "ash")
	public String ash;

	/**
	 * 
	 * @param idAliment    Recebe o Id do Alimento
	 * @param aliCustom    Recebe 0 ou 1 e será usado como um check no banco de
	 *                     dados para permitir ou não a alteração/exclusão do
	 *                     Alimento (permissão = 1)
	 * @param aliName      Recebe o Nome do Alimento
	 * @param alimentGroup Recebe o Grupo ao qual pertence o Alimento
	 * @param aliKcal      Recebe as Quilocalorias do Alimento
	 * @param aliKJ        Recebe os Quilojoules do Alimento
	 * @param aliCarb      Recebe os Carboidratos do Alimento
	 * @param aliProtein   Recebe a Proteína do Alimento
	 * @param aliFat       Recebe a Gorduta do Alimento
	 * @param humidity     Recebe a Umidade presente no Alimento
	 * @param dietaryFiber Recebe a Fibra dietética (fibras alimentares) do Alimento
	 * @param cholesterol  Recebe os níveis de Colesterol presentes no Alimento
	 * @param sodium       Recebe o Sódio do Alimento
	 * @param calcium      Recebe o Cálcio do Alimento
	 * @param magnesium    Recebe o Magnésio do Alimento
	 * @param manganese    Recebe o Manganês (ajuda na formação dos ossos) do
	 *                     Alimento
	 * @param phosphorus   Recebe o Fósforo do Alimento
	 * @param iron         Recebe o Ferro (sais minerais) do Alimento
	 * @param potassium    Recebe o Potássio (sais minerais) do Alimento
	 * @param copper       Recebe o Cobre (sais minerais) do Alimento
	 * @param zinc         Recebe o Zinco (oligoelemento importante para o sistema
	 *                     imunológico) do Alimento
	 * @param retinol      Recebe o Retinol (vitamina A) do Alimento
	 * @param rE           Recebe o Resíduo de extrato etéreo (quantidade de
	 *                     lipídeos) do Alimento
	 * @param rAE          Recebe o Retinol equivalente (padronização de ingestão de
	 *                     vitamina A) do Alimento
	 * @param thiamine     Recebe a Tiamina (vitamina B1) do Alimento
	 * @param riboflavin   Recebe a Riboflavina (vitamina B2) do Alimento
	 * @param pyridoxine   Recebe a Piridoxina (vitamina B6) do Alimento
	 * @param niacin       Recebe a Niacina (vitamina B3) do Alimento
	 * @param vitaminC     Recebe a Vitamina C (que contribui para o sistema
	 *                     imunológico) do Alimento.
	 * @param ash          Recebe o conjunto mineral total presente no Alimento
	 */
//	public Aliment(String name, String alimentGroup, String kcal,
//			String kJ, String carb, String protein, String fat, String humidity, String dietaryFiber,
//			String cholesterol, String sodium, String calcium, String magnesium, String manganese, String phosphorus,
//			String iron, String potassium, String copper, String zinc, String retinol, String rE, String rAE,
//			String thiamine, String riboflavin, String pyridoxine, String niacin, String vitaminC, String ash) {
//		super();
//		this.name = name;
//		this.alimentGroup = alimentGroup;
//		this.kcal = kcal;
//		this.kJ = kJ;
//		this.carb = carb;
//		this.protein = protein;
//		this.fat = fat;
//		this.humidity = humidity;
//		this.dietaryFiber = dietaryFiber;
//		this.cholesterol = cholesterol;
//		this.sodium = sodium;
//		this.calcium = calcium;
//		this.magnesium = magnesium;
//		this.manganese = manganese;
//		this.phosphorus = phosphorus;
//		this.iron = iron;
//		this.potassium = potassium;
//		this.copper = copper;
//		this.zinc = zinc;
//		this.retinol = retinol;
//		this.rE = rE;
//		this.rAE = rAE;
//		this.thiamine = thiamine;
//		this.riboflavin = riboflavin;
//		this.pyridoxine = pyridoxine;
//		this.niacin = niacin;
//		this.vitaminC = vitaminC;
//		this.ash = ash;
//	}
	
	public Aliment() {};
	
	public boolean isCustom() {
		return this.custom == 1;
	}

	public Integer getIdAliment() {
		return idAliment;
	}

	@Override
	public String toString() {
		return "Alimento: " + this.name + "\n    kcal: " + this.kcal + "\n    carb: "
				+ this.carb + "\n    protein: " + this.protein + "\n    fat: " + this.fat;
	}

	/**
	 * Método para retornar uma visão pequena do objeto.
	 * 
	 * @return String contendo apenas alguns dos atributos mais importantes
	 */
	public String smallInfo() {
		return "{id: " + idAliment + ", name: \"" + name + "\"}";

	}
	
	
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
	
	@Override
	public boolean delete() {
		boolean result = false;
		if(this.isCustom()) {
			super.delete();
			result = true;
		}
		
		return result;
	}

	public Aliment setName(String name) {
		this.name = name;
		return this;
	}

	public Aliment setAlimentGroup(String alimentGroup) {
		this.alimentGroup = alimentGroup;
		return this;
	}

	public Aliment setKcal(String kcal) {
		this.kcal = kcal;
		return this;
	}

	public Aliment setkJ(String kJ) {
		this.kJ = kJ;
		return this;
	}

	public Aliment setCarb(String carb) {
		this.carb = carb;
		return this;
	}

	public Aliment setProtein(String protein) {
		this.protein = protein;
		return this;
	}

	public Aliment setFat(String fat) {
		this.fat = fat;
		return this;
	}

	public Aliment setHumidity(String humidity) {
		this.humidity = humidity;
		return this;
	}

	public Aliment setDietaryFiber(String dietaryFiber) {
		this.dietaryFiber = dietaryFiber;
		return this;
	}

	public Aliment setCholesterol(String cholesterol) {
		this.cholesterol = cholesterol;
		return this;
	}

	public Aliment setSodium(String sodium) {
		this.sodium = sodium;
		return this;
	}

	public Aliment setCalcium(String calcium) {
		this.calcium = calcium;
		return this;
	}

	public Aliment setMagnesium(String magnesium) {
		this.magnesium = magnesium;
		return this;
	}

	public Aliment setManganese(String manganese) {
		this.manganese = manganese;
		return this;
	}

	public Aliment setPhosphorus(String phosphorus) {
		this.phosphorus = phosphorus;
		return this;
	}

	public Aliment setIron(String iron) {
		this.iron = iron;
		return this;
	}

	public Aliment setPotassium(String potassium) {
		this.potassium = potassium;
		return this;
	}

	public Aliment setCopper(String copper) {
		this.copper = copper;
		return this;
	}

	public Aliment setZinc(String zinc) {
		this.zinc = zinc;
		return this;
	}

	public Aliment setRetinol(String retinol) {
		this.retinol = retinol;
		return this;
	}

	public Aliment setrE(String rE) {
		this.rE = rE;
		return this;
	}

	public Aliment setrAE(String rAE) {
		this.rAE = rAE;
		return this;
	}

	public Aliment setThiamine(String thiamine) {
		this.thiamine = thiamine;
		return this;
	}

	public Aliment setRiboflavin(String riboflavin) {
		this.riboflavin = riboflavin;
		return this;
	}

	public Aliment setPyridoxine(String pyridoxine) {
		this.pyridoxine = pyridoxine;
		return this;
	}

	public Aliment setNiacin(String niacin) {
		this.niacin = niacin;
		return this;
	}

	public Aliment setVitaminC(String vitaminC) {
		this.vitaminC = vitaminC;
		return this;
	}

	public Aliment setAsh(String ash) {
		this.ash = ash;
		return this;
	}
	
	
}