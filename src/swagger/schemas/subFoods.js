export const subFoodsGetSchema = {
    properties:{
        idCustomer: {
            type: "integer",
            description: "ID de armazenamento do cliente."
        },
        idMeal: {
            type: "integer",
            description: "ID de armazenamento da refeição que possui esta food."
        },
        idFood: {
            type: "integer",
            description: "ID de armazenamento da food que detém a posse da subFood."
        },
        idSubFood: {
            type: "integer",
            description: "ID de armazenamento da subFood a ser buscada."
        },
        idAliment: {
            type: "integer",
            description: "ID de armazenamento do alimento."
        },
        isTaco: {
            type: "boolean",
            description: "Saber se idAliment é referente à tabela Taco ou se é referente à tabela AlimentCustom."
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

export const subFoodsPostSchema = {
    required: ["quantity", "unityQt", "obs"],
    properties:{
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
        "quantity": 1,
        "unityQt": "Xícara",
        "obs": "1/2 xícara de abacate amassado equivale a 1 banana média."
    }
}
