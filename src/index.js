import Customer from "./entities/Users/Customer.js"

//testando a classe Customer
const novo = new Customer({
    name: "Teste",
    email: "teste@mail.com",
    password: "12345",
    cpf: "123.456.789-10",
    cellphone: "(00) 9 0000-0000",
    address: "Rua Teste",
    gender: "Male",
    height: "170cm",
    weight: "80kg",
    birth: "01/01/2000"
})

console.log(novo)