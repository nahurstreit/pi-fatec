import Customer from "../entities/Users/Customer.js"
import * as validateReq from "../helpers/validator/validateReq.js"
import customerSchema from "../helpers/validator/schemas/customerSchema.js"
import { successResult, errorResult, serverError } from "../helpers/responseUtils.js"

export default {
    
    async all() {
        try {
            const customers = await Customer.findAll()
            return successResult(customers, 200)
        } catch (error) {
            return serverError(error)
        }
    },

    async findById(params){
        try{
            const { idCustumer } = params
            const custumer = await Customer.findByPk(idCustumer)
            if(!custumer) return errorResult("Usuário não encontrado.", 404)
            return successResult(custumer, 200)
        } catch (error){
            return serverError(error)
        }
    },

    async create(params, obj){
        try{
            const errors = validateReq.post(obj, customerSchema)
            if(errors) return errorResult(errors, 400)
            try{
                const custumer = await Customer.create(obj)
                return successResult(custumer, 201)
            } catch (error){
                return errorResult("Usuário não pode ser criado.", 400, error)
            }
        } catch (error){
            return serverError(error)
        }
    },

    async delete(params){
        const { idCustumer } = params
        try{
            const result = await Customer.destroy({where: {idCustumer: idCustumer}})
            if(result != 1) return errorResult("Usuário não encontrado.", 404)
            return successResult({mensagem: "Registro excluido."}, 200)
        } catch(error){
            return serverError(error)
        }
    },

    async update(params, body) {
        try {
            const { idCustomer } = params
            const customer = await Customer.findByPk(idCustomer)
            if (!customer) {
                return errorResult("Usuário não encontrado.", 404)
            }
            try {
                await customer.update(body)
                await customer.reload()
                return successResult(customer, 200)
            } catch (error) {
                return errorResult("Erro ao tentar atualizar o usuário.", 400, error)
            }
        } catch (error) {
            return serverError(error)
        }
    }
}