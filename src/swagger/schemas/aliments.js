export const alimentGetSchema = {
    properties:{
        infoTable: {
            type: "string",
            description: "Nome da tabela de referência dos dados."
        },
        idAliment: {
            type: "integer",
            description: "ID de armazenamento do alimento."
        },
        name: {
            type: "string",
            description: "Nome do alimento."
        },
        kcal: {
            type: "string",
            description: "Quantidade de kcal (quilocalorias) por 100g de alimento."
        },
        carb: {
            type: "string",
            description: "Quantidade de gramas de carboidrato por 100g de alimento."
        },
        protein: {
            type: "string",
            description: "Quantidade de gramas de proteína por 100g de alimento."
        },
        fat: {
            type: "string",
            description: "Quantidade de gramas de gordura total por 100g de alimento."
        },
    },
    example: {
        "infoTable": "Tabela Taco",
        "idAliment": 182,
        "name": "Banana, prata, crua",
        "kcal": "98,24970217",
        "carb": "25,95688406",
        "protein": "1,268115942",
        "fat": "0,065"
    }
}

export const alimentGetSchemaCustom = {
    ...alimentGetSchema,
    example: {
        "infoTable": "Alimento Customizado",
        "idAliment": 600,
        "name": "Banana, prata, crua",
        "kcal": "98,24970217",
        "carb": "25,95688406",
        "protein": "1,268115942",
        "fat": "0,065"
    }
}

export const alimentPostSchema = {
    required: ["name", "kcal", "carb", "protein", "fat"],
    properties:{
        name: {
            type: "string",
            description: "Nome do alimento."
        },
        kcal: {
            type: "string",
            description: "Quantidade de kcal (quilocalorias) por 100g de alimento."
        },
        carb: {
            type: "string",
            description: "Quantidade de gramas de carboidrato por 100g de alimento."
        },
        protein: {
            type: "string",
            description: "Quantidade de gramas de proteína por 100g de alimento."
        },
        fat: {
            type: "string",
            description: "Quantidade de gramas de gordura total por 100g de alimento."
        },
    },
    example: {
        "name": "Banana, prata, crua",
        "kcal": "98,24970217",
        "carb": "25,95688406",
        "protein": "1,268115942",
        "fat": "0,065"
    }
}

