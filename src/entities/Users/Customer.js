import Address from "./Address.js"
import User from "./User.js"
import Meal from "../Meal.js"
import {DataTypes} from "sequelize"
import sequelize from "../../controllers/database/dbConfig.js"

/**
 * Classe Customer [Cliente], que é uma subclasse de User. Essa classe servirá para armazenar os atributos e métodos de clientes no sistema.
 */
export default class Customer extends User {    
    /**
     * @param {Object} config
     * @param {string} config.name
     * @param {string} config.email
     * @param {string} config.password
     * @param {number} config.cpf
     * @param {number} config.cellphone
     * @param {Address} config.address
     * @param {number} config.height
     * @param {number} config.weight
     * @param {string} config.birth
     * @param {Array<Meal>} config.diet
     */
    constructor(config) {
        super(config.name, config.email, config.password)
        this.cpf = config.cpf
        this.cellphone = config.cellphone
        this.address = new Address(config.address)
        this.gender = config.gender
        this.height = config.height
        this.weight = config.weight
        this.birth = config.birth
        this.#setDiet(config.diet)
    }

    /**
     * Método para definir as dietas do usuário conforme passadas em config. Acessado apenas uma vez, ao tentar instanciar um objeto da classe Customer. Serve para quando já existe uma dieta pré-definida e armazenada no banco de dados.
     * @method
     * @private
     * @param {Array<Meal>} config - Array com as Meals a serem definidas.
     */
    #setDiet(configDiet) {
        this.diet = {}
        //Tenta criar as dietas de acordo com o objeto passado
        try {
            if(configDiet === undefined) return
            const meals = Object.keys(configDiet)
            meals.forEach((meal) => {
                this.setNewMeal(configDiet[meal])
            })
        }
        catch (err) {
            console.log(`Houve um erro ao definir as configurações iniciais de dieta: ${err}`)
            console.error(err)
        }
    }

    /**
     * Método para inserir novas Meal[refeições] na classe Customer. Usada para tanto na inicialização da classe Customer através do método privado #setDiet, quanto posteriormente por chamada de método. Adiciona novas instâncias de Meal como atributos do atributo específico diet presente na instância de Customer.
     * @method
     * @param {Object} config
     * @param {string} config.name - Nome principal da refeição
     * @param {string} config.hour - Hora da refeição
     * @param {string | null} config.obs - Observação da refeição
     * @param {Array<Food>} config.foods - Array de Foods da refeição
     */
    setNewMeal(config) {
        /**
         * Tenta descobrir qual é a próxima numeração de refeição para incluí-la como atributo.
         * Por exemplo: um cliente já tem uma meal1 e uma meal2 registrada. Ao acionar esse método, e passar o parâmetro {config} corretamente, será adicionado um atributo chamado meal3 (numerado automaticamente), no atributo diet da instância da classe Customer.
         * @returns {number} 
         */
        const currentFreeNumber = () => {
            let i = 1
            while (this.diet[`meal${i}`]) {
                i++
            }
            return i
        }
        //Depois de descoberto o próximo número possível para mealx, é então criada um novo atributo em this.diet como uma nova instância de Meal.
        this.diet[`meal${currentFreeNumber()}`] = new Meal({
            name: config.name,
            hour: config.hour,
            obs: config.obs,
            foods: config.foods || []
        })
    }
}

Costumer.init({
    idCostumer: {
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
    address: {
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

},{
    sequelize,
    modelName: "Costumer",
    timestamps: false
})
