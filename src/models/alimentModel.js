import * as validateReq from "../helpers/validator/validateReq.js"
import alimentSchema from "../helpers/validator/schemas/alimentSchema.js"
import { successResult, errorResult, serverError } from "../helpers/responseUtils.js"
import Aliment from "../entities/Aliment.js"

import { formatAlimentResponse } from "../helpers/alimentUtils.js"

export default {
    /**
     * Procura todos os Aliments no banco de dados.
     * @returns {Promise<{status: number, obj: Aliment[]}>} JSON de resposta à requisição, contendo um status code HTTP e um Array de Customer.
     */
        async all() {
            try {
                const allAliment = await Aliment.findAll()
                if(allAliment.length < 1) return serverError("Não foi possível recuperar os dados.")
    
                const alimentPromises = allAliment.map((aliment) => {
                    return formatAlimentResponse(aliment, true)
                })
    
                const aliments = await Promise.all(alimentPromises)
    
                return successResult(aliments, 200)
            } catch (error) {
                return serverError(error)
            }
        },

    /**
     * Procura todos os Alimentos fixados da tabela Taco no banco de dados.
     * @returns {Promise<{status: number, obj: Aliment[]}>} JSON de resposta à requisição, contendo um status code HTTP e um Array de Customer.
     */
    async allTaco() {
        try {
            const allAlimentTaco = await Aliment.findAll({where: {custom: 0}})
            if(allAlimentTaco.length < 1) return successResult({message: "Não foi possível recuperar os dados."}, 500)

            const alimentTacoPromises = allAlimentTaco.map((aliment) => {
                return formatAlimentResponse(aliment)
            })

            const alimentTaco = await Promise.all(alimentTacoPromises)

            return successResult(alimentTaco, 200)
        } catch (error) {
            return serverError(error)
        }
    },

        /**
     * Procura todos os Alimentos Personalizados do banco de dados.
     * @returns {Promise<{status: number, obj: Aliment[]}>} JSON de resposta à requisição, contendo um status code HTTP e um Array de Customer.
     */
        async allCustom() {
            try {
                const allAlimentCustom = await Aliment.findAll({where: {custom: 1}})
                if(allAlimentCustom.length < 1) return successResult({message: "Ainda não existem alimentos customizados cadastrados."}, 200)
                
                const alimentCustomPromises = allAlimentCustom.map((aliment) => {
                    return formatAlimentResponse(aliment)
                })
                
                const alimentCustom = Promise.all(alimentCustomPromises)

                return successResult(alimentCustom, 200)
            } catch (error) {
                return serverError(error)
            }
        },

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
            const aliment = await Aliment.findByPk(idAliment)
            if(!aliment) return errorResult("Alimento não encontrado.", 404)
            return successResult(formatAlimentResponse(aliment, true), 200)
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
        console.log(obj)
        try {
            const errors = validateReq.post(obj, alimentSchema)
            if(errors) return errorResult(errors, 400)

            console.log(obj)

            try {
                const aliment = await Aliment.create({...obj, custom: 1})
                return successResult(aliment, 201)
            } catch (error) {
                return serverError(error, "Alimento não pôde ser criado.")
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

            const aliment = await Aliment.findByPk(idAliment)
            if(!aliment) return errorResult("Alimento não encontrado", 404)
            if(aliment.custom == 0) return errorResult("Não é possível deletar esse alimento.", 401)

            const result = await Aliment.destroy({where: {idAliment: Number(idAliment)}})
            if(result != 1) return serverError("Houve um erro ao excluir o registro") 

            return successResult({message: "Registro excluído."}, 200)
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

            const aliment = await Aliment.findByPk(idAliment)
            if(!aliment) return errorResult("Alimento não encontrado", 404)
            if(aliment.custom == 0) return errorResult("Não é possível alterar esse alimento.", 401)

            try {
                await Aliment.update({...obj, custom: 1}, {where: {idAliment: idAliment}})
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