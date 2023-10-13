class Meal {
    constructor(config) {
      this.obs = config.obs || ""
      this.hour = config.hour || ""
      this.name = config.name || ""
      this.setFoods(config.foods) 
    }

  /**
   * 
   * Método para definir a lista de alimentos com controle de substituições {*} foodsArray 
   */
  setFoods(foodsArray) {
    this.foods = foodsArray
  }
}
