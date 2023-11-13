import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getPostCustomer = {
    get:{
        description: "Retorna todos os clientes cadastrados no banco de dados.",
        tags: ["Clientes"],
        responses: {
            200:{
                description: "Lista com todos os Clientes",
                content: {
                    "aplication/json": {
                        schema: {
                            type: "array",
                            items: {
                                $ref: "#/schemas/customerGetSchema",
                            }
                        },
                    }
                }
            },
            ...error_serverError
        },
    },

    post: {
        description: "Cria um novo cliente",
        tags:["Clientes"],
        requestBody: {
            required: true,
            content: {
                "application/json": {
                    schema: {
                        $ref: "#/schemas/customerPostSchema",
                    }
                }
            },
        },
        responses: {
            201: {
                description: "O cliente foi criado.",
                content: {
                    "aplication/json": {
                        schema: {
                            $ref: "#/schemas/customerGetSchema",
                            example: {
                                "idCustomer": 19,
                                "name": "José Antônio de Andrade",
                                "email": "ze.andrade@gmail.com",
                                "password": "Jose#162075",
                                "cpf": "099.142.568-77",
                                "cellphone": "(19)99428-2713",
                                "height": "1.81 m",
                                "weight": "92 kg",
                                "birth": "07/04/1950",
                                "gender": "Masculino"
                            }
                        }
                    }
                }
            },
            400: {
                description: "O corpo da requisição para criar um novo cliente foi enviado incorretamente.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                error: "enviar o NOME é obrigatório, enviar o EMAIL é obrigatório, enviar a SENHA é obrigatório, enviar o CPF é obrigatório, enviar o numero do CELULAR é obrigatório, enviar a ALTURA é obrigatório, enviar o PESO é obrigatório, enviar a data do ANIVERSÁRIO é obrigatório, enviar o GÊNERO é obrigatório",
                            },
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}