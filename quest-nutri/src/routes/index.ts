import { Router } from 'express'
import patientRoutes from './patients'
import nutritionistRoutes from './nutritionists'
import adminRoutes from './admin'
import authRoutes from './auth'
import { authNutritionist } from '../middlewares/auth/auth.middleware'

const routes = Router()
routes.use('/auth', authRoutes)
routes.use('/patient', patientRoutes)
routes.use('/nutritionist', authNutritionist, nutritionistRoutes)
routes.use('/admin',adminRoutes)
//routes.use('/docs', swagger)

export default routes