import customersPaths from "./customers/index.js"
import alimentsPaths from "./aliments/index.js"
import mealsPaths from "./meals/index.js"
import foodsPaths from "./foods/index.js"
import subFoodsPaths from "./subFoods/index.js"


/**
 * Definição dos paths que serão passados e interpretados pelo swagger.
 */
export default {
    ...customersPaths,
    ...mealsPaths,
    ...foodsPaths,
    ...subFoodsPaths,
    ...alimentsPaths,
}