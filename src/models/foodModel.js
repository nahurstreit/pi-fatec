import Food from "../entities/Foods/Food.js"
import { isCustomerMeal, isCustomerMealFoodProperty, formatFoodResponse, findFoodAndAliment} from "../helpers/foodUtils.js"

import * as validateReq from "../helpers/validator/validateReq.js"
import foodSchema from "../helpers/validator/schemas/foodSchema.js"

import { successResult, errorResult, serverError } from "../helpers/responseUtils.js"
import Aliment from "../entities/Aliment.js"

export default {
    /**
     * Procura todas as Foods no banco de dados que pertencem a uma determinada Meal, com chave primária igual à {idMeal}
     * passada pelos parâmetros da requisição, desde que essa Meal pertença de fato ao Customer com chave primária {idCustomer},
     * também passada pelos parâmetros.
     * 
     * @param {Object} params
     * @param {Number} params.idCustomer - id do Customer que detém as Meals a serem buscadas
     * @param {Number} params.idMeal - id da Meal que detém as Foods a serem buscadas
     * 
     * @returns {Promise<{status: number, obj: Food[]}>} JSON de resposta à requisição, contendo um status code HTTP e um Array de Food.
     */
    async all(params) {
        try {
            const {idCustomer, idMeal} = params
            if(!await isCustomerMeal(idCustomer, idMeal)) 
                return errorResult("O usuário não tem esses dados.", 404)

            const foodAll = await Food.findAll({where: {idMeal: idMeal}, include: [{model: Aliment, as: "mainAliment"}]})
            if(foodAll.length < 1) return successResult({message: "Ainda não existem comidas para essa refeição."}, 202)

            const foodPromises = foodAll.map(async (food) => {
                return formatFoodResponse(food)
            })

            const foods = await Promise.all(foodPromises)
            
            return successResult(foods, 200)

        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Procura uma Food específica cuja chave primária é igual a {idFood}, passada pelos parâmetros da requisição, desde que
     * essa Food pertença de fato à Meal com chave primária {idMeal} e que essa Meal pertença ao Customer com chave primária
     * {idCustomer}.
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer que detém a posse da Meal
     * @param {Number} params.idMeal - id da Meal que detém a posse da Food
     * @param {Number} params.idFood - id da Food a ser buscada
     * 
     * @returns {Promise<{status: number, obj: Food | {erro: string}>}} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto encontrado, ou uma mensagem de erro.
     */
    async findFoodById(params) {
        try {
            const {idCustomer, idMeal, idFood} = params
            if(!await isCustomerMeal(idCustomer, idMeal)) 
                return errorResult("O usuário não tem esses dados.", 404)
            
            const food = await findFoodAndAliment(Food, {idMeal: idMeal, idFood: idFood})
            if(!food) return errorResult("Comida não encontrada.", 404)

            return successResult(food, 200)
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta criar uma nova Food de acordo com as informações no corpo da requisição (req.body) para a Meal indicada
     * em {idMeal}, desde que essa Meal pertença ao Customer indicado em {idCustomer}.
     * Criará somente se o corpo da requisição atender aos padrões estabelecidos em foodSchema.
     * @see {@link ./src/helpers/validator/schemas/foodSchema.js}
     * 
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer que detém a posse da Meal
     * @param {Number} params.idMeal - id da Meal em que será adicionada a nova Food
     * 
     * @param {Object} obj - req.body
     * @param {Number} obj.idAliment - id do alimento a ser definido como mainAliment
     * @param {Boolean} obj.isTaco - booleano para definir se o idAliment é referência à tabela Taco ou se é referência à tabela AlimentCustom.
     * @param {Number} obj.quantity - quantidade daquele alimento
     * @param {String} obj.unityQt - unidade de medida daquele alimento
     * @param {String} obj.obs - observações sobre esse alimento
     * 
     * @returns {Promise<{status: number, obj: Food | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto criado, ou uma mensagem de erro.
     */
    async create(params, obj) {
        try {
            const {idCustomer, idMeal} = params
            if(!await isCustomerMeal(idCustomer, idMeal)) 
                return errorResult("Usuário ou Refeição não encontrada.", 404)

            const errors = validateReq.post(obj, foodSchema)
            if(errors) return errorResult(errors, 400)

            const aliment = await Aliment.findOne({where: {idAliment: obj.idAliment}})
            if(!aliment) return errorResult("idAliment é inválido.", 400)

            const food = await Food.create({
                idMeal: Number(idMeal),
                ...obj,
            })

            return successResult(await findFoodAndAliment(Food, {idFood: food.idFood}), 201) 
            
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta deletar do banco de dados um registro de Food
     * cuja chave primária é determinada por {idFood}, passada pelos parâmetros da requisição.
     * 
     * O registro de Food será deletado do banco de dados apenas se a Meal, identificada por {idMeal}, 
     * for a real proprietária daquela Food e se essa Meal pertencer de fato ao Customer identificado por {idCustomer}.
     * Isto é: não é possível excluir uma Food por uma rota que não seja a correta.
     * 
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer que detém a posse da Meal
     * @param {Number} params.idMeal - id da Meal que detém a posse da Food
     * @param {Number} params.idFood - id da Food a ser excluída
     * 
     * @returns {Promise<{status: number, obj: {message: string} | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e uma mensagem de sucesso ou erro.
     */
    async delete(params) {
        const {idCustomer, idMeal, idFood} = params 
        if(!await isCustomerMealFoodProperty(idCustomer, idMeal, idFood)) 
            return errorResult("O usuário não tem esses dados.", 404)

        try {
            const result = await Food.destroy({where: {idFood: idFood, idMeal: idMeal}})
            if(result === 0) return errorResult("Comida não encontrada.", 404)
            return successResult({mensagem: "Registro excluído."}, 200) 
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta fazer alterações em uma Food já criada, cuja chave primária é {idFood},
     * passada automaticamente através dos parâmetros da requisição.
     * 
     * Serão alterados os campos do registro de Food apenas se a Meal identificada por {idMeal}, 
     * for a real proprietária daquela Food e se essa Meal pertence de fato ao Customer identificado por {idCustomer}.
     * Isto é: não é possível alterar as informações de uma Food por uma rota que não seja a correta.
     * 
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer que detém a posse da Meal
     * @param {Number} params.idMeal - id da Meal que detém a posse da Food
     * @param {Number} params.idFood - id da Food a ser alterada
     * 
     * @param {Object} obj - req.body
     * @param {Number} obj.idAliment - id do alimento a ser definido como mainAliment
     * @param {Boolean} obj.isTaco - booleano para definir se o idAliment é referência à tabela Taco ou se é referência à tabela AlimentCustom.
     * @param {Number} obj.quantity - quantidade daquele alimento
     * @param {String} obj.unityQt - unidade de medida daquele alimento
     * @param {String} obj.obs - observações sobre esse alimento
     * 
     * @returns {Promise<{status: number, obj: Food | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto modificado, ou uma mensagem de erro.
     */
    async update(params, obj) {
        try {
            const {idCustomer, idMeal, idFood} = params
            if(!await isCustomerMealFoodProperty(idCustomer, idMeal, idFood)) 
                return errorResult("O usuário não tem esses dados.", 404)

            const food = await Food.findByPk(idFood)
            if(!food) return errorResult("Comida não encontrada.", 404)

            const aliment = await Aliment.findOne({where: {idAliment: obj.idAliment}})
            if(!aliment) return errorResult("idAliment é inválido.", 400)

            try {
                await Food.update(obj, {where: {idFood: idFood, idMeal: idMeal}})
                await food.reload()

                return successResult(await findFoodAndAliment(Food, {idFood: food.idFood}), 200) 

            } catch (error) {
                errorResult("Dados enviados para atualizar a comida principal são inválidos.", 400, error)
            }

        } catch (error) {
            return serverError(error)
        }
    },
}