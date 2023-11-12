import SubFood from "./SubFood.js"
import { DataTypes } from "sequelize"
import sequelize from "../../database/dbConfig.js"
import Aliment from "../Aliment.js"

/**
 * Classe de definição de Foods[comidas] em uma Meal[refeição]. O termo Foods dirá respeito a um alimento/comida quando referido em uma Meal[refeição], enquanto o termo Aliment dirá respeito de alimento/comida quando referido no banco de dados, contendo informações nutricionais daquele alimento. Nessa classe conterá atributos como: quantidade de comida, unidade de medida da comida, observações, e um array de subFood[comidas substitutas], que serão instâncias de SubFood, que têm as mesmas propriedades de uma Food, com exceção de não poderem ter um array de subFood. Ou seja, uma Food pode ter SubFood, enquanto uma SubFood não pode ter uma outra SubFood.
 */
export default class Food extends SubFood {
  /**
   * @param {Object} config
   * @param {Number} config.idFood - id da food no banco de dados.
   * @param {Number} config.idAliment - id do alimento na tabela TACO
   * @param {Number} config.isTaco - se é da tabela taco
   * @param {Number} config.quantity - quantidade do alimento na refeição
   * @param {String} config.unityQt - unidade de medida principal
   * @param {String} config.obs - observação do alimento
   */
    constructor(config) {
        super(config)
        delete this.idSubFood
    }

}

//Definição das colunas da tabela "Foods" do banco de dados, para o sequelize.
Food.init({
    idFood: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    idAliment: {
        type: DataTypes.INTEGER
    },
    quantity: {
        type: DataTypes.STRING
    },
    unityQt: {
        type: DataTypes.STRING
    },
    obs: {
        type: DataTypes.STRING
    },
}, {
    sequelize,
    modelName: "Food",
    timestamps: false,
    tableName: "Foods",
})

Food.hasMany(SubFood, {
    foreignKey: 'idFood'
})

SubFood.belongsTo(Food, {
    foreignKey: 'idFood'
})

Food.belongsTo(Aliment, {
    foreignKey: "idAliment",
    as: "mainAliment",
    targetKey: "idAliment",
})
