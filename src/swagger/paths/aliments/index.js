import { getPostAliment } from "./getPostAliment.js"
import { getDeletePutAliment } from "./getDeletePutAliment.js"
import { getAllTaco, getAllCustom } from "./getTacoNCustom.js"

const alimentsPaths = {
    '/api/aliments': getPostAliment,
    '/api/aliments/{idAliment}': getDeletePutAliment,
    '/api/aliments/taco': getAllTaco,
    '/api/aliments/custom': getAllCustom,
}

export default alimentsPaths