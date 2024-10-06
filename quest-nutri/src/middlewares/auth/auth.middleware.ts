import { Request, Response, NextFunction } from 'express'
import jwt from 'jsonwebtoken'
import 'dotenv/config'
import NotFound from '../../errors/NotFound.error'
import BadRequest from '../../errors/BadRequest.error'
import Unauthorized from '../../errors/Unauthorized.error'
import findContextController, { DietContextRequest } from '../../controllers/findContext.controller'
import patientService from '../../services/patient.service'

export async function authNutritionist(req: Request, res: Response, next: NextFunction) {
	try {
		const token = req.header('Authorization')?.replace('Bearer ', '')
		if(!token) throw new Unauthorized(`Token not provided`)
		try {
			const decoded = jwt.verify(token, process.env.JWT_SECRET!) as { nutritionistId: any, role: 'nutritionist', iat: number, exp: number }
			req.headers.nutritionistId = decoded?.nutritionistId.toString()
			next()
		} catch (error) {
			throw new BadRequest(`Invalid token`)
		}
	} catch (error) {
		next(new NotFound()) //preventing route exposure
	}
}

export async function authAndInjectPatient(req: Request, res: Response, next: NextFunction) {
	try {
		const token = req.header('Authorization')?.replace('Bearer ', '')
		if(!token) throw new Unauthorized(`Token not provided`)
		try {
			const decoded = jwt.verify(token, process.env.JWT_SECRET!) as { patientId: string, role: string, iat: number, exp: number }
			req.params.patientId = decoded.patientId
			next()
		} catch (error) {
			throw new BadRequest(`Invalid token`)
		}
	} catch (error) {
		next(new NotFound()) //preventing route exposure
	}
}