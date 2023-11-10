/**
 * Middleware para facilitar a comunicação entre a definição de rotas e as funções de Model.
 * Evita a complexidade de informações nos arquivos de rota, que sempre devem retornar a mesma coisa.
 * @param {*} req 
 * @param {*} res 
 * @param {Function} modelFunction Função callback a ser executada pela função genérica.
 */
export default async function handleRoute(req, res, modelFunction) {
    try {
        const result = await modelFunction(req.params, req.body) //Executa a função e retorna no res o status retornado pela função.
        res.status(result.status).json(result.obj)
    } catch (error) {
        console.log(error)
        res.status(500).json({erro: "Erro no servidor."})
    }
}