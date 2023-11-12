import {error_serverError} from "../../schemas/status500ErrorObj.js"

export const getAllTaco = {
    get: {
        description: "Retorna todos os alimentos do banco de dados que são da Tabela TACO.",
        tags: ["Alimentos Tabela Taco"],
        responses: {
            200: {
                description: "Lista com os alimentos da Tabela TACO.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/aliments",
                        },
                    }
                }
            },
            ...error_serverError
        },
    },
}

export const getAllCustom = {
    get: {
        description: "Retorna todos os alimentos customizados do banco de dados.",
        tags: ["Alimentos Customizados"],
        responses: {
            200: {
                description: "Lista com os alimentos customizados.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/alimentGetSchemaCustom",
                        },
                    }
                }
            },
            ...error_serverError
        },
    },
}