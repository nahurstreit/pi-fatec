class Meal {
    constructor(config) {
      this.obs = config.obs || ""
      this.dayOfWeek = config.dayOfWeek || ""
      this.mealTime = config.mealTime || ""
      this.portionSize = config.portionSize || ""
      this.foods = []
    }
  
    // Adiciona um alimento à refeição
    addFood(food) {
      this.foods.push(food)
    }

    // Remove um alimento da refeição
    removeFood(food) {
      const index = this.foods.indexOf(food)
      if (index !== -1) {
        this.foods.splice(index, 1)
      }
    }

    // Calcular o valor nutricional da refeição
    calculateNutritionalValue() {
      const totalNutrition = {
        protein: 0,
        carbohydrates: 0,
        fats: 0,
        vitamins: 0
      }

      for (const food of this.foods) {
        totalNutrition.protein += food.protein || 0
        totalNutrition.carbohydrates += food.carbohydrates || 0
        totalNutrition.fats += food.fats || 0
        totalNutrition.vitamins += food.vitamins || 0
      }

      return totalNutrition
    }
}
