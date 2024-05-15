package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Aliments")
public class Aliment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAliment")
    private Integer idAliment;

    @Column(name = "ali_custom", columnDefinition = "BIT")
    private Integer aliCustom;

    @Column(name = "ali_name")
    private String aliName;

    @Column(name = "alimentGroup")
    private String alimentGroup;

    @Column(name = "ali_kcal")
    private String aliKcal;

    @Column(name = "ali_kJ")
    private String aliKJ;

    @Column(name = "ali_carb")
    private String aliCarb;

    @Column(name = "ali_protein")
    private String aliProtein;

    @Column(name = "ali_fat")
    private String aliFat;

    @Column(name = "humidity")
    private String humidity;

    @Column(name = "dietaryFiber")
    private String dietaryFiber;

    @Column(name = "cholesterol")
    private String cholesterol;

    @Column(name = "sodium")
    private String sodium;

    @Column(name = "calcium")
    private String calcium;

    @Column(name = "magnesium")
    private String magnesium;

    @Column(name = "manganese")
    private String manganese;

    @Column(name = "phosphorus")
    private String phosphorus;

    @Column(name = "iron")
    private String iron;

    @Column(name = "potassium")
    private String potassium;

    @Column(name = "copper")
    private String copper;

    @Column(name = "zinc")
    private String zinc;

    @Column(name = "retinol")
    private String retinol;

    @Column(name = "RE")
    private String RE;

    @Column(name = "RAE")
    private String RAE;

    @Column(name = "thiamine")
    private String thiamine;

    @Column(name = "riboflavin")
    private String riboflavin;

    @Column(name = "pyridoxine")
    private String pyridoxine;

    @Column(name = "niacin")
    private String niacin;

    @Column(name = "vitaminC")
    private String vitaminC;

    @Column(name = "ash")
    private String ash;

	public Aliment(Integer idAliment, Integer aliCustom, String aliName, String alimentGroup, String aliKcal,
			String aliKJ, String aliCarb, String aliProtein, String aliFat, String humidity, String dietaryFiber,
			String cholesterol, String sodium, String calcium, String magnesium, String manganese, String phosphorus,
			String iron, String potassium, String copper, String zinc, String retinol, String rE, String rAE,
			String thiamine, String riboflavin, String pyridoxine, String niacin, String vitaminC, String ash) {
		this.idAliment = idAliment;
		this.aliCustom = aliCustom;
		this.aliName = aliName;
		this.alimentGroup = alimentGroup;
		this.aliKcal = aliKcal;
		this.aliKJ = aliKJ;
		this.aliCarb = aliCarb;
		this.aliProtein = aliProtein;
		this.aliFat = aliFat;
		this.humidity = humidity;
		this.dietaryFiber = dietaryFiber;
		this.cholesterol = cholesterol;
		this.sodium = sodium;
		this.calcium = calcium;
		this.magnesium = magnesium;
		this.manganese = manganese;
		this.phosphorus = phosphorus;
		this.iron = iron;
		this.potassium = potassium;
		this.copper = copper;
		this.zinc = zinc;
		this.retinol = retinol;
		RE = rE;
		RAE = rAE;
		this.thiamine = thiamine;
		this.riboflavin = riboflavin;
		this.pyridoxine = pyridoxine;
		this.niacin = niacin;
		this.vitaminC = vitaminC;
		this.ash = ash;
	}
	
	public Aliment() {}

	public Integer getIdAliment() {
		return idAliment;
	}

	public void setIdAliment(Integer idAliment) {
		this.idAliment = idAliment;
	}

	public Integer getAliCustom() {
		return aliCustom;
	}

	public void setAliCustom(Integer aliCustom) {
		this.aliCustom = aliCustom;
	}

	public String getAliName() {
		return aliName;
	}

	public void setAliName(String aliName) {
		this.aliName = aliName;
	}

	public String getAlimentGroup() {
		return alimentGroup;
	}

	public void setAlimentGroup(String alimentGroup) {
		this.alimentGroup = alimentGroup;
	}

	public String getAliKcal() {
		return aliKcal;
	}

	public void setAliKcal(String aliKcal) {
		this.aliKcal = aliKcal;
	}

	public String getAliKJ() {
		return aliKJ;
	}

	public void setAliKJ(String aliKJ) {
		this.aliKJ = aliKJ;
	}

	public String getAliCarb() {
		return aliCarb;
	}

	public void setAliCarb(String aliCarb) {
		this.aliCarb = aliCarb;
	}

	public String getAliProtein() {
		return aliProtein;
	}

	public void setAliProtein(String aliProtein) {
		this.aliProtein = aliProtein;
	}

	public String getAliFat() {
		return aliFat;
	}

	public void setAliFat(String aliFat) {
		this.aliFat = aliFat;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getDietaryFiber() {
		return dietaryFiber;
	}

	public void setDietaryFiber(String dietaryFiber) {
		this.dietaryFiber = dietaryFiber;
	}

	public String getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(String cholesterol) {
		this.cholesterol = cholesterol;
	}

	public String getSodium() {
		return sodium;
	}

	public void setSodium(String sodium) {
		this.sodium = sodium;
	}

	public String getCalcium() {
		return calcium;
	}

	public void setCalcium(String calcium) {
		this.calcium = calcium;
	}

	public String getMagnesium() {
		return magnesium;
	}

	public void setMagnesium(String magnesium) {
		this.magnesium = magnesium;
	}

	public String getManganese() {
		return manganese;
	}

	public void setManganese(String manganese) {
		this.manganese = manganese;
	}

	public String getPhosphorus() {
		return phosphorus;
	}

	public void setPhosphorus(String phosphorus) {
		this.phosphorus = phosphorus;
	}

	public String getIron() {
		return iron;
	}

	public void setIron(String iron) {
		this.iron = iron;
	}

	public String getPotassium() {
		return potassium;
	}

	public void setPotassium(String potassium) {
		this.potassium = potassium;
	}

	public String getCopper() {
		return copper;
	}

	public void setCopper(String copper) {
		this.copper = copper;
	}

	public String getZinc() {
		return zinc;
	}

	public void setZinc(String zinc) {
		this.zinc = zinc;
	}

	public String getRetinol() {
		return retinol;
	}

	public void setRetinol(String retinol) {
		this.retinol = retinol;
	}

	public String getRE() {
		return RE;
	}

	public void setRE(String rE) {
		RE = rE;
	}

	public String getRAE() {
		return RAE;
	}

	public void setRAE(String rAE) {
		RAE = rAE;
	}

	public String getThiamine() {
		return thiamine;
	}

	public void setThiamine(String thiamine) {
		this.thiamine = thiamine;
	}

	public String getRiboflavin() {
		return riboflavin;
	}

	public void setRiboflavin(String riboflavin) {
		this.riboflavin = riboflavin;
	}

	public String getPyridoxine() {
		return pyridoxine;
	}

	public void setPyridoxine(String pyridoxine) {
		this.pyridoxine = pyridoxine;
	}

	public String getNiacin() {
		return niacin;
	}

	public void setNiacin(String niacin) {
		this.niacin = niacin;
	}

	public String getVitaminC() {
		return vitaminC;
	}

	public void setVitaminC(String vitaminC) {
		this.vitaminC = vitaminC;
	}

	public String getAsh() {
		return ash;
	}

	public void setAsh(String ash) {
		this.ash = ash;
	}
    
	@Override
	public String toString() {
		return "Alimento: " + this.getAliName()
		+ "\n    kcal: " + this.getAliKcal()
		+ "\n    carb: " + this.getAliCarb()
		+ "\n    protein: " + this.getAliProtein()
		+ "\n    fat: " + this.getAliFat();
	}
}