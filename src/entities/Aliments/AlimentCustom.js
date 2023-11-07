import { DataTypes } from "sequelize"
import Aliment from "./Aliment.js"
import sequelize from "../../database/dbConfig.js"

export default class AlimentCustom extends Aliment {
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

//Definição das colunas da tabela "AlimentCustom" do banco de dados, para o sequelize.
AlimentCustom.init({
    idAliment: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        type: DataTypes.STRING
    },
    kcal: {
        type: DataTypes.STRING
    },
    carb: {
        type: DataTypes.STRING
    },
    protein: {
        type: DataTypes.STRING
    },
    fat: {
        type: DataTypes.STRING
    },
} , {
    sequelize,
    modelName: "AlimentCustom",
    timestamps: false,
    tableName: "AlimentCustom"
})