import { Router } from 'express'
import patientController from '../../controllers/patient.controller'

const patientRoutes = Router()

patientRoutes.route('/')
.get(patientController.getAll)
.post(patientController.create)

patientRoutes.route('/:id')
.get(patientController.getById)
.patch(patientController.updateById)
.delete(patientController.deleteById)

export default patientRoutes