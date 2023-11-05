import { DataTypes } from "sequelize"
import Aliment from "./Aliment.js"
import sequelize from "../../database/dbConfig.js"

export default class AlimentTaco extends Aliment {
    constructor(config) {
        super(config)
    }
}

AlimentTaco.init({
    idAliment: {
        type: DataTypes.INTEGER,
        primaryKey: true,
    },
    name: {
        type: DataTypes.STRING
    },
    alimentGroup: {
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