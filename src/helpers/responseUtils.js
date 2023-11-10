/**
 * Função para facilitar a resposta de uma chamada à API.
 * Função para erros na execução do banco de dados ou por invalidade do "body" da requisição.
 * @param {String} message Mensagem a ser exibida quando chamar a API.
 * @param {Number} status Status HTTP a ser retornado. Status 400 por padrão.
 * @param {Error} erro Erro enviado pelo catch. Null por padrão.
 * @param {JSON} aditional Objeto com parâmetros opcionais a serem retornados.
 * 
 * @example
 * //Após pesquisar no banco de dados e não encontrar um usuário.
 * errorResult("Usuário não encontrado", 404)
 * return { 
 *      status: 404,
 *      obj: {erro: "Usuário não encontrado."}
 * } 
 * 
 * //Body enviado na requisição veio incorreto.
 * errorResult("Requisição Incorreta", 400, null, {updated: false})
 * return {
 *      status: 400,
 *      obj: {erro: "Requisição incorreta."},
 *      updated: false
 * } 
 * @returns {JSON}
 */
export function errorResult(message, status=400, erro=null, aditional) {
    if(erro) console.error(erro)
    return {status: status, obj: {erro: message}, ...aditional}
}


/**
 * Função para facilitar a resposta de uma chamada à API.
 * Função para erros na execução do código por parte do SERVIDOR.
 * @param {Error} erro Erro enviado pelo catch.
 * @param {String} message Mensagem a ser exibida quando chamar a API. Por padrão: "Erro no servidor"
 * @param {Number} status Status HTTP a ser retornado. Status 500 por padrão.
 * 
 * @example
 * //Indicando um erro padrão no servidor, enviado (error) de catch.
 * serverError(error)
 * return { 
 *      status: 500,
 *      obj: {erro: "Erro no servidor."}
 * } 
 * 
 * //Indicando um erro no banco de dados, com mensagem e status code personalizado.
 * serverError(error, "Banco de dados inativo", 502)
 * return {
 *      status: 502,
 *      obj: {erro: "Banco de dados inativo."}
 * }
 * 
 * @returns {JSON}
 */
export function serverError(erro, message="Erro no servidor.", status=500) {
    console.error(erro)
    return {status: status, obj:{erro: message}}
}


/**
 * Função para facilitar a resposta de uma chamada à API.
 * Função para respostas corretas do servidor.
 * @param {JSON} obj Objeto a ser retornado na resposta.
 * @param {String} status Status HTTP a ser retornado. Status 200 por padrão.
 * @param {JSON} aditional Objeto com parâmetros opcionais a serem retornados. 
 * 
 * @example
 * //Criando um usuário novo no sistema.
 * successResult(Customer, 201)
 * return {
 *      status: 201,
 *      obj: Customer // <- Instância de Customer. 
 * } 
 * 
 * //Enviando uma instância de Customer, depois de atualizado, com parâmetros opicionais.
 * successResult(Customer, 200, {found: true, updated: true})
 * return {
 *      status: 200, 
 *      obj: Customer,
 *      found: true, //<- Atributos do parâmetro aditional são distribuídos no Objeto final.
 *      updated: true
 * }
 * 
 * @returns {JSON}
 */
export function successResult(obj, status=200, aditional) {
    return {status: status, obj: obj, ...aditional}
}

/**
 * Função para facilitar a declaração de rotas incorretas para POST.
 * @returns {JSON}
 */
export function wrongPostPath() {
    return {status: 404, obj: {message: "Rota incorreta para POST."}}
}