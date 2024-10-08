import { Router } from 'express'
import nutritionistController from '../../controllers/nutritionist.controller'
import patientController from '../../controllers/patient.controller'
import { validateDto } from '../../middlewares/validate/validateDto.middleware'
import { CreatePatientDto } from '../../models/patient/dto/create.patient.dto'
import { UpdatePatientDto } from '../../models/patient/dto/update.patient.dto'
import dietRoutes from '../patients/diet/diet.routes'
import fctx from '../../controllers/findContext.controller'

const nutritionistRoutes = Router()

nutritionistRoutes.route('/').all(fctx.findNutritionist)
	.get(nutritionistController.getById)

nutritionistRoutes.route('/patient').all(fctx.findNutritionist)
	.get(patientController.getAll)
	.post(validateDto(CreatePatientDto), patientController.create)

nutritionistRoutes.route('/patient/:patientId').all(fctx.findNutritionist, fctx.injectPatient, fctx.findPatient)
	.get(patientController.getPatient)
	.patch(validateDto(UpdatePatientDto), patientController.updateById)
	.delete(patientController.deleteById)

nutritionistRoutes.use('/patient/:patientId/diet', fctx.injectPatient, dietRoutes)

export default nutritionistRoutes