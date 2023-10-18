/**
 * Classe para a definição de [Alimento Substituto]
 */
export default class SubFood {

    @param {Object} config
    @param {number} config.idAlimentTaco
    @param {number} config.quantity
    @param {string} config.unityQt
    @param {string} config.obs
    @param {Array<Food>} config.subFood
    @param {number} config.idAlimentCustom
    
    constructor(config) {
      this.mainAliment = null
      this.quantity = config.quantity
      this.unityQt = config.unityQt
      this.obs = config.obs
      this.configAliment(config)
    }
}
