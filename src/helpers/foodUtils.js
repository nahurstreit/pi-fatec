import Food from "../entities/Foods/Food.js"
import Meal from "../entities/Meal.js"
import SubFood from "../entities/Foods/SubFood.js"
import Aliment from "../entities/Aliment.js"

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
 * @description A função recebe um objeto do tipo Food ou SubFood e através das informações desse objeto é feita uma
 * readaptação do objeto para que inclua o campo "mainAliment" em sua estrutura.
 * 
 * @param {Food | SubFood} food - Instância de Food ou SubFood a ser formatada. 
 * @returns {JSON} JSON com o objeto formatado contendo as informações principais daquela comida.
 */
export function formatFoodResponse(food) {
    const {custom, ...infoMainAliment} = food.mainAliment
    const {idAliment, ...infoFood} = food.dataValues

    return {...infoFood, mainAliment: infoMainAliment}
}

/**
 * @summary Função para buscar o objeto [Food ou SubFood] que acabou de ser criado/atualizado, e retorna esse objeto formatado.
 * 
 * @description A função recebe a classe a ser utilizada Food ou SubFood e realiza uma busca do objeto daquela classe dentro
 * do banco de dados incluindo no objeto o campo mainAliment e mandando a resposta formatada através da função formatFoodResponse.
 * 
 * @param {Food | SubFood} Class - Classe a ser usada para buscar no banco de dados. Ou Food ou SubFood.
 * @param {JSON} where - Query específica a ser realizada para encontrar.
 * @returns {Promise<JSON>} JSON com o objeto formatado contendo as informações principais daquela comida.
 */
export async function findFoodAndAliment(Class, where) {
    const food = await Class.findOne({where: {...where}, include: [{model: Aliment, as: "mainAliment"}]})
    if(!food) return null
    return formatFoodResponse(food)
}