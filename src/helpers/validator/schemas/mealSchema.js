import Schema from "validate"

const mealSchema = new Schema ({
    name: {
        type: String,
        required: true,
        message: "enviar o NOME é obrigatório"
    },
    hour: {
        type: String,
        required: true,
        message: "enviar a HORA é obrigatório"
    },
    obs: {
        type: String,
        required: false
    }
})

export default mealSchema
