import Schema from "validate"

const customerSchema = new Schema ({
  name: {
    type: String,
    required: true,
    message: "enviar o NOME é obrigatório"
  },
  email: {
    type: String,
    required: true,
    message: "enviar o EMAIL é obrigatório"
  },
  password: {
    type: String,
    required: true,
    message: "enviar a SENHA é obrigatório"
  },
  cpf: {
    type: String,
    required: true,
    message: "enviar o CPF é obrigatório"
  },
  cellphone: {
    type: String,
    required: true,
    message: "enviar o TELEFONE é obrigatório"
  },
  height: {
    type: String,
    required: true,
    message: "enviar a ALTURA é obrigatório"
  },
  weight: {
    type: String,
    required: true,
    message: "enviar o PESO é obrigatório"
  },
  gender: {
    type: String,
    required: true,
    message: "enviar o SEXO é obrigatório"
  },
  birth: {
    type: String,
    required: true,
    message: "enviar a DATA DE NASCIMENTO é obrigatório"
  }
})

export default customerSchema
