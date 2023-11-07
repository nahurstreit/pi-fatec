import { DataTypes } from "sequelize"
import Aliment from "./Aliment.js"
import sequelize from "../../database/dbConfig.js"

export default class AlimentTaco extends Aliment {
    /**
     * @param {Object} config
     * @param {Number} config.idAliment
     * @param {String} config.name 
     * @param {String} config.kcal
     * @param {String} config.carb
     * @param {String} config.protein
     * @param {String} config.fat
     */
    constructor(config) {
        super(config)
    }
}

//Definição das colunas da tabela "Taco" do banco de dados, para o sequelize.
AlimentTaco.init({
    idAliment: {
        type: DataTypes.INTEGER,
        primaryKey: true,
    },
    name: {
        type: DataTypes.STRING
    },
    kcal: {
        type: DataTypes.FLOAT
    },
    carb: {
        type: DataTypes.FLOAT
    },
    protein: {
        type: DataTypes.FLOAT
    },
    fat: {
        type: DataTypes.FLOAT
    },
}, {
    sequelize,
    modelName: "Taco",
    timestamps: false,
    tableName: "Taco"
})