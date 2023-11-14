import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getPostCustomer = {
    get:{
        description: "Retorna todos os clientes cadastrados no banco de dados.",
        tags: ["Clientes"],
        responses: {
            200:{
                description: "Lista com todos os Clientes",
                content: {
                    "application/json": {
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
        description: "Cria um novo cliente.",
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
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/customerGetSchema",
                        }
                    }
                }
            },

            400: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) O corpo da requisição para criar uma novo Cliente foi enviado incorretamente. (2) O corpo da requisição não contém todos os dados necessários para criar um novo Cliente.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                exemplo_1: {
                                    erro: "JSON inválido no corpo da solicitação."
                                },
                                exemplo_2: {
                                    erro: "enviar o NOME é obrigatório, enviar o EMAIL é obrigatório, enviar a SENHA é obrigatório, enviar o CPF é obrigatório, enviar o numero do CELULAR é obrigatório, enviar a ALTURA é obrigatório, enviar o PESO é obrigatório, enviar a data do ANIVERSÁRIO é obrigatório, enviar o GÊNERO é obrigatório",
                                },
                            },
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}