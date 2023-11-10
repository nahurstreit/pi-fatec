import express from "express"
import sequelize from "./database/dbConfig.js"
import CustomerRoutes from "./router/customer.routes.js"
import * as AlimentRoutes from "./router/aliment.routes.js"

const PORT = 3000 || 3333

sequelize.sync().then(() => console.log(`Banco de dados sincronizado.`))

const app = express()
app.use(express.json())

//Rotas de usuários
const customerRoutes = new CustomerRoutes()
app.use('/user[s]?', customerRoutes.routes())

//Rotas de Alimentos da Tabela TACO
const tacoRoutes = new AlimentRoutes.TacoRoutes()
app.use('/api/v1/taco/', tacoRoutes.routes())

//Rotas de Alimentos Customizados
const alimentCustomRoutes = new AlimentRoutes.AlimentCustomRoutes()
app.use('/alimentCustom', alimentCustomRoutes.routes())

//Middleware para requisição em rotas inválidas ou para rotas não existentes.
app.use((req, res) => {
  res.status(404).json({mensagem: "Rota não encontrada ou inválida."});
})

//Middlware para tratar os erros de solicitção do Usuário no body da requisição.
app.use((erro, req, res, next) => {
    if (erro instanceof SyntaxError) {
      res.status(400).json({error: 'JSON inválido no corpo da solicitação.'})
    } else {
      next(erro)
    }
})

app.listen(PORT, () => {
    console.log(`App rodando na porta ${PORT}.`)
})