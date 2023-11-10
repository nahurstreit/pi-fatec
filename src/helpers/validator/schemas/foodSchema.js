import Schema from "validate"

/**
 * Schema para validar o corpo de uma Requisição ao tentar fazer um POST de um nova Food ou SubFood.
 */
const foodSchema = new Schema ({
    idAliment: {
        type: Number,
        required: true,
        message: "enviar o ID DO ALIMENTO é obrigatório"
    },
    isTaco: {
        type: Boolean,
        required: true,
        message: "declarar se É DA TABELA TACO é obrigatório (apenas true ou false)"
    },
    quantity: {
        type: Number,
        required: true,
        message: "enviar a QUANTIDADE é obrigatório"
    },
    unityQt: {
        type: String,
        required: true,
        message: "enviar a UNIDADE DE MEDIDA é obrigatório"
    },
    obs: {
        type: String,
        required: false
    }
})

export default foodSchema
