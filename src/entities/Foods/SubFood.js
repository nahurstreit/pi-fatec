import Aliment from "../Aliment.js"
import dbConnect from "../../models/dbConnect.js"
import { Model, DataTypes } from "sequelize"
import sequelize from "../../database/dbConfig.js"

/**
 * Classe para a definição de SubFood[Comidas Substitutas] de uma Food[Comida] principal. O termo Foods ou SubFoods dirá respeito a um alimento/comida quando referido em uma Meal[Refeição], enquanto o termo Aliment dirá respeito de alimento/comida quando referido no banco de dados, contendo informações nutricionais daquele alimento.
 */
export default class SubFood extends Model {
    /**
     * @param {Object} config
     * @param {number | null} config.idAlimentTaco - id do alimento na tabela TACO
     * @param {number} config.quantity - quantidade do alimento na refeição
     * @param {string} config.unityQt - unidade de medida principal
     * @param {string} config.obs - observação do alimento
     * @param {number | null} config.idAlimentCustom - id do alimento na tabela de alimentos CUSTOM
     */
    constructor(config) {
        super()
        this.mainAliment = null
        this.quantity = config.quantity || 0
        this.unityQt = config.unityQt || ""
        this.obs = config.obs || ""
        this.configAliment(config)
    }

    /**
     * Método para a configuração do alimento principal como um objeto da classe Aliment. Para isso serão acessadas, através do idAlimentTaco OU então idAlimentCustom, as informações nutricionais daquele alimento em um dos banco de dados. O programa sempre dará prioridade para procurar as informações nutricionais no banco de dados da tabela TACO, buscando idAlimentTaco dentro da chave primária de id dos alimentos da tabela TACO. Se idAlimentTaco for {null} o programa deverá procurar o idAlimentCustom na tabela de alimentos Custom. Em seguida, as informações serão passadas para o constructor da classe Aliment, fazendo com que o mainAliment seja uma instância de Aliment.
     * @method
     * @param {Object} config
     * @param {number | null} config.idAlimentTaco
     * @param {number | null} config.idAlimentCustom 
     */
    configAliment(config) {
        if(config.idAlimentTaco !== null) {
            const result = dbConnect.getInfoTaco(config.idAlimentTaco)
            this.mainAliment = new Aliment(result)
        } else {
            const result = dbConnect.getInfoAlimentCustom(config.idAlimentCustom)
            this.mainAliment = new Aliment(result)
        }
    }
}

SubFood.init({
    idSubFood: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    quantity: {
        type: DataTypes.STRING
    },
    unityQt: {
        type: DataTypes.STRING,
    },
    obs: {
        type: DataTypes.STRING
    }
},{
    sequelize,
    modelName: "SubFood",
    timestamps: false,
    tableName: "SubFoods"
})
