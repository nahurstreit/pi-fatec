import SubFood from "./SubFood.js"
import {DataTypes} from "sequelize"
import sequelize from "../../controllers/database/dbConfig.js"
/**
 * Classe de definição de Foods[comidas] em uma Meal[refeição]. O termo Foods dirá respeito a um alimento/comida quando referido em uma Meal[refeição], enquanto o termo Aliment dirá respeito de alimento/comida quando referido no banco de dados, contendo informações nutricionais daquele alimento. Nessa classe conterá atributos como: quantidade de comida, unidade de medida da comida, observações, e um array de subFood[comidas substitutas], que serão instâncias de SubFood, que têm as mesmas propriedades de uma Food, com exceção de não poderem ter um array de subFood. Ou seja, uma Food pode ter SubFood, enquanto uma SubFood não pode ter uma outra SubFood.
 */
export default class Food extends SubFood {
  /**
   * @param {Object} config
   * @param {number | null} config.idAlimentTaco - id do alimento na tabela TACO
   * @param {number} config.quantity - quantidade do alimento na refeição
   * @param {string} config.unityQt - unidade de medida principal
   * @param {string} config.obs - observação do alimento
   * @param {number | null} config.idAlimentCustom - id do alimento na tabela de alimentos CUSTOM
   * @param {Array<SubFood> | null} config.subFood - array de alimentos substitutos
   */
    constructor(config) {
        super(config)
        this.subFood = []
        this.configSubFood(config.subFood || [])
    }

    /**
     * Método para a criação de SubFoods em uma instância de Food. Para cada elemento dentro do array passado através de config.subFood, será criada uma nova instância de SubFood dentro do array principal do atributo this.subFood.
     * @description Maneira de acesso: customer.diet["meal{x}"].foods[y].subFood, onde x é o número da meal e y é o número do elemento no array foods.
     * @method
     * @param {Array<SubFood>} array 
     */
    configSubFood(array) {
        if(array.length < 1) return
        array.forEach((food) => {
            this.subFood.push(new SubFood({
                idAlimentTaco: food.idAlimentTaco,
                quantity: food.quantity,
                unityQt: food.unityQt,
                obs: food.obs,
                idAlimentCustom: food.idAlimentCustom
            }))
        })
    }
}

Food.init({
    idFood: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    quantity: {
        type: DataTypes.STRING
    },
    unityQt: {
        type: DataTypes.STRING,
    },
    obs: {
        type: DataTypes.STRING
    }
},{
    sequelize,
    modelName: "Food",
    timestamps: false
})
