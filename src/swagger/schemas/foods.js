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
        "obs": "A banana prata é uma das que possuem menos calorias (cerca de 74 kcal por 70 g), ela é rica em potássio e fibras. Além de ter muitos acúcares, como: a sacarose, frutose, e glicose. Também, possui sais minerais, como: cálcio, ferro, sódio, zinco, potássio, magnésio, fósforo e vitaminas A, B1, B2 e C."
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
        "obs": "A banana prata é uma das que possuem menos calorias (cerca de 74 kcal por 70 g), ela é rica em potássio e fibras. Além de ter muitos acúcares, como: a sacarose, frutose, e glicose. Também, possui sais minerais, como: cálcio, ferro, sódio, zinco, potássio, magnésio, fósforo e vitaminas A, B1, B2 e C."
    }
}