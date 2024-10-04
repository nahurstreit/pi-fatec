import { Router } from 'express'
import patientRoutes from './patients'
import nutritionistRoutes from './nutritionists'

const routes = Router()
routes.use('/patient', patientRoutes)
routes.use('/nutritionist', nutritionistRoutes)

//routes.use('/docs', swagger)

export default routes