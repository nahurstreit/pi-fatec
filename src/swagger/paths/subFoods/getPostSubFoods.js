import { error_serverError_All } from "../../schemas/status500ErrorObj.js"

export const getPostSubFoods = {
    get: {
        description: "Retorna uma lista com todas as Comidas Substitutas cadastradas no banco de dados para uma Comida Principal informada (idFood). A Comida Principal informada deve pertencer à Refeição informada (idMeal), que deve pertencer ao Cliente informado (idCustomer).",
        tags: ["Comidas Substitutas"],
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
                    type: "integer"
                }
            },
            {
                name: "idFood",
                in: "path",
                description: "ID da Comida.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
        ],
        responses: {
            200: {
                description: "Lista com todas as Comidas Substitutas da Comida Principal informada.",
                content: {
                    "application/json": {
                        schema: {
                            type: "array",
                            items: {
                                $ref: "#/schemas/subFoodsGetSchema",
                            }
                        },
                    }
                }
            },
            
            202: {
                description: "A Comida Principal de id:'idFood' ainda não tem nenhuma Comida Substituta.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                message: "Ainda não existem comidas substitutas."
                            }
                        },
                    }
                }
            },

            404: {
                description: "'idCustomer', 'idMeal' e/ou 'idFood' não existe(m), ou os id's existem mas não têm ligação entre si (a refeição não pertence àquele cliente ou a comida não pertence àquela refeição).",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Usuário, refeição ou comida principal não encontrado(s)."
                            }
                        }
                    }
                }
            },

            ...error_serverError_All
        },
    },

    post: {
        description: "Cria uma nova Comida Substituta para a Comida Principal informada com 'idFood'.  Não é possível criar uma Comida Substituta para uma Comida Principal que não exista. Essa comida Principal deve pertencer à Refeição informada (idMeal) e essa refeição deve pertencer ao Cliente informado (idCustomer).",
        tags: ["Comidas Substitutas"],
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
                    type: "integer"
                }
            },
            {
                name: "idFood",
                in: "path",
                description: "ID da Comida.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
        ],
        requestBody: {
            required: true,
            content: {
                "application/json": {
                    schema: {
                        $ref: '#/schemas/subFoodsPostSchema',
                    }
                }
            },
        },
        responses: {
            201: {
                description: "Comida Substituta criada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/subFoodsGetSchema",
                        }
                    }
                }
            },

            400: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) O corpo da requisição para criar uma nova Comida Substituta foi enviado incorretamente. (2) O corpo da requisição não contém os dados obrigatórios para criação de uma Comida Substituta. (3) O 'idAliment' passado no corpo da requisição não existe no banco de dados.",
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
                description: "'idCustomer', 'idMeal' e/ou 'idFood' não existe(m), ou os id's existem mas não têm ligação entre si (a refeição não pertence àquele cliente ou a comida não pertence àquela refeição).",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Usuário, refeição ou comida principal não encontrado(s)."
                            }
                        }
                    }
                }
            },

            ...error_serverError_All
        },
    }
}