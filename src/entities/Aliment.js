/**
 * Classe para a definição de [Alimentos]
 */
export default class Aliment{
    constructor(config){
        this.name = config.name;
        this.group = config.group;
        this.kcal = config.kcal;
        this.carb = config.carb;
        this.protein = config.protein;
        this.fat = config.fat;
    }
}
