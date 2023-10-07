/**
 * Classe para criação de usuários novos na plataforma. Tanto administrativos quanto clientes.
 */
export default class User {
    /**
     * @param {string} name 
     * @param {string} email 
     * @param {string} password 
     */
    constructor(name, email, password) {
        this.name = name
        this.email = email
        this.password = password
    }
}