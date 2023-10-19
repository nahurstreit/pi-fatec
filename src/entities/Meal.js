import Food from "./Foods/Food.js"

/**
 * Classe para a definição de Meals[refeições] como atributos do atributo diet de uma instância da classe Customer.
 */
export default class Meal{
    /**
     * @param {Object} config
     * @param {string} config.name - Nome da refeição. Exemplo: "Almoço", "Janta" etc.
     * @param {string} config.hour - Hora da refeição
     * @param {string} config.obs - Observações da refeição
     * @param {Array<Food>} config.foods - Array de Foods daquela refeição
     */
    constructor(config) {
        this.obs = config.obs || ""
        this.hour = config.hour || ""
        this.name = config.name || ""
        this.setFoods(config.foods || [])
    }

    /**
     * Método para definir as Foods[comidas] da instância Meal[refeição].
     * @param {Array<Object>} arrayFoods - Array com Objetos para a criação de Foods.
     */
    setFoods(arrayFoods) {
        this.foods = []
        arrayFoods.forEach((food) => {
            this.foods.push(new Food({
                idAlimentTaco: food.idAlimentTaco,
                idAlimentCustom: food.idAlimentCustom,
                quantity: food.quantity,
                unityQt: food.unityQt,
                obs: food.obs,
                subFood: food.subFood
            }))
        });
    }
}