import { Router } from 'express'
import nutritionistController from '../../controllers/nutritionist.controller'
import patientController from '../../controllers/patient.controller'
import { validateDto } from '../../middlewares/validate/validateDto.middleware'
import { CreatePatientDto } from '../../models/patient/dto/create.patient.dto'
import { UpdatePatientDto } from '../../models/patient/dto/update.patient.dto'
import dietRoutes from '../patients/diet/diet.routes'

const nutritionistRoutes = Router()

nutritionistRoutes.route('/')
	.get(nutritionistController.getById)

nutritionistRoutes.route('/patient')
	.get(patientController.getAll)
	.post(validateDto(CreatePatientDto), patientController.create)

nutritionistRoutes.route('/patient/:patientId')
	.get(patientController.getById)
	.patch(validateDto(UpdatePatientDto), patientController.updateById)
	.delete(patientController.deleteById)

nutritionistRoutes.use('/patient/:patientId/diet', dietRoutes)

export default nutritionistRoutes