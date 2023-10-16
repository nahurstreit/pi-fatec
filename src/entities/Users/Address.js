/**
 * Classe para a definição do Endereço
 */
export default class Address{
    constructor(config){
        this.street = config.street || ""
        this.number = config.number || ""
        this.complement = config.complement || ""
        this.cep = config.cep || ""
        this.city = config.city || ""
        this.state = config.state || "SP"
    }
}