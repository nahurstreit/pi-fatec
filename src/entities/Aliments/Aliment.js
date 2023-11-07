import { Model } from "sequelize"

/**
 * Classe para a definição de Aliments[alimentos/comidas] do banco de dados. Essa classe difere das classes Food e SubFood pelo contexto. Enquanto Food e SubFood dizem respeito ao alimento/comida quando é referenciado em uma Meal[refeição], a classe Aliment refere-se aos alimentos/comidas quando são tratados como os dados nutricionais armazenados no banco de dados. Aliment conterá as informações nutricionais e adicionais sobre o alimento, algo genérico, que NÃO é vinculado à uma instância de Customer[cliente]. Já as classes Food e SubFood, fazem referência ao alimento vinculando-o a um Customer através de uma Meal[refeição].
 */
export default class Aliment extends Model{
    /**
     * @param {Object} config
     * @param {Number} config.idAliment
     * @param {String} config.name 
     * @param {String} config.kcal
     * @param {String} config.carb
     * @param {String} config.protein
     * @param {String} config.fat
     */
    constructor(config){
        super()
        this.idAliment = config.idAliment
        this.name = config.name
        this.kcal = config.kcal
        this.carb = config.carb
        this.protein = config.protein
        this.fat = config.fat
    }
}
