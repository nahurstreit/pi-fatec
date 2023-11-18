import { Model, DataTypes } from "sequelize"
import sequelize from "../../database/dbConfig.js"

import Aliment from "../Aliment.js"

/**
 * Classe para a definição de SubFood[Comidas Substitutas] de uma Food[Comida] principal. O termo Foods ou SubFoods dirá respeito a um alimento/comida quando referido em uma Meal[Refeição], enquanto o termo Aliment dirá respeito de alimento/comida quando referido no banco de dados, contendo informações nutricionais daquele alimento.
 */
export default class SubFood extends Model {
  /**
   * @param {Object} config
   * @param {Number} idSubFood - id da subfood no banco de dados
   * @param {Number} idFood - id da chave estrangeira food
   * @param {Number} config.idAliment - id do alimento (ou na tabela Taco ou AlimentCustom)
   * @param {Number} config.isTaco - se é da tabela taco (1 = sim, 0 = não)
   * @param {Number} config.quantity - quantidade do alimento na refeição
   * @param {String} config.unityQt - unidade de medida principal
   * @param {String} config.obs - observação do alimento
   */
    constructor(config) {
        super()
        this.idFood = config.idFood
        this.idSubFood = config.idSubFood
        this.idAliment = config.idAliment
        this.idMeal = config.idMeal
        this.quantity = config.quantity
        this.unityQt = config.unityQt
        this.obs = config.obs
        this.mainAliment = config.mainAliment
    }
}

//Definição das colunas da tabela "SubFood" do banco de dados, para o sequelize.
SubFood.init({
    idSubFood: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    idAliment: {
        type: DataTypes.INTEGER,
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
    modelName: "SubFood",
    timestamps: false,
    tableName: "SubFoods",
})

SubFood.belongsTo(Aliment, {
    foreignKey: "idAliment",
    as: "mainAliment",
    targetKey: "idAliment",
})
