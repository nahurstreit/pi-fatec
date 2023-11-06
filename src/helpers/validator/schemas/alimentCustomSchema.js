import Schema from "validate"

const alimentCustomSchema = new Schema ({
    name: {
        type: String,
        required: true,
        message: "enviar o NOME é obrigatório"
    },
    kcal: {
        type: String,
        required: true,
        message: "enviar a quantidade de QUILOCALORIA é obrigatório"
    },
    carb: {
        type: String,
        required: true,
        message: "enviar a quantidade de CARBOIDRATO é obrigatório"
    },
    protein: {
        type: String,
        required: true,
        message: "enviar a quantidade de PROTEÍNA é obrigatório"
    },
    fat: {
        type: String,
        required: true,
        message: "enviar a quantidade de GORDURA é obrigatório"
    }
})

export default alimentCustomSchema
