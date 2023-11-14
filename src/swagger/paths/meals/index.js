import { getPostMeals } from "./getPostMeals.js"
import { getDeletePutMeals } from "./getDeletePutMeals.js"

const mealsPaths = {
    '/api/customer/{idCustomer}/meal': getPostMeals,
    '/api/customer/{idCustomer}/meal/{idMeal}': getDeletePutMeals,
}

export default mealsPaths