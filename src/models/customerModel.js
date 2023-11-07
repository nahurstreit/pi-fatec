import Customer from "../entities/Users/Customer.js"
import * as validateReq from "../helpers/validator/validateReq.js"
import customerSchema from "../helpers/validator/schemas/customerSchema.js"
import { successResult, errorResult, serverError } from "../helpers/responseUtils.js"

export default {
    /**
     * Procura todos os Customers no banco de dados.
     * @returns {Promise<{status: number, obj: Customer[]}>} JSON de resposta à requisição, contendo um status code HTTP e um Array de Customer.
     */
    async all() {
        try {
            const customers = await Customer.findAll()
            return successResult(customers, 200)
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Procura um Customer específico cuja chave primária é igual a {idCustomer}, passada pelos parâmetros da requisição.
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer
     * 
     * @returns {Promise<{status: number, obj: Customer | {erro: string}>}} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto encontrado, ou uma mensagem de erro.
     */
    async findById(params) {
        try {
            const {idCustomer} = params
            const customer = await Customer.findByPk(idCustomer)
            if(!customer) return errorResult("Usuário não encontrado.", 404)
            return successResult(customer, 200)
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta criar um novo Customer de acordo com as informações no corpo da requisição (req.body).
     * Criará somente se o corpo da requisição atendar aos padrões estabelecidos em customerSchema.
     * @see {@link ./src/helpers/validator/schemas/customerSchema.js}
     * 
     * @param {Object} params - req.params: Não utilizado, mas necessário declarar.
     * 
     * @param {Object} obj - req.body
     * @param {String} obj.name - Nome do Customer
     * @param {String} obj.email - Email
     * @param {String} obj.password - Senha
     * @param {String} obj.cpf - CPF
     * @param {String} obj.cellphone - Celular/Telefone
     * @param {String} obj.height - Altura
     * @param {String} obj.weight - Peso
     * @param {String} obj.birth - Data de Nascimento
     * @param {String} obj.gender - Gênero
     * 
     * @returns {Promise<{status: number, obj: Customer | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto criado, ou uma mensagem de erro.
     */
    async create(params, obj) {
        try {
            const errors = validateReq.post(obj, customerSchema)
            if(errors) return errorResult(errors, 400)

            try {
                const customer = await Customer.create(obj)
                return successResult(customer, 201)
            } catch (error) {
                return errorResult("Usuário não pôde ser criado.", 400, error)
            }
            
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta deletar do banco de dados um registro de Customer
     * cuja chave primária é determinada por {idCustomer}, passada pelos parâmetros da requisição.
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer a ser excluído
     * 
     * @returns {Promise<{status: number, obj: {message: string} | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e uma mensagem de sucesso ou erro.
     */
    async delete(params) {
        const {idCustomer} = params
        try {
            const result = await Customer.destroy({where: {idCustomer: idCustomer}})
            if(result != 1) return errorResult("Usuário não encontrado.", 404) 
            return successResult({mensagem: "Registro excluído."}, 200)
        } catch (error) {
            return serverError(error)
        }
    },

    /**
     * Função que tenta fazer alterações em um Customer já criado, cuja chave primária é {idCustomer},
     * passada automaticamente através dos parâmetros da requisição.
     * @param {Object} params - req.params
     * @param {Number} params.idCustomer - id do Customer a ser atualizado.
     * 
     * @param {Object} obj - req.body
     * @param {String?} obj.name - Nome do Customer
     * @param {String?} obj.email - Email
     * @param {String?} obj.password - Senha
     * @param {String?} obj.cpf - CPF
     * @param {String?} obj.cellphone - Celular/Telefone
     * @param {String?} obj.height - Altura
     * @param {String?} obj.weight - Peso
     * @param {String?} obj.birth - Data de Nascimento
     * @param {String?} obj.gender - Gênero
     * 
     * @returns {Promise<{status: number, obj: Customer | {erro: string}}>} JSON de resposta à requisição, contendo um status code HTTP e, ou o objeto modificado, ou uma mensagem de erro.
     */
    async update(params, obj) {
        const {idCustomer} = params
        try {
            const customer = await Customer.findByPk(idCustomer)
            if(!customer) return errorResult("Usuário não encontrado.", 404)

            try {
                await Customer.update(obj, {where: {idCustomer: idCustomer}})
                await customer.reload()
                return successResult(customer, 200) 

            } catch (error) {
                return errorResult("Dados enviados para atualizar o usuário são inválidos.", 400)
            }

        } catch (error) {
            return serverError(error)
        }
    } 
}