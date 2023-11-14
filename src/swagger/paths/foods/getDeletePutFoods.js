import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getDeletePutFoods = {
    get: {
        description: "Retorna uma comida pelo ID.",
        tags: ["Comidas"],
        parameters: [
            {
                name: "idFood",
                in: "path",
                description: "ID da Comida",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idMeal",
                in: "path",
                description: "ID da Refeição",
                required: true,
                schema: {
                    type: "interger"
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
            },
            {
                name: "idAliment",
                in: "path",
                description: "ID do Alimento",
                required: true,
                schema: {
                    type: "integer"
                }
            }
        ],
        responses: {
            200: {
                description: "A comida encontrada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/foodsGetSchema",
                        },
                    }
                }
            },
            404: {
                description: "Nenhuma comida foi encontrada com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Comida não encontrada."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    },

    delete: {
        description: "Deleta uma comida pelo ID, desde que seja uma comida personalizada. Não é possível excluir Comidas da Tabela TACO.",
        tags: ["Comidas"],
        parameters: [
            {
                name: "idFoods",
                in: "path",
                description: "ID da comida a ser excluída.",
                required: true,
                schema: {
                    type: "integer",
                }
            },
        ],
        responses: {
            200: {
                description: "A comida foi excluída.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                message: "Comida excluída."
                            }
                        },
                    }
                }
            },

            401: {
                description: "A comida com ID informado não pode ser excluída.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Não é possível deletar essa comida."
                            }
                        },
                    }
                }
            },

            404: {
                description: "Nenhuma comida foi encontrada com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Comida não encontrada."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    },

    put: {
        description: "Atualiza as informações de uma comida pelo ID, desde que seja uma comida personalizada. Não é possível alterar comidas da Tabela TACO.",
        tags: ["Comidas"],
        parameters: [
            {
                name: "idFoods",
                in: "path",
                description: "ID da comida a ser atualizada.",
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
                        $ref: '#/schemas/foodsPostSchema',
                    }
                }
            },
        },
        responses: {
            200: {
                description: "A comida atualizada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/foodsGetSchema",
                            example: {
                                "idCustomer": 19,
                                "idMeal": 4,
                                "idFood": 5,
                                "idAliment": 182,
                                "isTaco": true,
                                "quantity": 2,
                                "unityQt": "Unidade(s)",
                                "obs": "A banana prata é uma das que possuem menos calorias (cerca de 74 kcal por 70 g), ela é rica em potássio e fibras. Além de ter muitos acúcares, como: a sacarose, frutose, e glicose. Também, possui sais minerais, como: cálcio, ferro, sódio, zinco, potássio, magnésio, fósforo e vitaminas A, B1, B2 e C."
                            }
                        }
                    }
                }
            },
            400: {
                description: "O corpo da requisição para atualizar a comida foi enviado incorretamente.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Dados enviados para atualizar a comida são inválidos.",
                            },
                        }
                    }
                }
            },

            401: {
                description: "A comida com ID informado não pode ser alterada.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Não é possível alterar essa comida."
                            }
                        },
                    }
                }
            },

            404: {
                description: "Nenhuma comida foi encontrada com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Comida não encontrada."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}