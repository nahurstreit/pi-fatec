import Food from "../entities/Foods/Food.js"
import Meal from "../entities/Meal.js"
import SubFood from "../entities/Foods/SubFood.js"

/**
 * Função para conferir no banco de dados se uma Meal[refeição] é de um dado Customer[usuário/cliente] através
 * da coluna idCustomer da tabela Meals.
 * @param {Number} idCustomer - Id do usuário.
 * @param {Number} idMeal - Id da refeição.
 * @returns {Promise<Boolean>} Retorna true se a refeição não for propriedade daquele usuário. Ou false, se for.
 */
export async function isCustomerMeal(idCustomer, idMeal) {
    const property = await Meal.findOne({where: {idMeal: idMeal, idCustomer: idCustomer}}) //Procura a Meal com idMeal que tenha idCustomer
    if(property) return true //true se tiver
    return false //false se não tiver
}

/**
 * Função para conferir no banco de dados se uma Food[comida] pertence à uma dada Meal[refeição] através
 * da coluna 'idMeal' da tabela Foods.
 * @param {Number} idCustomer 
 * @param {Number} idMeal 
 * @returns {Promise<Boolean>} Retorna true se a refeição não for propriedade daquele usuário. Ou false, se for.
 */
export async function isFoodMeal(idMeal, idFood) {
    const property = await Food.findOne({where: {idFood: idFood, idMeal: idMeal}})
    if(property) return true
    return false
}

/**
 * @summary Função para conferir no banco de dados se uma Food[comida] pertence à uma dada Meal[refeição]
 * e se essa Meal pertence a um dado Customer[usuário/cliente].
 * 
 * @description Os parâmetros são enviados automaticamente através das rota da API. A função verifica no banco de dados
 * se o Customer tem a propriedade da Meal e se a Meal em questão é proprietária da Food. 
 * 
 * Retornará FALSE casos em que:
 * - Customer não é proprietário da Meal e Meal não é proprietária da Food.
 * - Customer é proprietário da Meal, mas Meal não é proprietária da Food.
 * - Meal é proprietária da Food, mas Customer não é proprietário da Meal.
 * 
 * Retornará TRUE apenas quando Customer for proprietário de uma Meal e essa mesma Meal for proprietária da Food.
 * 
 * Essa função deverá ser usada para quando for preciso criar ou fazer update de dados das tabelas Food e SubFood de modo
 * a conferir se está sendo passado o caminho correto para a modificação no banco de dados.
 * 
 * @param {Number} idCustomer - id do Customer no banco de dados
 * @param {Number} idMeal - id da Meal no banco de dados
 * @param {Number} idFood - id da Food no banco de dados
 * @returns {Promise<Boolean>} Customer é proprietário da Meal e Meal é proprietária da Food.
 */
export async function isCustomerMealFoodProperty(idCustomer, idMeal, idFood) {
    const customerProperty = await isCustomerMeal(idCustomer, idMeal)
    const mealProperty = await isFoodMeal(idMeal, idFood)
    if(customerProperty && mealProperty) return true
    return false
}

/**
 * @summary Função para adequar o objeto [Food ou SubFood] para ser enviado como resposta de uma requisição da API.
 * 
 * @description A função recebe um objeto do tipo Food ou SubFood e através desse objeto é acessado o método
 * 'searchMainAliment' que busca e cria um atributo chamado 'mainAliment' ao objeto, de acordo com as informações contidas
 * na criação daquele objeto. Após o método criar o novo atributo a função formata o objeto e o retorna.
 * 
 * @param {Food | SubFood} food - Instância de Food ou SubFood a ser formatada. 
 * @returns {Promise<JSON>} JSON com o objeto formatado contendo as informações principais daquela comida.
 */
export async function setMainAliment(food) {
    const objFood = await food.searchMainAliment()
    const {idAliment, isTaco, ...infoFood} = objFood.dataValues
    return {
        ...infoFood,
        mainAliment: objFood.mainAliment,
    }
}

