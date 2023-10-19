import Customer from "./entities/Users/Customer.js"

//testando a classe Customer
const enderecoCliente = {
    street: "Rua Teste",
    number: 123,
    complement: null,
    cep: 12345160,
    city: "Indaiatuba"
}

const refeicao1 = {
    name: "Café da manhã",
    hour: "08:00",
    foods: [
        {
            idAlimentTaco: 0,
            quantity: 2,
            unityQt: "fatias",
            obs: "Cortar bem finas"
        }
    ]
}

const refeição2 = {
    name: "Almoço",
    hour: "12:00",
    obs: "Não comer rápido",
    foods: [
        {
            idAlimentTaco: 1,
            quantity: 300,
            unityQt: "gramas",
            subFood: [
                {
                    idAlimentTaco: null,
                    idAlimentCustom: 0,
                    quantity: 200,
                    unityQt: "gramas",
                    obs: "Cortar bem finas"
                }
            ]
        }
    ]
}

const testeCliente = new Customer({
    name: "Teste",
    email: "teste@mail.com",
    password: "12345",
    cpf: "123.456.789-10",
    cellphone: "(00) 9 0000-0000",
    address: enderecoCliente,
    gender: "Male",
    height: "170cm",
    weight: "80kg",
    birth: "01/01/2000",
    diet: {
        meal1: refeicao1,
        meal2: refeição2
    }
})

console.log(testeCliente)