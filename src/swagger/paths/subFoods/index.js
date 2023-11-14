/* ⇊ Área para import ⇊ */
import { getPostSubFoods } from "./getPostSubFoods.js"
import { getDeletePutSubFoods } from "./getDeletePutSubFoods.js"
/* ⇈ Área para import ⇈ */


/* ⇊ Definir os paths (pegar de ./src/router/customer.routes.js) ⇊ */
const subFoodsPaths = {
    'api/customer/{idCustomer}/meal/{idMeal}/food/{idFood}/subfood': getPostSubFoods,
    'api/customer/{idCustomer}/meal/{idMeal}/food/{idFood}/subfood/{idSubFood}': getDeletePutSubFoods,
}

export default subFoodsPaths