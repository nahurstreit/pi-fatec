import { Model, DataTypes } from "sequelize"
import sequelize from "../../database/dbConfig.js"

import alimentCustomModel from "../../models/alimentCustomModel.js"
import tacoModel from "../../models/tacoModel.js"

/**
 * Classe para a definição de SubFood[Comidas Substitutas] de uma Food[Comida] principal. O termo Foods ou SubFoods dirá respeito a um alimento/comida quando referido em uma Meal[Refeição], enquanto o termo Aliment dirá respeito de alimento/comida quando referido no banco de dados, contendo informações nutricionais daquele alimento.
 */
export default class SubFood extends Model {
  /**
   * @param {Object} config
   * @param {Number} idSubFood - id da subfood no banco de dados
   * @param {Number} idFood - id da chave estrangeira food
   * @param {Number} config.idAliment - id do alimento (ou na tabela Taco ou AlimentCustom)
   * @param {Number} config.isTaco - se é da tabela taco (1 = sim, 0 = não)
   * @param {Number} config.quantity - quantidade do alimento na refeição
   * @param {String} config.unityQt - unidade de medida principal
   * @param {String} config.obs - observação do alimento
   */
    constructor(config) {
        super()
        this.idFood = config.idFood
        this.idSubFood = config.idSubFood
        this.idAliment = config.idAliment
        this.idMeal = config.idMeal
        this.isTaco = config.isTaco
        this.quantity = config.quantity
        this.unityQt = config.unityQt
        this.obs = config.obs
    }

    /**
     * @summary Método para a configuração do atributo mainAliment através do atributo idAliment e isTaco.
     * @description O método procura em um dos dois possíveis banco de dados de Alimentos, algum registro que tenha
     * como chave primária , o valor da propriedade idAliment deste mesmo objeto.
     * 
     * O registro pode ser encontrado ou na tabela 'Taco' ou na tabela 'AlimentCustom'. Ambas tabelas têm como nome da chave
     * primária: 'idAliment'. A tabela escolhida para buscar as informações do alimento será definida através da propriedade
     * 'isTaco' deste mesmo objeto.
     *  
     * Na hora de criar a instância do objeto [Food ou SubFood], será definida a propriedade 'isTaco' de acordo com a coluna
     * com esse mesmo nome. Essa coluna armazena apenas os valores 1 ou 0, representando {Sim} ou {Não}.
     * - Se a propriedade 'isTaco' == 1, idAliment será chave estrangeira da tabela 'Taco'.
     * - Se a propriedade 'isTaco' == 0, idAliment será chave estrangeira da tabela 'AlimentCustom'.
     * 
     * @returns {Promise<Food | SubFood>} Retorna o próprio objeto [Food ou SubFood] quando mainAliment for definido.
     */
    async searchMainAliment() {
        try {
            if(this.isTaco == 1) {
                const aliment = await tacoModel.findById({idAliment: this.idAliment})
                const {idAliment, ...info} = aliment.obj.dataValues
                this.mainAliment = {
                    tableName: "Tabela TACO",
                    ...info
                }
            } else {
                const aliment = await alimentCustomModel.findById({idAliment: this.idAliment})
                const {idAliment, ...info} = aliment.obj.dataValues
                this.mainAliment = {
                    tableName: "Alimentos Personalizados", 
                    ...info
                }
            }
            return this
        } catch (error) {
            return this
        }
    }
}

//Definição das colunas da tabela "SubFood" do banco de dados, para o sequelize.
SubFood.init({
    idSubFood: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    idAliment: {
        type: DataTypes.INTEGER
    },
    isTaco: {
        type: DataTypes.INTEGER
    },
    quantity: {
        type: DataTypes.STRING
    },
    unityQt: {
        type: DataTypes.STRING
    },
    obs: {
        type: DataTypes.STRING
    },
}, {
    sequelize,
    modelName: "SubFood",
    timestamps: false,
    tableName: "SubFoods",
})
