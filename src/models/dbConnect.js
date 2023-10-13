import dbSimulation from "../../dbSimulation.js";

function getInfoTaco(id) {
    return dbSimulation.dbAlimentTACO[id]
}

function getInfoAlimentCustom(id) {
    return dbSimulation.dbAlimentCUSTOM[id]
}

async function getInfoCustomer(id) {
    return dbSimulation.dbCustomers[id]
}

export default {
    getInfoTaco,
    getInfoAlimentCustom,
    getInfoCustomer,
}