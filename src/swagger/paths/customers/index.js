import { getPostCustomer } from "./getPostCustomers.js"
import { getDeletePutCustomer } from "./getDeletePutCustomers.js"


/* ⇊ Definir os paths (pegar de ./src/router/customer.routes.js) ⇊ */
const customersPaths = {
    '/api/customer/': getPostCustomer,
    '/api/customer/{idCustomer}': getDeletePutCustomer ,
}

export default customersPaths