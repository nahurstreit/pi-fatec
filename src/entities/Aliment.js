/**
 * Classe para a definição de [Alimentos]
 */
export default class Aliment{
    constructor(name, group, kcal, carb, protein, fat){
        this.name = name;
        this.group = group;
        this.kcal = kcal;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
    }
}
