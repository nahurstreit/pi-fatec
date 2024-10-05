import { Router } from 'express'
import patientController from '../../controllers/patient.controller'
import { validateDto } from '../../middlewares/validate/validateDto.middleware'
import { UpdatePatientDto } from '../../models/patient/dto/update.patient.dto'
import { authAndInjectPatient } from '../../middlewares/auth/auth.middleware'

const patientRoutes = Router({mergeParams: true})

patientRoutes.route('/').all(authAndInjectPatient)
	.get(patientController.getById)
	.patch(validateDto(UpdatePatientDto) ,patientController.updateById)

export default patientRoutes