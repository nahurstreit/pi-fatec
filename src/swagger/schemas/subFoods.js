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
        "idAliment": 163,
        "quantity": 1,
        "unityQt": "Xícara",
        "obs": "1/2 xícara de abacate amassado equivale a 1 banana média.",
        "mainAliment": {
            "idAliment": 163,
            "name": "Abacate, cru",
            "kcal": "96,1547087",
            "carb": "6,030869565",
            "protein": "1,239130435",
            "fat": "8,396666667"
          }
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
        "idAliment": 163,
        "quantity": 1,
        "unityQt": "Xícara",
        "obs": "1/2 xícara de abacate amassado equivale a 1 banana média."
    }
}
