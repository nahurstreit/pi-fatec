import Schema from "validate"

/**
 * Schema para validar o corpo de uma Requisição ao tentar fazer um POST de uma nova Meal.
 */
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
