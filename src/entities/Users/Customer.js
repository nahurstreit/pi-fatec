import User from "./User.js";

/**
 * Classe Customer (Cliente), que é uma subclasse de User.
 */
export default class Customer extends User {
    /**
     * @param {Object} config
     * @param {string} config.name
     * @param {string} config.email
     * @param {string} config.password
     * @param {number} config.cpf
     * @param {number} config.cellphone
     * @param {string} config.address
     * @param {number} config.height
     * @param {number} config.weight
     * @param {string} config.birth
     */
    constructor(config) {
        super(config.name, config.email, config.password)
        this.cpf = config.cpf
        this.cellphone = config.cellphone
        this.address = config.address
        this.gender = config.gender
        this.height = config.height
        this.weight = config.weight
        this.birth = config.birth
    }
}