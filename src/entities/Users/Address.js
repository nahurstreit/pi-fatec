/**
 * Classe para a definição de Address[endereço] de uma instância de Customer[cliente].
 */
export default class Address{
    /**
     * @param {Object} config
     * @param {string} config.street
     * @param {number} config.number
     * @param {string | null} config.complement
     * @param {number} config.cep
     * @param {string} config.city
     * @param {string | null} config.state
     */
    constructor(config){
        this.street = config.street || ""
        this.number = config.number || ""
        this.complement = config.complement || ""
        this.cep = config.cep || ""
        this.city = config.city || "Indaiatuba"
        this.state = config.state || "SP"
    }
}