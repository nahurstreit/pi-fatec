import Schema from "validate"

const foodSchema = new Schema ({
  idAliment: {
    type: Number,
    required: true,
    message: "enviar o ID DO ALIMENTO é obrigatório"
  },
  taco: {
    type: Boolean,
    required: true,
    message: "declarar se É DA TABELA TACO é obrigatório (apenas true ou false)"
  },
  quantity: {
    type: Number,
    required: true,
    message: "enviar a QUANTIDADE é obrigatório"
  },
  unityQt: {
    type: Number,
    required: true,
    message: "enviar a UNIDADE DE MEDIDA é obrigatório"
  },
  obs: {
    type: String,
    required: false
  }
})

export default foodSchema
