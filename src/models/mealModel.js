import Meal from "../entities/Meal.js"
import * as validateReq from "../helpers/validator/validateReq.js"
import mealSchema from "../helpers/validator/schemas/mealSchema.js"
import { successResult, errorResult, serverError } from "../helpers/responseUtils.js"

export default {
    /**
     * Procura todas as Meals no banco de dados de um determinado Customer cuja chave primária é igual à {idCustomer},
     * passada pelos parâmetros da requisição.
     * 
     * @param {Object} params
     * @param {Number} params.idCustomer - id do Customer que detém as Meals a serem buscadas
     * 
     * @returns {Promise<{status: number, obj: Meal[]}>} JSON de resposta à requisição, contendo um status code HTTP e um Array de Meal.
     */
    async all(params) {
        const {idCustomer} = params
        try {
            const meals = await Meal.findAll({where: {idCustomer: idCustomer}})
            return successResult(meals, 200)
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Procura uma Meal específica cuja chave primária é igual a {idMeal}, passada pelos parâmetros da requisição, desde que
     * essa Meal pertença de fato ao Customer com chave primária {idCustomer}, também passado pelos parâmetros.
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer
     * @param {Number} params.idMeal - id da Meal
     * 
     * @returns {Promise<{status: number, obj: Meal | {erro: string}>}} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto encontrado, ou uma mensagem de erro.
     */
    async findMealById(params) {
        try {
            const {idCustomer, idMeal} = params
            const meal = await Meal.findOne({where: {idMeal: idMeal, idCustomer: idCustomer}})
            if(!meal) return errorResult("O usuário não tem essa refeição.")
            return successResult(meal, 200)
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta criar uma nova Meal de acordo com as informações no corpo da requisição (req.body) para o Customer
     * indicado em {idCustomer}.
     * Criará somente se o corpo da requisição atender aos padrões estabelecidos em mealSchema.
     * @see {@link ./src/helpers/validator/schemas/mealSchema.js}
     * 
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer em que será adicionada a nova Meal
     * 
     * @param {Object} obj - req.body
     * @param {String} obj.name - Nome da refeição
     * @param {String} obj.hour - Horário da refeição
     * @param {String?} obj.obs - Observações sobre a refeição.
     * 
     * @returns {Promise<{status: number, obj: Meal | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto criado, ou uma mensagem de erro.
     */
    async create(params, obj) {
        try {
            const errors = validateReq.post(obj, mealSchema)
            if(errors) return errorResult(errors, 400)
            try {
                const {idCustomer} = params
                const meal = await Meal.create({
                    ...obj,
                    idCustomer: Number(idCustomer)
                })
                return successResult(meal, 201)
            } catch (error) {
                return errorResult("Refeição não pôde ser criada.", 400, error)
            }
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta deletar do banco de dados um registro de Meal
     * cuja chave primária é determinada por {idMeal}, passada pelos parâmetros da requisição.
     * 
     * Será deletado do banco de dados apenas se o Customer, identificado por idCustomer, for o real proprietário daquela refeição.
     * Isto é: não é possível excluir uma Meal por uma rota que não seja a correta.
     * 
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer que detem a posse da Meal
     * @param {Number} params.idMeal - id da Meal a ser excluída
     * 
     * @returns {Promise<{status: number, obj: {message: string} | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e uma mensagem de sucesso ou erro.
     */
    async delete(params) {
        try {
            const {idCustomer, idMeal} = params
            const result = await Meal.destroy({where: {idMeal: idMeal, idCustomer: idCustomer}})
            if(result != 1) return errorResult("O usuário não tem essa refeição.", 404)
            return successResult({mensagem: "Registro excluído."}, 200)
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta fazer alterações em uma Meal já criada, cuja chave primária é {idMeal},
     * passada automaticamente através dos parâmetros da requisição.
     * 
     * Serão alterados os campos do registro de Meal apenas se o Customer, identificado por idCustomer, for o real proprietário
     * daquela Meal. Isto é: não é possível alterar as informações de uma Meal por uma rota que não seja a correta.
     * 
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer que detem a posse da Meal
     * @param {Number} params.idMeal - id da Meal a ser alterada
     * 
     * @param {Object} obj - req.body
     * @param {String} obj.name - Nome da refeição
     * @param {String} obj.hour - Horário da refeição
     * @param {String?} obj.obs - Observações sobre a refeição.
     * 
     * @returns {Promise<{status: number, obj: Meal | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto modificado, ou uma mensagem de erro.
     */
    async update(params, obj) {
        try {
            const {idCustomer, idMeal} = params
            const meal = await Meal.findOne({where: {idCustomer: idCustomer,idMeal: idMeal}})
            if(!meal) return errorResult("O usuário não tem essa refeição.", 404) 

            try {
                await Meal.update(obj, {where: {idMeal: idMeal, idCustomer: idCustomer}})
                await meal.reload()
                return successResult(meal, 200)
            } catch (error) {
                return errorResult("Dados enviados para atualizar o usuário são inválidos.", 400)
            }

        } catch (error) {
            return serverError(error)
        }
    },
}