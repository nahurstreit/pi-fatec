import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getPostAliment = {
    get: {
        description: "Retorna todos os alimentos cadastrados no banco de dados.",
        tags: ["Alimentos"],
        responses: {
            200: {
                description: "Lista com todos os alimentos.",
                content: {
                    "application/json": {
                        schema: {
                            type: "array",
                            items: {
                                $ref: "#/schemas/alimentGetSchema",
                            }
                        },
                    }
                }
            },
            ...error_serverError
        },
    },

    post: {
        description: "Cria um novo Alimento Customizado.",
        tags: ["Alimentos", "Alimentos Customizados"],
        requestBody: {
            required: true,
            content: {
                "application/json": {
                    schema: {
                        $ref: '#/schemas/alimentPostSchema',
                    }
                }
            },
        },
        responses: {
            201: {
                description: "O alimento criado.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/alimentGetSchemaCustom",
                        }
                    }
                }
            },
            400: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) O corpo da requisição para criar um novo Alimento Customizado foi enviado incorretamente. (2) O corpo da requisição não contém os dados obrigatórios para criação de um Alimento Customizado.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                exemplo_1: {
                                    erro: "JSON inválido no corpo da solicitação."
                                },
                                exemplo_2: {
                                    erro: "enviar o NOME é obrigatório, enviar a quantidade de PROTEÍNA é obrigatório",
                                }
                            },
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}