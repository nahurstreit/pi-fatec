import { Router } from 'express'
import fcx from '../../controllers/findContext.controller'
import patientController from '../../controllers/patient.controller'
import { validateDto } from '../../middlewares/validate/validateDto.middleware'
import { UpdatePatientDto } from '../../models/patient/dto/update.patient.dto'

const patientRoutes = Router({mergeParams: true})

patientRoutes.route('/').all(fcx.findPatient)
	.get(patientController.getPatient)
	.patch(validateDto(UpdatePatientDto), patientController.updateById)

export default patientRoutes