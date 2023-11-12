export const foodsGetSchema = {
    properties:{
        idCustomer: {
            type: "integer",
            description: "ID de armazenamento do cliente que detém a posse da refeição."
        },
        idMeal: {
            type: "integer",
            description: "ID de armazenamento da refeição que detém a posse da food."
        },
        idFood: {
            type: "integer",
            description: "ID de armazenamento da food."
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
        "idAliment": 182,
        "isTaco": true,
        "quantity": 2,
        "unityQt": "Unidade(s)",
        "obs": "A banana prata é uma das que possuem menos calorias (cerca de 74 kcal por 70 g), ela é rica em potássio e fibras. Além de ter muitos acúcares, como: a sacarose, frutose, e glicose. Também, possui sais minerais, como: cálcio, ferro, sódio, zinco, potássio, magnésio, fósforo e vitaminas A, B1, B2 e C."
    }
}

export const foodsPostSchema = {
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
        "quantity": 2,
        "unityQt": "Unidade(s)",
        "obs": "A banana prata é uma das que possuem menos calorias (cerca de 74 kcal por 70 g), ela é rica em potássio e fibras. Além de ter muitos acúcares, como: a sacarose, frutose, e glicose. Também, possui sais minerais, como: cálcio, ferro, sódio, zinco, potássio, magnésio, fósforo e vitaminas A, B1, B2 e C."
    }
}
