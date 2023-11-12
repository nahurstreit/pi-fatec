import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getDeletePutAliment = {
    get: {
        description: "Retorna um alimento pelo ID.",
        tags: ["Alimentos"],
        parameters: [
            {
                name: "idAliment",
                in: "path",
                description: "ID do alimento",
                required: true,
                schema: {
                    type: "integer",
                }
            },
        ],
        responses: {
            200: {
                description: "O alimento encontrado.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/alimentGetSchema",
                        },
                    }
                }
            },
            404: {
                description: "Nenhum alimento foi encontrado com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Alimento não encontrado."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    },

    delete: {
        description: "Deleta um alimento pelo ID, desde que seja um alimento personalizado. Não é possível excluir alimentos da Tabela TACO.",
        tags: ["Alimentos"],
        parameters: [
            {
                name: "idAliment",
                in: "path",
                description: "ID do alimento a ser excluído.",
                required: true,
                schema: {
                    type: "integer",
                }
            },
        ],
        responses: {
            200: {
                description: "O alimento foi excluído.",
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
                description: "O alimento com ID informado não pode ser excluído.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Não é possível deletar esse alimento."
                            }
                        },
                    }
                }
            },

            404: {
                description: "Nenhum alimento foi encontrado com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Alimento não encontrado."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    },

    put: {
        description: "Atualiza as informações de um alimento pelo ID, desde que seja um alimento personalizado. Não é possível alterar alimentos da Tabela TACO.",
        tags: ["Alimentos"],
        parameters: [
            {
                name: "idAliment",
                in: "path",
                description: "ID do alimento a ser atualizado.",
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
                        $ref: '#/schemas/alimentPostSchema',
                    }
                }
            },
        },
        responses: {
            200: {
                description: "O alimento atualizado.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/alimentGetSchema",
                            example: {
                                "infoTable": "Alimentos Personalizados",
                                "idAliment": 600,
                                "name": "Banana, prata, crua",
                                "kcal": "98,24970217",
                                "carb": "25,95688406",
                                "protein": "1,268115942",
                                "fat": "0,065"
                            }
                        }
                    }
                }
            },
            400: {
                description: "O corpo da requisição para atualizar o alimento foi enviado incorretamente.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Dados enviados para atualizar o alimento são inválidos.",
                            },
                        }
                    }
                }
            },

            401: {
                description: "O alimento com ID informado não pode ser alterado.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Não é possível alterar esse alimento."
                            }
                        },
                    }
                }
            },

            404: {
                description: "Nenhum alimento foi encontrado com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Alimento não encontrado."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}