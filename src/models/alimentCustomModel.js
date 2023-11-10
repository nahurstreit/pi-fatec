import AlimentCustom from "../entities/Aliments/AlimentCustom.js"
import * as validateReq from "../helpers/validator/validateReq.js"
import alimentCustomSchema from "../helpers/validator/schemas/alimentCustomSchema.js"
import { successResult, errorResult, serverError } from "../helpers/responseUtils.js"

export default {
    /**
     * Procura um AlimentCustom específico cuja chave primária é igual a {idAliment}, passada pelos parâmetros da requisição.
     * @param {Object} params - req.params
     * @param {Number} params.idAliment
     * 
     * @returns {Promise<{status: number, obj: AlimentCustom | {erro: string}>}} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto encontrado, ou uma mensagem de erro.
     */
    async findById(params) {
        const {idAliment} = params
        try {
            const aliment = await AlimentCustom.findByPk(idAliment)
            if(!aliment) return errorResult("Alimento não encontrado.", 404) 
            return successResult(aliment, 200)
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta criar um novo AlimentCustom de acordo com as informações no corpo da requisição (req.body).
     * Criará somente se o corpo da requisição atendar aos padrões estabelecidos em alimentCustomSchema.
     * @see {@link ./src/helpers/validator/schemas/alimentCustomSchema.js}
     * 
     * @param {Object} params - req.params: Não utilizado, mas necessário declarar.
     * 
     * @param {Object} obj - req.body
     * @param {String} obj.name - Nome do alimento a ser criado
     * @param {Number} obj.kcal - Quantidade de quilocalorias
     * @param {Number} obj.carb - Quantidade de carboidratos
     * @param {Number} obj.protein - Quantidade de proteína
     * @param {Number} obj.fat - Quantidade de Gordura Total
     * 
     * @returns {Promise<{status: number, obj: AlimentCustom | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto criado, ou uma mensagem de erro.
     */
    async create(params, obj) {
        try {
            const errors = validateReq.post(obj, alimentCustomSchema)
            if(errors) return errorResult(errors, 400) 

            try {
                const aliment = await AlimentCustom.create(obj)
                return successResult(aliment, 201)
            } catch (error) {
                return errorResult("Alimento não pôde ser criado.", 400, error)
            }
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função para deletar do banco de dados um registro de AlimentCustom
     * cuja chave primária é determinada por {idAliment}, passada pelos parâmetros da requisição.
     * @param {Object} params - req.params
     * @param {Number} params.idAliment - id do alimento a ser excluído
     * 
     * @returns {Promise<{status: number, obj: {message: string} | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e uma mensagem de sucesso ou erro.
     */
    async delete(params) {
        try {
            const {idAliment} = params
            const result = await AlimentCustom.destroy({where: {idAliment: Number(idAliment)}})
            if(result != 1) return errorResult("Registro não encontrado.", 404) 

            return successResult("Registro excluído.", 200)
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função para fazer alterações em um AlimentCustom já criado com determinado idAliment. 
     * @param {Object} params - req.params
     * @param {Number} params.idAliment - id do alimento a ser atualizado.
     * 
     * @param {Object} obj - req.body
     * @param {String?} obj.name - Nome do alimento a ser criado
     * @param {Number?} obj.kcal - Quantidade de quilocalorias
     * @param {Number?} obj.carb - Quantidade de carboidratos
     * @param {Number?} obj.protein - Quantidade de proteína
     * @param {Number?} obj.fat - Quantidade de Gordura Total
     * 
     * @returns {Promise<{status: number, obj: AlimentCustom | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto modificado, ou uma mensagem de erro.
     */
    async update(params, obj) {
        try {
            const {idAliment} = params

            const aliment = await AlimentCustom.findByPk(idAliment)
            if(!aliment) return errorResult("Alimento não encontrado", 404) 

            try {
                await AlimentCustom.update(obj, {where: {idAliment: idAliment}})
                await aliment.reload()
                return successResult(aliment, 200)
            } catch (error) {
                return errorResult("Dados enviados para atualizar o alimento são inválidos.", 400, error)
            }

        } catch (error) {
            return serverError(error)
        }
    },
}