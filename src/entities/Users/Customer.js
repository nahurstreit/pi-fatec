import User from "./User.js"
import Meal from "../Meal.js"
import { DataTypes } from "sequelize"
import sequelize from "../../database/dbConfig.js"

/**
 * Classe Customer [Cliente], que é uma subclasse de User. Serve para manipulação de 'Customers' no sistema.
 */
export default class Customer extends User {    
    /**
     * @param {Object} config
     * @param {String} config.name
     * @param {String} config.email
     * @param {String} config.password
     * @param {Number} config.idCustomer
     * @param {String} config.cpf
     * @param {String} config.cellphone
     * @param {String} config.height
     * @param {String} config.weight
     * @param {String} config.birth
     */
    constructor(config) {
        super(config.name, config.email, config.password)
        this.idCustomer = config.idCustomer
        this.cpf = config.cpf
        this.cellphone = config.cellphone
        this.gender = config.gender
        this.height = config.height
        this.weight = config.weight
        this.birth = config.birth
    }
}

//Definição das colunas da tabela "Customer" do banco de dados, para o sequelize.
Customer.init({
    idCustomer: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    name: {
        type: DataTypes.STRING
    },
    email: {
        type: DataTypes.STRING,
    },
    password: {
        type: DataTypes.STRING
    },
    cpf: {
        type: DataTypes.STRING
    },
    cellphone: {
        type: DataTypes.STRING
    },
    gender: {
        type: DataTypes.STRING
    },
    height: {
        type: DataTypes.STRING
    },
    weight: {
        type: DataTypes.STRING
    },
    birth: {
        type: DataTypes.STRING
    }
} , {
    sequelize,
    modelName: "Customer",
    timestamps: false,
    tableName: "Customers"
})

Customer.hasMany(Meal, {
    foreignKey: 'idCustomer',
})

Meal.belongsTo(Customer, {
    foreignKey: 'idCustomer'
})