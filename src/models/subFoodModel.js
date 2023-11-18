import SubFood from "../entities/Foods/SubFood.js"
import { isCustomerMealFoodProperty, findFoodAndAliment, formatFoodResponse } from "../helpers/foodUtils.js"

import * as validateReq from "../helpers/validator/validateReq.js"
import foodSchema from "../helpers/validator/schemas/foodSchema.js"

import { successResult, errorResult, serverError } from "../helpers/responseUtils.js"

import Aliment from "../entities/Aliment.js"

export default {
    /**
     * Procura todas as SubFoods no banco de dados que pertencem a uma determinada Food, com chave primária igual à {idFood}
     * passada pelos parâmetros da requisição, desde que essa Food pertença a Meal identificada por {idMeal} e essa Meal
     * pertença ao Customer indicado por {idCustomer}.
     * 
     * @param {Object} params
     * @param {Number} params.idCustomer - id do Customer que detém a posse da Meal
     * @param {Number} params.idMeal - id da Meal que detém a posse da Food
     * @param {Number} params.idFood - id da Food que detém a posse das SubFoods a serem buscadas.
     * 
     * @returns {Promise<{status: number, obj: SubFood[]}>} JSON de resposta à requisição, contendo um status code HTTP e um Array de Food.
     */
    async all(params) {
        try {
            const {idCustomer, idMeal, idFood} = params
            if(!await isCustomerMealFoodProperty(idCustomer, idMeal, idFood))
                return errorResult("Usuário, refeição ou comida principal não encontrado(s).", 404)

            const subFoodsAll = await SubFood.findAll({where: {idFood: idFood}, include: [{model: Aliment, as: "mainAliment"}]})
            if(subFoodsAll.length < 1) return successResult({message: "Ainda não existem comidas substitutas."}, 202)

            const subFoodsPromises = subFoodsAll.map(async (subFood) => {
                return formatFoodResponse(subFood)
            })

            const subFoods = await Promise.all(subFoodsPromises)
            
            return successResult(subFoods, 200)

        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Procura uma SubFood específica cuja chave primária é igual a {idSubFood}, passada pelos parâmetros da requisição, desde que
     * essa SubFood pertença de fato à Food com chave primária {idFood}, que deve pertencer à Meal identificada por
     * {idMeal} que por sua vez, deve pertencer ao Customer identificado por {idCustomer}.
     * 
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer que detém a posse da Meal
     * @param {Number} params.idMeal - id da Meal que detém a posse da Food
     * @param {Number} params.idFood - id da Food que detém a posse da SubFood
     * @param {Number} params.idSubFood - id da SubFood a ser buscada
     * 
     * @returns {Promise<{status: number, obj: SubFood | {erro: string}>}} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto encontrado, ou uma mensagem de erro.
     */
    async findSubFoodById(params) {
        try {
            const {idCustomer, idMeal, idFood, idSubFood} = params
            if(!await isCustomerMealFoodProperty(idCustomer, idMeal, idFood)) 
                return errorResult("Usuário, refeição ou comida principal não encontrado(s).", 404)

            const subFood = await findFoodAndAliment(SubFood, {idSubFood: idSubFood, idFood: idFood})
            if(!subFood) return errorResult("Comida Substituta não encontrada.", 404)

            return successResult(subFood, 200)
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta criar uma nova SubFood de acordo com as informações no corpo da requisição (req.body) para a Food indicada
     * em {idFood}, desde que essa Food pertença à {idMeal} indicado em {idMeal}, que por sua vez, deve pertencer ao Customer
     * indicado em {idCustomer}.
     * Criará somente se o corpo da requisição atender aos padrões estabelecidos em foodSchema. Assim como em uma Food.
     * @see {@link ./src/helpers/validator/schemas/foodSchema.js}
     * 
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer que detém a posse da Meal
     * @param {Number} params.idMeal - id da Meal que detém a posse da Food
     * @param {Number} params.idFood - id da Food em que será adicionada a nova SubFood
     * 
     * @param {Object} obj - req.body
     * @param {Number} obj.idAliment - id do alimento a ser definido como mainAliment
     * @param {Boolean} obj.isTaco - booleano para definir se o idAliment é referência à tabela Taco ou se é referência à tabela AlimentCustom.
     * @param {Number} obj.quantity - quantidade daquele alimento
     * @param {String} obj.unityQt - unidade de medida daquele alimento
     * @param {String} obj.obs - observações sobre esse alimento
     * 
     * @returns {Promise<{status: number, obj: SubFood | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto criado, ou uma mensagem de erro.
     */
    async create(params, obj) {
        try {
            const {idCustomer, idMeal, idFood} = params
            if(!await isCustomerMealFoodProperty(idCustomer, idMeal, idFood)) 
                return errorResult("Usuário, refeição ou comida principal não encontrado(s).", 404)

            const errors = validateReq.post(obj, foodSchema)
            if(errors) return errorResult(errors, 400)

            const aliment = await Aliment.findOne({where: {idAliment: obj.idAliment}})
            if(!aliment) return errorResult("idAliment é inválido.", 400)
            
            const subFood = await SubFood.create({
                idFood: Number(idFood),
                ...obj,
            })

            return successResult(await findFoodAndAliment(SubFood, {idSubFood: subFood.idSubFood}), 201) 
            
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta deletar do banco de dados um registro de SubFood
     * cuja chave primária é determinada por {idSubFood}, passada pelos parâmetros da requisição.
     * 
     * O registro de SubFood será deletado do banco de dados apenas se a Food, identificada por {idFood}, 
     * for a real proprietária daquela SubFood, além disso, essa Food deve pertencer à Meal indicada por {idMeal},
     * que por sua vez, deve pertencer ao Customer identificado por {idCustomer}.
     * Isto é: não é possível excluir uma SubFood por uma rota que não seja a correta.
     * 
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer que detém a posse da Meal
     * @param {Number} params.idMeal - id da Meal que detém a posse da Food
     * @param {Number} params.idFood - id da Food que detém a posse da SubFood
     * @param {Number} params.idSubFood - id da SubFood a ser deletada
     * 
     * @returns {Promise<{status: number, obj: {message: string} | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e uma mensagem de sucesso ou erro.
     */
    async delete(params) {
        const {idCustomer, idMeal, idFood, idSubFood} = params 
        if(!await isCustomerMealFoodProperty(idCustomer, idMeal, idFood)) 
            return errorResult("Usuário, refeição ou comida principal não encontrado(s).", 404)

        try {
            const result = await SubFood.destroy({where: {idSubFood: idSubFood, idFood: idFood}})
            if(result === 0) return errorResult("Comida Substituta não encontrada.", 400)
            return successResult({mensagem: "Registro excluído."}, 200) 
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta fazer alterações em uma SubFood já criada, cuja chave primária é {idSubFood},
     * passada automaticamente através dos parâmetros da requisição.
     * 
     * Serão alterados os campos do registro de SubFood apenas se a Food, identificada por {idFood}, 
     * for a real proprietária daquela SubFood, além disso, essa Food deve pertencer à Meal indicada por {idMeal},
     * que por sua vez, deve pertencer ao Customer identificado por {idCustomer}.
     * Isto é: não é possível alterar as informações de uma SubFood por uma rota que não seja a correta.
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
     * @returns {Promise<{status: number, obj: SubFood | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto modificado, ou uma mensagem de erro.
     */
    async update(params, obj) {
        try {
            const {idCustomer, idMeal, idFood, idSubFood} = params
            if(!await isCustomerMealFoodProperty(idCustomer, idMeal, idFood)) 
                return errorResult("Usuário, refeição ou comida principal não encontrado(s).", 404)

            const subFood = await SubFood.findOne({where: {idSubFood: idSubFood, idFood: idFood}})
            if(!subFood) return errorResult("Comida Substituta não encontrada.", 404)

            if("idAliment" in obj) {
                const aliment = await Aliment.findOne({where: {idAliment: obj.idAliment}})
                if(!aliment) return errorResult("idAliment é inválido.", 400)
            }

            try {
                await SubFood.update(obj, {where: {idSubFood: idSubFood, idFood: idFood}})
                await subFood.reload()

                return successResult(await findFoodAndAliment(SubFood, {idSubFood: subFood.idSubFood}), 200) 

            } catch (error) {
                errorResult("Dados enviados para atualizar a comida substituta são inválidos.", 400, error)
            }

        } catch (error) {
            return serverError(error)
        }
    },
}