import { Router } from 'express'
import patientRoutes from './patients'
import nutritionistRoutes from './nutritionists'
import adminRoutes from './admin'
import authRoutes from './auth'
import { authAdmin, authNutritionist, authPatient } from '../middlewares/auth/auth.middleware'
import swaggerUi from 'swagger-ui-express'
import swaggerDocs from './../swagger.json'

const routes = Router()
routes.use('/auth', authRoutes)
routes.use('/patient', authPatient, patientRoutes)
routes.use('/nutritionist', authNutritionist, nutritionistRoutes)
routes.use('/admin', authAdmin, adminRoutes)
routes.use('/docs', swaggerUi.serve, swaggerUi.setup(swaggerDocs))

export default routes