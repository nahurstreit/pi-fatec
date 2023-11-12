export const customerGetSchema = {
    properties:{
        idCustomer: {
            type: "integer",
            description: "ID de armazenamento do cliente."
        },
        name: {
            type: "string",
            description: "Nome do cliente."
        },
        email: {
            type: "string",
            description: "Nome do e-mail cadastrado pelo cliente."
        },
        password: {
            type: "string",
            description: "Senha para login do cliente."
        },
        cpf: {
            type: "string",
            description: "CPF referente ao cliente."
        },
        cellphone: {
            type: "string",
            description: "Númeto de celular/telefone do cliente com ddd."
        },
        height: {
            type: "string",
            description: "Altura do cliente."
        },
        weight: {
            type: "string",
            description: "Peso do cliente."
        },
        birth: {
            type: "string",
            description: "Data de nascimento do cliente."
        },
        gender: {
            type: "string",
            description: "Genero ao qual o cliente se identifica."
        }
    },
    example: {
        "idCustomer": 19,
        "name": "José Antônio de Andrade",
        "email": "ze.andrade@gmail.com",
        "password": "Jose#162075",
        "cpf": "099.142.568-77",
        "cellphone": "(19)99428-2713",
        "height": "1.81 m",
        "weight": "92 kg",
        "birth": "07/04/1950",
        "gender": "Masculino"
    }
}

export const customerPostSchema = {
    required: ["name", "email", "password", "cpf", "cellphone", "height", "weight", "birth", "gender"],
    properties:{
        name: {
            type: "string",
            description: "Nome do cliente."
        },
        email: {
            type: "string",
            description: "Nome do e-mail cadastrado pelo cliente."
        },
        password: {
            type: "string",
            description: "Senha para login do cliente."
        },
        cpf: {
            type: "string",
            description: "CPF referente ao cliente."
        },
        cellphone: {
            type: "string",
            description: "Númeto de celular/telefone do cliente com ddd."
        },
        height: {
            type: "string",
            description: "Altura do cliente."
        },
        weight: {
            type: "string",
            description: "Peso do cliente."
        },
        birth: {
            type: "string",
            description: "Data de nascimento do cliente."
        },
        gender: {
            type: "string",
            description: "Genero ao qual o cliente se identifica."
        }
    },
    example: {
        "name": "José Antônio de Andrade",
        "email": "ze.andrade@gmail.com",
        "password": "Jose#162075",
        "cpf": "099.142.568-77",
        "cellphone": "(19)99428-2713",
        "height": "1.81 m",
        "weight": "92 kg",
        "birth": "07/04/1950",
        "gender": "Masculino"
    }
}
