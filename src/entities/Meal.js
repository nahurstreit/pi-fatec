import Food from "./Foods/Food.js"
import { Model, DataTypes } from "sequelize"
import sequelize from "../database/dbConfig.js"

/**
 * @class Classe para a definição de Meals[refeições].
 */
export default class Meal extends Model {
    /**
     * @param {Object} config
     * @param {string} config.name - Nome da refeição. Exemplo: "Almoço", "Janta" etc.
     * @param {string} config.hour - Hora da refeição
     * @param {string} config.obs - Observações da refeição
     * @param {Array<Food>} config.foods - Array de Foods daquela refeição
     */
    constructor(config) {
        super()
        this.idMeal = config.idMeal
        this.idCustomer = config.idCustomer
        this.obs = config.obs
        this.hour = config.hour
        this.name = config.name
    }
}

//Definição das colunas da tabela "Meal" do banco de dados, para o sequelize.
Meal.init({
    idMeal: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    obs: {
        type: DataTypes.STRING
    },
    hour: {
        type: DataTypes.STRING
    },
    name: {
        type: DataTypes.STRING
    }
}, {
    sequelize,
    modelName: "Meal",
    timestamps: false
})

Meal.hasMany(Food, {
    foreignKey: 'idMeal',
})

Food.belongsTo(Meal, {
    foreignKey: 'idMeal'
})