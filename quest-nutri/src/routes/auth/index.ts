import { Router } from 'express'
import authController from '../../controllers/auth.controller'
import { validateDto } from '../../middlewares/validate/validateDto.middleware'
import CreateNutritionistDto from '../../models/nutritionist/dto/create.nutritionist.dto'
import { LoginDto } from '../../models/login.dto'
import { PasswordResetDto, SendPasswordResetDto } from '../../models/passwordReset.dto'

const authRoutes = Router()

authRoutes.route('/register')
	.post(validateDto(CreateNutritionistDto), authController.register)

authRoutes.route('/login/nutritionist')
	.post(validateDto(LoginDto), authController.nutriLogin)

authRoutes.route('/login/patient')
	.post(validateDto(LoginDto), authController.patientLogin)

authRoutes.route('/login/admin')
	.post(authController.adminLogin)

authRoutes.route('/password/reset/patient/send')
	.post(validateDto(SendPasswordResetDto), authController.sendResetPasswordToken)
    
authRoutes.route('/password/reset/patient/validate/:token')
	.post(validateDto(PasswordResetDto), authController.resetPasswordPatient)

export default authRoutes