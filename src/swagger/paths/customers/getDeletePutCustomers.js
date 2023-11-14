import { error_serverError_All } from "../../schemas/status500ErrorObj.js"

export const getDeletePutCustomer = {
    get: {
        description: "Retorna um cliente pelo ID.",
        tags: ["Clientes"],
        parameters: [
            {
                name: "idCustomer",
                in: "path",
                description: "ID do cliente.",
                required: true,
                schema: {
                    type: "integer",
                }
            },
        ],
        responses: {
            200: {
                description: "cliente encontrado.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/customerGetSchema",
                        },
                    }
                }
            },
            
            404: {
                description: "Nenhum cliente foi encontrado com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Usuário não encontrado."
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    },

    delete: {
        description: "Deleta um cliente pelo ID.",
        tags: ["Clientes"],
        parameters: [
            {
                name: "idCustomer",
                in: "path",
                description: "ID do cliente a ser excluído.",
                required: true,
                schema: {
                    type: "integer",
                }
            },
        ],
        responses: {
            200: {
                description: "O Cliente foi excluído.",
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
                description: "Nenhum cliente foi encontrado com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                erro: "Usuário não encontrado."
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    },

    put: {
        description: "Atualiza as informações de um cliente pelo ID.",
        tags: ["Clientes"],
        parameters: [
            {
                name: "idCustomer",
                in: "path",
                description: "ID do cliente a ser atualizado.",
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
                        $ref: "#/schemas/customerPostSchema",
                    }
                }
            },
        },
        responses: {
            200: {
                description: "Cliente atualizado.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/customerGetSchema",
                            example: {
                                "idCustomer": 19,
                                "name": "José Antônio de Andrade",
                                "email": "ze.andrade@gmail.com",
                                "password": "Jose#162075",
                                "cpf": "099.142.568-77",
                                "cellphone": "(19)99428-2713",
                                "height": "1.81 m",
                                "weight": "92 kg",
                                "birth": "07/04/1950",
                                "gender": "Masculino"
                            }
                        }
                    }
                }
            },

            400: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) O corpo da requisição para atualizar o Cliente foi enviado incorretamente. (2) O corpo da requisição está tentando atualizar dados inválidos.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                exemplo_1: {
                                  erro: "JSON inválido no corpo da solicitação."
                                },
                                exemplo_2: {
                                  erro: "Dados enviados para atualizar o usuário são inválidos."
                                }
                            }
                        },
                    }
                }
            },

            404: {
                description: "Nenhum Cliente foi encontrado com o ID informado.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Usuário não encontrado."
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    }
}
