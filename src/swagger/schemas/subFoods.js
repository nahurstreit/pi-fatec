export const subFoodsGetSchema = {
    properties:{
        idSubFood: {
            type: "integer",
            description: "ID de armazenamento da subFood a ser buscada."
        },
        idFood: {
            type: "integer",
            description: "ID de armazenamento da food que detém a posse da subFood."
        },
        idAliment: {
            type: "integer",
            description: "ID de registro das informações nutricionais do Alimento."
        },
        quantity: {
            type: "number",
            description: "Quantidade do alimento."
        },
        unityQt: {
            type: "string",
            description: "Unidade de medida do alimento."
        },
        obs: {
            type: "string",
            description: "Observações sobre o alimento."
        }
    },
    example: {
        "idSubFood": 27,
        "idFood": 5,
        "idAliment": 182,
        "quantity": 1,
        "unityQt": "Xícara",
        "obs": "1/2 xícara de abacate amassado equivale a 1 banana média."
    }
}

export const subFoodsPostSchema = {
    required: ["idAliment", "quantity", "unityQt", "obs"],
    properties:{
        idAliment: {
            type: "integer",
            description: "ID de registro das informações nutricionais do Alimento."
        },
        quantity: {
            type: "number",
            description: "Quantidade do alimento."
        },
        unityQt: {
            type: "string",
            description: "Unidade de medida do alimento."
        },
        obs: {
            type: "string",
            description: "Observações sobre o alimento."
        }
    },
    example: {
        "idAliment": 182,
        "quantity": 1,
        "unityQt": "Xícara",
        "obs": "1/2 xícara de abacate amassado equivale a 1 banana média."
    }
}
