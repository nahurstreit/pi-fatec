import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getDeletePutMeals = {
    get: {
        description: "Retorna uma refeição pelo ID.",
        tags: ["Refeições"],
        parameters: [
            {
                name: "idMeal",
                in: "path",
                description: "ID da Refeição",
                required: true,
                schema: {
                    type: "integer",
                }
            },
            {
                name: "IdCustomer",
                in: "path",
                description: "ID do Cliente",
                require: true,
                schema: {
                    type: "integer"
                }
            }
        ],
        responses: {
            200: {
                description: "Refeição encontrada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/mealsGetSchema",
                        },
                    }
                }
            },
            404: {
                description: "Nenhuma refeição foi encontrada com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Refeição não encontrada."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    },

    delete: {
        description: "Deleta uma refeição pelo ID.",
        tags: ["Refeições"],
        parameters: [
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

            401: {
                description: "A refeição com ID informado não pode ser excluída.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Não é possível deletar essa refeição."
                            }
                        },
                    }
                }
            },

            404: {
                description: "Nenhuma refeição foi encontrada com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Refeição não encontrada."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    },

    put: {
        description: "Atualiza as informações de uma refeição pelo ID.",
        tags: ["Refeições"],
        parameters: [
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
                description: "Refeição atualizada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/mealsGetSchema",
                            example: {
                                "idCustomer": 19,
                                "idMeal": 4,"name": "Lanche da manhã",
                                "hour": "10:30",
                                "obs": "O lanche deve ser feito após a refeição do café da manhã e antes da refeição do almoço."
                            }
                        }
                    }
                }
            },
            400: {
                description: "O corpo da requisição para atualizar a refeição foi enviado incorretamente.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Dados enviados para atualizar a refeição são inválidos.",
                            },
                        }
                    }
                }
            },

            401: {
                description: "A refeição com ID informado não pode ser alterada.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Não é possível alterar essa refeição."
                            }
                        },
                    }
                }
            },

            404: {
                description: "Nenhuma refeição foi encontrada com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Refeição não encontrada."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}