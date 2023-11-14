import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getPostMeals = {
    get: {
        description: "Retorna todas as refeições cadastradas no banco de dados.",
        tags: ["Refeições"],
        responses: {
            200: {
                description: "Lista com todas as refeições.",
                content: {
                    "application/json": {
                        schema: {
                            type: "array",
                            items: {
                                $ref: "#/schemas/mealsGetSchema",
                            }
                        },
                    }
                }
            },
            ...error_serverError
        },
    },

    post: {
        description: "Cria uma nova refeição",
        tags: ["Refeições"],
        requestBody: {
            required: true,
            content: {
                "application/json": {
                    schema: {
                        $ref: '#/schemas/mealsPostSchema',
                    }
                }
            },
        },
        responses: {
            201: {
                description: "A refeição foi criada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/mealsGetSchema",
                            example: {
                                "idCustomer": 19,
                                "idMeal": 4,
                                "name": "Lanche da manhã",
                                "hour": "10:30",
                                "obs": "O lanche deve ser feito após a refeição do café da manhã e antes da refeição do almoço."
                            }
                        }
                    }
                }
            },
            400: {
                description: "O corpo da requisição para criar uma nova refeição foi enviado incorretamente.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "enviar o NOME é obrigatório, enviar o HORÁRIO é obrigatório.",
                            },
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}