import AlimentTaco from "../entities/Aliments/AlimentTaco.js"
import { successResult, serverError, errorResult } from "../helpers/responseUtils.js"

export default {
    /**
     * Procura um AlimentTaco específico cuja chave primária é igual a {idAliment}, passada pelos parâmetros da requisição.
     * @param {Object} params - req.params
     * @param {Number} params.idAliment
     * 
     * @returns {Promise<{status: number, obj: AlimentTaco | {erro: string}>}} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto encontrado, ou uma mensagem de erro.
     */
    async findById(params) {
        try {
            const {idAliment} = params
            const aliment = await AlimentTaco.findByPk(idAliment)
            if(!aliment) return errorResult("Alimento não encontrado.", 404)
            return successResult(aliment, 200)
        } catch (error) {
            return serverError(error)
        }
    },
}