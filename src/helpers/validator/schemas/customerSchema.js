import Schema from "validate"

const customerSchema = new Schema ({
  name: {
    type: String,
    required: true,
    message: "enviar o nome é OBRIGATÓRIO"
  },
  email: {
    type: String,
    required: true,
    message: "enviar o email é OBRIGATÓRIO"
  },
  password: {
    type: String,
    required: true,
    message: "enviar a senha é OBRIGATÓRIO"
  },
  cpf: {
    type: String,
    required: true,
    message: "enviar o CPF é OBRIGATÓRIO"
  },
  cellphone: {
    type: String,
    required: true,
    message: "enviar o telefone é OBRIGATÓRIO"
  },
  height: {
    type: String,
    required: true,
    message: "enviar a altura é OBRIGATÓRIO"
  },
  weight: {
    type: String,
    required: true,
    message: "enviar o peso é OBRIGATÓRIO"
  },
  gender: {
    type: String,
    required: true,
    message: "enviar o sexo é OBRIGATÓRIO"
  },
  birth: {
    type: String,
    required: true,
    message: "enviar a data de nascimento é OBRIGATÓRIO"
  }
})

export default customerSchema