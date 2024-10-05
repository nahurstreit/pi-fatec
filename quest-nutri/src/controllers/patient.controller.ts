import { NextFunction, Request, Response } from 'express'
import jwt from 'jsonwebtoken'
import patientService from '../services/patient.service'
import NotFound from '../errors/NotFound.error'
import ShouldNeverHappen from '../errors/ShouldNeverHappen.error'
import { generatePasswordResetToken } from '../utils/password.reset.util'

class PatientController {
	async findPatient(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const { patientId } = req.params
			const patient = await patientService.findById(patientId)
			if (!patient) throw new NotFound('Patient not found')
			return res.status(200).json(patient)
		} catch (error) {
			next(error)
		}
	}

	async getById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const { patientId } = req.params
			const patient = await patientService.findById(patientId)
			if (!patient) throw new NotFound('Patient not found')
			return res.status(200).json(patient)
		} catch (error) {
			next(error)
		}
	}

	async getAll(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const {nutritionistId} = req.headers
			if(!nutritionistId) throw new ShouldNeverHappen('Getting all patients from one nutritionist')
			const patients = await patientService.findAllFromNutritionist(nutritionistId as string)
			return res.status(200).json(patients)
		} catch (error) {
			next(error)
		}
	}

	async create(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const {nutritionistId} = req.headers
			if(!nutritionistId) throw new ShouldNeverHappen('Creating a new patient to a nutritionist')
			const newPatient = await patientService.create({...req.body, nutri: nutritionistId})
			return res.status(201).json({patient: newPatient, activationToken: generatePasswordResetToken('Patient', newPatient!._id as string)})
		} catch (error) {
			next(error)
		}
	}

	async getByEmail(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const patient = await patientService.findByEmail(req.body.email)
			if (!patient) throw new NotFound('Patient not found')
			return res.status(201).json(patient)
		} catch (error) {
			next(error)
		}
	}

	async updateById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const { patientId } = req.params
			const patient = await patientService.update(patientId, req.body)
			if (!patient) throw new NotFound('Patient not found')
			return res.status(200).json(patient)
		} catch (error) {
			next(error)
		}
	}

	async deleteById(req: Request, res: Response, next: NextFunction): Promise<void | any> {
		try {
			const { patientId } = req.params
			const patient = await patientService.delete(patientId)
			if (patient) return res.status(200).json({ message: 'Patient deleted' })
			throw new NotFound('Patient not found')
		} catch (error) {
			next(error)
		}
	}
}

export default new PatientController()