/**
 * @summary Função para adequar o objeto Aliment para ser enviado como resposta de uma requisição da API.
 * 
 * @description A função recebe um objeto do tipo Aliment e através das informações desse objeto é feita uma
 * readaptação do objeto para que exclua o campo custom de sua estrutura.
 * 
 * @param {Aliment} aliment - Instância de Food ou SubFood a ser formatada. 
 * @returns {JSON} JSON com o objeto formatado contendo as informações principais daquela comida.
 */
export function formatAlimentResponse(aliment, showTableName=false) {
    const {custom, ...infoAliment} = aliment.dataValues

    if(showTableName) return {infoTable: custom == 0? "Tabela Taco" : "Alimento Customizado", ...infoAliment}
    return infoAliment
}