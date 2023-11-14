import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getDeletePutSubFoods = {
    get: {
        description: "Retorna uma comida substituta pelo ID.",
        tags: ["Comidas Substitutas"],
        parameters: [
            {
                name: "idSubFood",
                in: "path",
                description: "ID da Comida Substituta.",
                required: true,
                schema: {
                    type: "interger"
                }
            },
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
                description: "Comida Substituta encontrada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/subFoodsGetSchema",
                        },
                    }
                }
            },
            404: {
                description: "Nenhuma Comida Substituta foi encontrada com o ID informado.",
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
        description: "Deleta uma Comida Substituta pelo ID, desde que seja uma comida substituta personalizada. Não é possível excluir Comidas Substitutas da Tabela TACO.",
        tags: ["Comidas Substitutas"],
        parameters: [
            {
                name: "idSubFoods",
                in: "path",
                description: "ID da Comida Substituta a ser excluída.",
                required: true,
                schema: {
                    type: "integer",
                }
            },
        ],
        responses: {
            200: {
                description: "A Comida Substituta foi excluída.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                message: "Comida Substituta excluída."
                            }
                        },
                    }
                }
            },

            401: {
                description: "A Comida Substituta com ID informado não pode ser excluída.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Não é possível deletar essa Comida Substituta."
                            }
                        },
                    }
                }
            },

            404: {
                description: "Nenhuma Comida Substituta foi encontrada com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Comida Substituta não encontrada."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    },

    put: {
        description: "Atualiza as informações de uma Comida Substituta pelo ID, desde que seja uma Comida Substiituta personalizada. Não é possível alterar comidas da Tabela TACO.",
        tags: ["Comidas Substitutas"],
        parameters: [
            {
                name: "idSubFoods",
                in: "path",
                description: "ID da Comida Substituta a ser atualizada.",
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
                        $ref: '#/schemas/subFoodsPostSchema',
                    }
                }
            },
        },
        responses: {
            200: {
                description: "Comida Substituta atualizada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/subFoodsGetSchema",
                            example: {
                                "idCustomer": 19,
                                "idMeal": 4,
                                "idFood": 5,
                                "idSubFood": 27,
                                "idAliment": 182,
                                "isTaco": true,
                                "quantity": 1,
                                "unityQt": "Xícara",
                                "obs": "1/2 xícara de abacate amassado equivale a 1 banana média."
                            }
                        }
                    }
                }
            },
            400: {
                description: "O corpo da requisição para atualizar a Comida Substituta foi enviado incorretamente.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Dados enviados para atualizar a Comida Substituta são inválidos.",
                            },
                        }
                    }
                }
            },

            401: {
                description: "A Comida Substituta com ID informado não pode ser alterada.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Não é possível alterar essa Comida Substituta."
                            }
                        },
                    }
                }
            },

            404: {
                description: "Nenhuma Comida Substituta foi encontrada com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Comida Substituta não encontrada."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}