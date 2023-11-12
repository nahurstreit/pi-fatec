/**
 * Importe essa variável para facilitar declaração de status 500.
 */
export const error_serverError = {
    500: {
        description: "Erro interno ao procurar os alimentos no banco de dados.",
        content: {
            "application/json": {
                schema: {
                    type: "object",
                    example: {
                        erro: "Erro no servidor."
                    }
                }
            }
        }
    },
}
