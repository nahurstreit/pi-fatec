import { error_serverError_All } from "../../schemas/status500ErrorObj.js"

export const getDeletePutSubFoods = {
    get: {
        description: "Retorna uma Comida Substituta específica do banco de dados com id:'idSubFood'. Só é possível acessar essa comida caso pertença à Comida Principal informada (idFood). Essa Comida Principal deve pertencer à Refeição informada (idMeal) e essa Refeição deve pertencer ao Cliente informado (idCustomer).",
        tags: ["Comidas Substitutas"],
        parameters: [
            {
                name: "idCustomer",
                in: "path",
                description: "ID do Cliente.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idMeal",
                in: "path",
                description: "ID da Refeição.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idFood",
                in: "path",
                description: "ID da Comida Principal.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idSubFood",
                in: "path",
                description: "ID da Comida Substituta.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
        ],
        responses: {
            200: {
                description: "A Comida Substituta com 'idSubFood encontrada para a Comida Principal informada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/subFoodsGetSchema",
                        },
                    }
                }
            },

            404: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) 'idCustomer', 'idMeal', 'idFood' e/ou 'idSubFood' não existe(m), ou os id's existem mas não têm ligação entre si (a refeição não pertence àquele cliente, a comida não pertence àquela refeição, ou a comida substituta não substitui aquela comida principal). (2) O Cliente tem aquela Refeição e a Refeição tem aquela Comida Principal, mas a Comida Substituta de id:'idSubFood' não existe para a Comida Principal.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                exemplo_1: {
                                    erro: "Usuário, refeição ou comida principal não encontrado(s)."
                                },
                                exemplo_2: {
                                    erro: "Comida Substituta não encontrada."
                                },
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    },

    delete: {
        description: "Tenta deletar uma Comida Substituta pelo 'idSubFood'. Só é possível deletar essa Comida Substituta caso pertença seja substituta da Comida Principal informada (idFood). Essa Comida Principal deve pertencer à Refeição informada (idMeal) e essa Refeição deve pertencer ao Cliente informado (idCustomer).",
        tags: ["Comidas Substitutas"],
        parameters: [
            {
                name: "idCustomer",
                in: "path",
                description: "ID do Cliente.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idMeal",
                in: "path",
                description: "ID da Refeição.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idFood",
                in: "path",
                description: "ID da Comida Principal.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idSubFood",
                in: "path",
                description: "ID da Comida Substituta.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
        ],
        responses: {
            200: {
                description: "A Comida Substituta foi excluída.",
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
                description: "Esse status é retornado quando uma dessas situações acontece: (1) 'idCustomer', 'idMeal', 'idFood' e/ou 'idSubFood' não existe(m), ou os id's existem mas não têm ligação entre si (a refeição não pertence àquele cliente, a comida não pertence àquela refeição, ou a comida substituta não substitui aquela comida principal). (2) O Cliente tem aquela Refeição e a Refeição tem aquela Comida Principal, mas a Comida Substituta de id:'idSubFood' não existe para a Comida Principal.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                exemplo_1: {
                                    erro: "Usuário, refeição ou comida principal não encontrado(s)."
                                },
                                exemplo_2: {
                                    erro: "Comida Substituta não encontrada."
                                },
                            }
                        }
                    }
                }
            },
            ...error_serverError_All
        },
    },

    put: {
        description: "Tenta atualizar as informações de uma Comida Substituta pelo 'idSubFood'. Só é possível atualizar as informações dessa Comida Substituta caso seja substituta da Comida Principal informada (idFood). Essa Comida principal deve pertencer à Refeição informada (idMeal) e essa Refeição deve pertencer ao Cliente informado (idCustomer).",
        tags: ["Comidas Substitutas"],
        parameters: [
            {
                name: "idCustomer",
                in: "path",
                description: "ID do Cliente.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idMeal",
                in: "path",
                description: "ID da Refeição.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idFood",
                in: "path",
                description: "ID da Comida Principal.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
            {
                name: "idSubFood",
                in: "path",
                description: "ID da Comida Substituta.",
                required: true,
                schema: {
                    type: "integer"
                }
            },
        ],
        requestBody: {
            required: true,
            content: {
                "application/json": {
                    schema: {
                        required: [""],
                        $ref: '#/schemas/subFoodsPostSchema',
                    }
                }
            },
        },
        responses: {
            200: {
                description: "Comida Substituta atualizada.",
                content: {
                    "application/json": {
                        schema: {
                            $ref: "#/schemas/subFoodsGetSchema",
                        }
                    }
                }
            },

            400: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) O corpo da requisição para atualizar a Comida Substituta foi enviado incorretamente. (2) O corpo da requisição está tentando atualizar dados inválidos. (3) O idAliment passado no corpo da requisição não existe no banco de dados.",
                content: {
                    "application/json": {
                        schema: {
                            type: "object",
                            example: {
                                exemplo_1: {
                                    erro: "JSON inválido no corpo da solicitação."
                                  },
                                exemplo_2: {
                                    erro: "Dados enviados para atualizar a comida substituta são inválidos."
                                },
                                exemplo_3: {
                                    erro: "idAliment é inválido."
                                },
                            },
                        }
                    }
                }
            },

            404: {
                description: "Esse status é retornado quando uma dessas situações acontece: (1) 'idCustomer', 'idMeal', 'idFood' e/ou 'idSubFood' não existe(m), ou os id's existem mas não têm ligação entre si (a refeição não pertence àquele cliente, a comida não pertence àquela refeição, ou a comida substituta não substitui aquela comida principal). (2) O Cliente tem aquela Refeição e a Refeição tem aquela Comida Principal, mas a Comida Substituta de id:'idSubFood' não existe para a Comida Principal.",
                content: {
                    "application/json": {
                        schema: {
                            example: {
                                exemplo_1: {
                                    erro: "Usuário, refeição ou comida principal não encontrado(s)."
                                },
                                exemplo_2: {
                                    erro: "Comida Substituta não encontrada."
                                },
                            }
                        }
                    }
                }
            },

            ...error_serverError_All
        },
    }
}