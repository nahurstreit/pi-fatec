import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getDeletePutCustomer = {
    get: {
        description: "Retorna um cliente pelo ID.",
        tags: ["Clientes"],
        parameters: [
            {
                name: "IdCustomer",
                in: "path",
                description: "ID do cliente",
                require: true,
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
                                erro: "Cliente não encontrado."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    },

    delete: {
        description: "Deleta um cliente pelo ID",
        tags: ["Clientes"],
        parameters: [
            {
                name: "idCustomer",
                in: "path",
                description: "ID do cliente a ser excluido.",
                required: true,
                schema: {
                    type: "integer",
                }
            },
        ],
        responses: {
            200: {
                description: "o cliente foi excluido.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                message: "Registro excluido"
                            }
                        },
                    }
                }
            },

            401: {
                description: "O cliente com o ID informado não pode ser excluido.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Não foi possivel deletar esse cliente"
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
                                erro: "Cliente não encontrado."
                            }
                        }
                    }
                }
            },
            ...error_serverError
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
                description: "O corpo da requisição para atualizar o cliente foi enviado incorretamente.",
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
                description: "O cliente com o ID informado não pode ser alterado.",
                content: {
                    "application/json":{
                        schema:{
                            type: "object",
                            example: {
                                erro: "Não é possivel alterar esse cliente"
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
                                erro: "Cliente não encontrado."
                            }
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}
