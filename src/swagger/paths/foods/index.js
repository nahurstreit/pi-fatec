/* ⇊ Área para import ⇊ */
import { getPostFoods } from "./getPostFoods.js"
import { getDeletePutFoods } from "./getDeletePutFoods.js"
/* ⇈ Área para import ⇈ */


/* ⇊ Definir os paths (pegar de ./src/router/customer.routes.js) ⇊ */
const foodsPaths = {
    'api/customer/{idCustomer}/meal/{idMeal}/food/': getPostFoods,
    'api/customer/{idCustomer}/meal/{idMeal}/food/{idFood}': getDeletePutFoods,
}

export default foodsPaths