import paths from './paths/index.js'
import * as schemas from './schemas/index.js'

const swaggerDocument_v0_1_0 = {
    openapi: "3.1.0",
    info: {
        title: "QuestNutri",
        version: "0.1.0",
        description:
            "Esta é a documentação da API da futura aplicação: 'QuestNutri App', desenvolvida para o Projeto Integrador da FATEC Indaiatuba na matéria de Linguagem de Programação.",
    },
    tags: [
        {
            name: "Clientes", 
            description: "Métodos de acesso e manipulação de informação dos Clientes da aplicação."
        },
        {
            name: "Refeições",
            description: "Métodos de acesso e manipulação das Refeições de um Cliente específico."
        },
        {
            name: "Comidas Principais",
            description: "Métodos de acesso e manipulação das Comidas Principais de uma Refeição específica."
        },
        {
            name: "Comidas Substitutas",
            description: "Métodos de acesso e manipulação das Comidas Substitutas (ou opções de substituição) de uma Comida Principal."
        },
        {
            name: "Alimentos",
            description: "Métodos de acesso e manipulação de informações nutricionais de Alimentos Customizados e Alimentos da Tabela TACO."
        },
        {
            name: "Alimentos Tabela TACO",
            description: "Método de acesso a informações nutricionais apenas de Alimentos da Tabela TACO."
        },
        {
            name: "Alimentos Customizados",
            description: "Métodos de acesso e manipulação de informações nutricionais apenas de Alimentos Customizados."
        }
    ],
    servers: [
        {
            url: "http://localhost:3000",
        },
    ],

    paths,
    schemas: {
        ...schemas
    },
}

export default swaggerDocument_v0_1_0