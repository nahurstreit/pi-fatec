import { Router } from 'express'
import patientRoutes from './patients'

const routes = Router()
routes.use('/patient', patientRoutes)

//routes.use('/docs', swagger)

export default routes