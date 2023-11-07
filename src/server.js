import { Express } from "express"
import sequelize from "./database/dbConfig.js"
import CustomerRoutes from "./router/customer.routes.js"

//console.log("Samuel")

const PORT = 3000 || 3333

const app = express()
app.use(express.json())


const customerRoutes = new CustomerRoutes();
app.use('/user[s]?', customerRoutes.routes());

app.listen(PORT, () => { console.log(`Server started on port ${PORT}`) })