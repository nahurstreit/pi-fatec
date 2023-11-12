import paths from './paths/index.js'
import * as schemas from './schemas/index.js'

const swaggerDocument_v0_1_0 = {
    openapi: "3.1.0",
    info: {
        title: "QuestNutri",
        version: "0.1.0",
        description:
            "Documentação da API",
    },
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