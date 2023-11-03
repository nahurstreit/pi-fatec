import { Sequelize } from "sequelize"
const sequelize = new sequelize('questNutri', 'User', 'password',{
    host: './questNutri.db',
    dialect: 'sqlite'
})

export default sequelize
