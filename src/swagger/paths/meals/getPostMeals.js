import { error_serverError_All } from "../../schemas/status500ErrorObj.js"

export const getPostMeals = {
    get: {
        description: "Retorna todas as refeições cadastradas no banco de dados para um cliente específico com 'idCustomer'.",
        tags: ["Refeições"],
        parameters: [
            {
                name: "idCustomer",
                in: "path",
                description: "ID do cliente para consultar as refeições.",
                required: true,
                schema: {
                    type: "integer",
                }
            },
        ],
        responses: {
            200: {
                description: "Lista com todas as refeições do cliente informado.",
                content: {
                    "application/json": {
                        schema: {
                            type: "array",
                            items: {
                                $ref: "#/schemas/mealsGetSchema",
                            }
                        },
                    }
                }
            },
            202: {
                description: "O usuário com esse 'idCustomer' ainda não tem nenhuma refeição cadastrada.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                message: "Esse usuário ainda não tem refeições cadastradas."
                            }
                        },
                    }
                }
            },
            ...error_serverError_All
        },
    },

    post: {
        description: "Cria uma nova Refeição para o Cliente informado (idCustomer). Não é possível criar uma refeição para um Cliente que não exista.",
        tags: ["Refeições"],
        parameters: [
            {
                name: "idCustomer",
                in: "path",
                description: "ID do cliente para criar uma refeição.",
                required: true,
                schema: {
                    type: "integer",
                }
            },
        ],
        requestBody: {
            required: true,
            content: {
                "application/json": {
                    schema: {
                        $ref: '#/schemas/mealsPostSchema',
                    }
                }
            },
        },
        responses: {
            201: {
                description: "A refeição foi criada para o cliente informado.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/mealsGetSchema",
                            example: {
                                "idMeal": 4,
                                "idCustomer": 19,
                                "name": "Lanche da manhã",
                                "hour": "10:30",
                                "obs": "O lanche deve ser feito após a refeição do café da manhã e antes da refeição do almoço."
                            }
                        }
                    }
                }
            },

            400: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) idCustomer informado não existe. (2) O corpo da requisição para criar uma nova refeição foi enviado incorretamente. (3) O corpo da requisição não contém todos os dados necessários para criar uma nova refeição.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                exemplo_1: {
                                    erro: "Esse usuário não existe."
                                },
                                exemplo_2: {
                                    erro: "JSON inválido no corpo da solicitação."
                                  },
                                  exemplo_3: {
                                    erro: "enviar o NOME é obrigatório, enviar o HORÁRIO é obrigatório."
                                  }
                            },
                        }
                    }
                }
            },
            
            ...error_serverError_All
        },
    }
}