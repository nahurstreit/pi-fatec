/**
 * Classe para a definição de [Alimento Substituto]
 */
export default class SubFood {
    constructor(config) {
      this.mainAliment = config.mainAliment || ""
      this.quantity = config.quantity || ""
      this.obs = config.obs || ""
    }
}
