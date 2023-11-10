import { Sequelize } from "sequelize"

const sequelize = new Sequelize('questNutri', 'User', 'password',{
    host: './questNutri.db',
    dialect: 'sqlite'
})

export default sequelize