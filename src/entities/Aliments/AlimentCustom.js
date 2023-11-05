import { DataTypes } from "sequelize"
import Aliment from "./Aliment.js"
import sequelize from "../../database/dbConfig.js"

export default class AlimentCustom extends Aliment {
    constructor(config) {
        super(config)
    }
}

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