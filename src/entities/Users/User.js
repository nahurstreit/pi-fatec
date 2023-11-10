import {Model} from "sequelize"

/**
 * Classe para criação de usuários novos na plataforma. Tanto administrativos quanto clientes.
 */
export default class User extends Model{
    /**
     * @param {string} name 
     * @param {string} email 
     * @param {string} password 
     */
    constructor(name, email, password) {
        super()
        this.name = name
        this.email = email
        this.password = password
    }
}