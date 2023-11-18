export const foodsGetSchema = {
    properties:{
        idFood: {
            type: "integer",
            description: "ID de armazenamento da food."
        },
        idMeal: {
            type: "integer",
            description: "ID de armazenamento da refeição que detém a posse da food."
        },
        idAliment: {
            type: "integer",
            description: "ID de armazenamento do alimento."
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
        "idFood": 5,
        "idMeal": 4,
        "idAliment": 182,
        "quantity": 2,
        "unityQt": "Unidade(s)",
        "obs": "Corte a banana em fatias.",
        "mainAliment": {
            "idAliment": 182,
            "name": "Banana, prata, crua",
            "kcal": "98,24970217",
            "carb": "25,95688406",
            "protein": "1,268115942",
            "fat": "0,065"
          }
    }
}

export const foodsPostSchema = {
    required: ["idAliment", "quantity", "unityQt"],
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
        "quantity": 2,
        "unityQt": "Unidade(s)",
        "obs": "Corte a banana em fatias."
    }
}