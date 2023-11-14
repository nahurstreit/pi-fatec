import { error_serverError } from "../../schemas/status500ErrorObj.js"

export const getPostSubFoods = {
    get: {
        description: "Retorna todas as Comidas cadastradas no banco de dados.",
        tags: ["Comidas Substitutas"],
        responses: {
            200: {
                description: "Lista com todas as Comidas Substitutas.",
                content: {
                    "application/json": {
                        schema: {
                            type: "array",
                            items: {
                                $ref: "#/schemas/subFoodsGetSchema",
                            }
                        },
                    }
                }
            },
            ...error_serverError
        },
    },

    post: {
        description: "Cria uma nova Comida Substituta.",
        tags: ["Comidas Substitutas"],
        requestBody: {
            required: true,
            content: {
                "application/json": {
                    schema: {
                        $ref: '#/schemas/subFoodsPostSchema',
                    }
                }
            },
        },
        responses: {
            201: {
                description: "Comida substituta criada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/foodsGetSchema",
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
                description: "O corpo da requisição para criar uma nova comida substituta foi enviado incorretamente.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                erro: "enviar a QUANTIDADE é obrigatório, enviar a UNIDADE DE MEDIDA é obrigatório.",
                            },
                        }
                    }
                }
            },
            ...error_serverError
        },
    }
}