import { error_serverError_All } from "../../schemas/status500ErrorObj.js"

export const getPostFoods = {
    get: {
        description: "Retorna uma lista com todas as Comidas Principais cadastradas no banco de dados para uma Refeição informada (idFood). A Refeição informada deve pertencer ao Cliente informado (idCustomer).",
        tags: ["Comidas Principais"],
        parameters: [
            {
                name: "idCustomer",
                in: "path",
                description: "ID do Cliente.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idMeal",
                in: "path",
                description: "ID da Refeição.",
                required: true,
                schema: {
                    type: "integer",
                }
            }
        ],
        responses: {
            200: {
                description: "Lista com todas as comidas para aquela refeição do cliente.",
                content: {
                    "application/json": {
                        schema: {
                            type: "array",
                            items: {
                                $ref: "#/schemas/foodsGetSchema",
                            }
                        },
                    }
                }
            },

            202: {
                description: "A refeição com 'idMeal' ainda não tem nenhuma comida cadastrada.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                message: "Ainda não existem comidas para essa refeição."
                            }
                        },
                    }
                }
            },

            404: {
                description: "'idCustomer' e/ou 'idMeal' não existe(m), ou os id's existem mas não têm ligação entre si (a refeição não pertence àquele cliente).",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "O usuário não tem esses dados."
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    },

    post: {
        description: "Cria uma nova Comida Principal na refeição informada com 'idMeal'.  Não é possível criar uma Comida Principal para uma Refeição que não exista. A Refeição informada (idMeal) deve ao Cliente informado (idCustomer).",
        tags: ["Comidas Principais"],
        parameters: [
            {
                name: "idCustomer",
                in: "path",
                description: "ID do Cliente.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idMeal",
                in: "path",
                description: "ID da Refeição.",
                required: true,
                schema: {
                    type: "integer",
                }
            }
        ],
        requestBody: {
            required: true,
            content: {
                "application/json": {
                    schema: {
                        $ref: '#/schemas/foodsPostSchema',
                    }
                }
            },
        },
        responses: {
            201: {
                description: "A comida criada na refeição informada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/foodsGetSchema",
                        }
                    }
                }
            },
            400: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) O corpo da requisição para criar uma nova Comida Principal foi enviado incorretamente. (2) O corpo da requisição não contém os dados obrigatórios para criação de uma Comida Principal. (3) O 'idAliment' passado no corpo da requisição não existe no banco de dados.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                exemplo_1: {
                                    erro: "JSON inválido no corpo da solicitação."
                                },
                                exemplo_2: {
                                    erro: "Enviar o ID do Alimento é obrigatório, enviar a QUANTIDADE é obrigatório, enviar a UNIDADE DE MEDIDA é obrigatório.",
                                },
                                exemplo_3: {
                                    erro: "idAliment é inválido."
                                }
                            },
                            
                        }
                    }
                }
            },

            404: {
                description: "'idCustomer' e/ou 'idMeal' não existe(m), ou os id's existem mas não têm ligação entre si (a refeição não pertence àquele cliente).",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Usuário ou Refeição não encontrada."
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    }
}