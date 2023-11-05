import { Express } from "express"
import sequelize from "./database/dbConfig.js"
import CustomerRoutes from "./router/customer.routes.js"

const PORT = 3000 || 3333

console.log("Samuel")

const customerRoutes = new CustomerRoutes()