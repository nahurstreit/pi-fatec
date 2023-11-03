import { Sequelize } from "sequelize"
const sequelize = new sequelize('questNutri', 'User', 'password',{
    host: 'localHost',
    dialect: 'sqlite'
})

export default sequelize
