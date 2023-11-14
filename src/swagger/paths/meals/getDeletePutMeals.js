import { error_serverError_All } from "../../schemas/status500ErrorObj.js"

export const getDeletePutMeals = {
    get: {
        description: "Retorna uma refeição específica, com 'idMeal', de algum cliente específico, com 'idCustomer'. Não é possível visualizar refeições cujo 'idMeal' pertença a outro 'idCustomer'.",
        tags: ["Refeições"],
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
                description: "Refeição encontrada com 'idMeal' que pertença a 'idCustomer'.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/mealsGetSchema",
                        },
                    }
                }
            },
            404: {
                description: "Nenhuma refeição foi encontrada com 'idMeal' que pertença a 'idCustomer'.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "O usuário não tem essa refeição."
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    },

    delete: {
        description: "Tenta deletar uma refeição específica, com 'idMeal', de algum cliente específico, com 'idCustomer'. Não é possível deletar refeições cujo 'idMeal' pertença a outro 'idCustomer'.",
        tags: ["Refeições"],
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
                description: "ID da refeição a ser excluída.",
                required: true,
                schema: {
                    type: "integer",
                }
            },
        ],
        responses: {
            200: {
                description: "A refeição foi excluída.",
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
                description: "Nenhuma refeição com 'idMeal' que pertence a 'idCustomer' foi encontrada.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "O usuário não tem essa refeição."
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    },

    put: {
        description: "Tenta atualizar uma refeição específica, com 'idMeal', de algum cliente específico, com 'idCustomer'. Não é possível deletar refeições cujo 'idMeal' pertença a outro 'idCustomer'.",
        tags: ["Refeições"],
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
                description: "ID da refeição a ser atualizada.",
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
                        required: [""],
                        $ref: '#/schemas/mealsPostSchema',
                    }
                }
            },
        },
        responses: {
            200: {
                description: "A refeição atualizada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/mealsGetSchema",
                        }
                    }
                }
            },
            400: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) O corpo da requisição para criar uma nova refeição foi enviado incorretamente. (2) O corpo da requisição está tentando atualizar dados inválidos.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                exemplo_1: {
                                    "erro": "JSON inválido no corpo da solicitação."
                                  },
                                  exemplo_2: {
                                    erro: "Dados enviados para atualizar o usuário são inválidos."
                                  }
                            },
                        }
                    }
                }
            },

            404: {
                description: "Nenhuma refeição com 'idMeal' que pertence a 'idCustomer' foi encontrada.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "O usuário não tem essa refeição."
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    }
}