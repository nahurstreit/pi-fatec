import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getDeletePutAliment = {
    get: {
        description: "Retorna um alimento pelo ID.",
        tags: ["Alimentos", "Alimentos Customizados"],
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
        tags: ["Alimentos", "Alimentos Customizados"],
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
                description: "O alimento com ID informado não pode ser excluído. (Obs.: Alimentos da Tabela TACO não podem ser deletados.)",
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
        tags: ["Alimentos", "Alimentos Customizados"],
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
                description: "Esse status é retornado quando uma dessas situações acontece: (1) O corpo da requisição para atualizar o Alimento Customizado foi enviado incorretamente. (2) O corpo da requisição está tentando atualizar dados inválidos.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                exemplo_1: {
                                    erro: "JSON inválido no corpo da solicitação."
                                },
                                exemplo_2: {
                                    erro: "Dados enviados para atualizar o alimento são inválidos.",
                                }
                            },
                        }
                    }
                }
            },

            401: {
                description: "O alimento com ID informado não pode ser alterado. (Obs.: Alimentos da Tabela TACO não podem ser alterados.)",
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