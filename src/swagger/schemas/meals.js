export const mealsGetSchema = {
    properties:{
        idMeal: {
            type: "integer",
            description: "ID de armazenamento da refeição do cliente."
        },
        idCustomer: {
            type: "integer",
            description: "ID de armazenamento do cliente que tem posse desta refeição."
        },
        name: {
            type: "string",
            description: "Nome da refeição."
        },
        hour: {
            type: "string",
            description: "Horário da refeição."
        },
        obs: {
            type: "string",
            description: "Observações sobre a refeição."
        }
    },
    example: {
        "idMeal": 20,
        "idCustomer": 4,
        "name": "Lanche da manhã",
        "hour": "10:30",
        "obs": "O lanche deve ser feito após a refeição do café da manhã e antes da refeição do almoço."
    }
}

export const mealsPostSchema = {
    required: ["name", "hour", "obs"],
    properties:{
        name: {
            type: "string",
            description: "Nome da refeição."
        },
        hour: {
            type: "string",
            description: "Horário da refeição."
        },
        obs: {
            type: "string",
            description: "Observações sobre a refeição."
        }
    },
    example: {
        "name": "Lanche da manhã",
        "hour": "10:30",
        "obs": "O lanche deve ser feito após a refeição do café da manhã e antes da refeição do almoço."
    }
}
