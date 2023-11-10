/**
 * Função para validação de um método post conforme Schema específico. 
 * Retorna uma string com os erros de schema ou então retorna null, caso não tenham erros.
 * @param {JSON} obj - Objeto a ser verificado.
 * @param {Schema} schema - Schema que define os campos obrigatórios e opcionais, bem como mensagens de erros.
 * 
 * @example
 * //Considerando o schema mealSchema em que: name e hour são obrigatórios e obs é opcional.
 * 
 * //Enviando todos os campos.
 * const obj1 = {name: "nomeMeal", hour: "hourMeal", obs: "obsMeal"}
 * post(obj1, mealSchema)
 * -> return null //<- Não existem erros.
 * 
 * 
 * //Deixando de enviar um campo.
 * const obj2 = {hour: "hourMeal"}
 * post(obj2, mealSchema)
 * -> return "enviar o nome é OBRIGATÓRIO" //<- Existem os seguintes erros.
 * 
 * 
 * //Enviando apenas os obrigatórios.
 * const obj3 = {name: "nomeMeal", hour: "hourMeal"}
 * post(obj3, mealSchema)
 * -> return null //<- Como obs é opcional, não retornará como erro.
 * 
 * @returns {String | null}
 */
export function post(obj, schema) {
    const errors = schema.validate(obj)
    if(errors.length > 0) {
        const errorMessages = errors.map(error => error.message).join(', ')
        return errorMessages
    }
    return null
}