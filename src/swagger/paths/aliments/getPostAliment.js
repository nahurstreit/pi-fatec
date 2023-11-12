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
        description: "Cria um novo Alimento Customizado",
        tags: ["Alimentos"],
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
                description: "O corpo da requisição para criar um novo alimento foi enviado incorretamente.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "enviar o NOME é obrigatório, enviar a quantidade de PROTEÍNA é obrigatório",
                            },
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}