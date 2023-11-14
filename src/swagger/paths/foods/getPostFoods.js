import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getPostFoods = {
    get: {
        description: "Retorna todas as Comidas cadastradas no banco de dados.",
        tags: ["Comidas"],
        responses: {
            200: {
                description: "Lista com todas as Comidas.",
                content: {
                    "application/json": {
                        schema: {
                            type: "array",
                            items: {
                                $ref: "#/schemas/foodsGetSchema",
                            }
                        },
                    }
                }
            },
            ...error_serverError
        },
    },

    post: {
        description: "Cria uma nova Comida",
        tags: ["Comidas"],
        requestBody: {
            required: true,
            content: {
                "application/json": {
                    schema: {
                        $ref: '#/schemas/foodsPostSchema',
                    }
                }
            },
        },
        responses: {
            201: {
                description: "Comida criada.",
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
                description: "O corpo da requisição para criar uma nova comida foi enviado incorretamente.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "Enviar a QUANTIDADE é obrigatório, enviar a UNIDADE DE MEDIDA é obrigatório.",
                            },
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}