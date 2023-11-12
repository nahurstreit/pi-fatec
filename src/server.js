import express from "express"
import sequelize from "./database/dbConfig.js"

import swaggerUi from "swagger-ui-express"
import swaggerDocument_v0_1_0 from "./swagger/config.js"

import CustomerRoutes from "./router/customer.routes.js"
import AlimentRoutes from "./router/aliment.routes.js"

const PORT = 3000 || 3333

sequelize.sync().then(() => console.log(`Banco de dados sincronizado.`))

const app = express()
app.use(express.json())

//Rota genérica para acessar a documentação MAIS RECENTE da API
app.get('/api/doc[s]', (req, res) => res.redirect(301, '/api/docs/v0.1.0'))

//Rota para acessar o Swagger com respectiva versão da API.
app.use('/api/doc[s]/v0.1.0', swaggerUi.serve, swaggerUi.setup(swaggerDocument_v0_1_0, {explorer: true}))

//Rotas de usuários
const customerRoutes = new CustomerRoutes()
app.use('api/customer[s]?', customerRoutes.routes())

//Rotas de Alimentos (Personalizados ou da Tabela Taco)
const alimentRoutes = new AlimentRoutes()
app.use('/api/aliment[s]?/', alimentRoutes.routes())

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