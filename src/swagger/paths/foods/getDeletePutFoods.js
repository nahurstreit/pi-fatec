import { error_serverError_All } from "../../schemas/status500ErrorObj.js"

export const getDeletePutFoods = {
    get: {
        description: "Retorna uma Comida Principal específica do banco de dados com id:'idFood'. Só é possível acessar essa Comida Principal caso pertença à Refeição informada (idMeal). Essa Refeição deve pertencer ao Cliente informado (idCustomer).",
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
                    type: "integer"
                }
            },
            {
                name: "idFood",
                in: "path",
                description: "ID da Comida Principal.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
        ],
        responses: {
            200: {
                description: "A Comida Principal com 'idFood' encontrada na Refeição informada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/foodsGetSchema",
                        },
                    }
                }
            },

            404: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) 'idCustomer' e/ou 'idMeal' não existe(m), ou os id's existem mas não têm ligação entre si (a refeição não pertence àquele cliente). (2) O Cliente tem aquela Refeição, mas a Comida Principal de id:'idFood' não existe para a Refeição.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                exemplo_1: {
                                    erro: "O usuário não tem esses dados."
                                },
                                exemplo_2: {
                                    erro: "Comida não encontrada."
                                },
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    },

    delete: {
        description: "Tenta deletar uma Comida Principal pelo 'idFood'. Só é possível deletar essa comida caso pertença à Refeição informada (idMeal) e essa Refeição pertença ao Cliente informado (idCustomer).",
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
                    type: "integer"
                }
            },
            {
                name: "idFood",
                in: "path",
                description: "ID da Comida Principal a ser deletada.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
        ],
        responses: {
            200: {
                description: "A Comida Principal foi excluída.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                message: "Registro excluído."
                            }
                        },
                    }
                }
            },

            404: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) 'idCustomer' e/ou 'idMeal' não existe(m), ou os id's existem mas não têm ligação entre si (a refeição não pertence àquele cliente). (2) O cliente tem aquela refeição, mas a comida de id:'idFood' não existe.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                exemplo_1: {
                                    erro: "Usuário, refeição ou comida principal não encontrado(s)."
                                },
                                exemplo_2: {
                                    erro: "Comida não encontrada."
                                },
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    },

    put: {
        description: "Tenta atualizar as informações de uma Comida Principal pelo 'idFood'. Só é possível atualizar as informações dessa Comida Principal caso pertença à Refeição informada (idMeal) e essa Refeição deve pertencer ao Cliente informado (idCustomer).",
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
                    type: "integer"
                }
            },
            {
                name: "idFood",
                in: "path",
                description: "ID da Comida Principal a ser atualizada.",
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
                        required: [""],
                        $ref: '#/schemas/foodsPostSchema',
                    }
                }
            },
        },
        responses: {
            200: {
                description: "A Comida Principal atualizada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/foodsGetSchema",
                        }
                    }
                }
            },

            400: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) O corpo da requisição para atualizar a Comida Principal foi enviado incorretamente. (2) O corpo da requisição está tentando atualizar dados inválidos. (3) O idAliment passado no corpo da requisição não existe no banco de dados.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                exemplo_1: {
                                    erro: "JSON inválido no corpo da solicitação."
                                  },
                                exemplo_2: {
                                    erro: "Dados enviados para atualizar a comida principal são inválidos."
                                },
                                exemplo_3: {
                                    erro: "idAliment é inválido."
                                },
                            },
                        }
                    }
                }
            },

            404: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) 'idCustomer', 'idMeal' e/ou 'idFood' não existe(m), ou os id's existem mas não têm ligação entre si (a refeição não pertence àquele cliente ou a comida não pertence àquela refeição). (2) O Cliente tem aquela Refeição, mas a Comida Principal de id:'idFood' não existe para a Refeição.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                exemplo_1: {
                                    erro: "O usuário não tem esses dados."
                                },
                                exemplo_2: {
                                    erro: "Comida não encontrada."
                                },
                            }
                        }
                    }
                }
            },

            ...error_serverError_All
        },
    }
}